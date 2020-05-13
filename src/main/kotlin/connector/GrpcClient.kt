package io.project.connector

import io.grpc.ManagedChannel
import io.project.proto.GenerateSpzRequest
import io.project.proto.SpzGeneratorServiceGrpcKt.SpzGeneratorServiceCoroutineStub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.Closeable
import java.util.concurrent.TimeUnit

class GrpcClient constructor(
    private val channel: ManagedChannel
) : Closeable {

    private val stub: SpzGeneratorServiceCoroutineStub = SpzGeneratorServiceCoroutineStub(channel)

    fun sendRequest(data: String) = runBlocking {
        val request = GenerateSpzRequest.newBuilder().setContent(data).build()
        return@runBlocking withContext(Dispatchers.Default) {
            stub.generateSpz(request)
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
