package io.project.connector

import io.grpc.stub.StreamObserver
import io.project.proto.GeneratedPdf
import io.project.proto.PdfConnectorGrpc
import io.project.proto.Request

class GRpcConnector : PdfConnectorGrpc.PdfConnectorImplBase() {

    override fun getGeneratedPdf(request: Request?, responseObserver: StreamObserver<GeneratedPdf>?) {

    }
}
