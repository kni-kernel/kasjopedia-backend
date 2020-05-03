package io.project.connector

import io.grpc.ManagedChannel
import java.io.Closeable
import io.project.proto.PdfConnectorGrpcKt.PdfConnectorCoroutineStub

class GRpcClient constructor(
    private val channel: ManagedChannel
) : Closeable {

    private val stub: PdfConnectorCoroutineStub = PdfConnectorCoroutineStub(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}