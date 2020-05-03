package io.project.connector

import io.grpc.ManagedChannel
import java.io.Closeable
import io.project.proto.PdfConnectorGrpcKt.PdfConnectorCoroutineStub
import io.project.proto.Request
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.TimeUnit

class GrpcClient constructor(
    private val channel: ManagedChannel
) : Closeable {

    private val stub: PdfConnectorCoroutineStub = PdfConnectorCoroutineStub(channel)

    suspend fun sendRequest(data: String) = coroutineScope {
        val request = Request.newBuilder().setData(data).build()
        val response = async {
            stub.getGeneratedPdf(request)
        }
        println(response)
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
