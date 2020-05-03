package io.project.connector

class HttpConnector: PdfConnector {
    override fun sendRequest(form: String): String {
        return form
    }

}
