package io.project.service

import io.project.connector.PdfConnector

class PdfService(private val pdfConnector: PdfConnector) {

    fun generatePdf(form: String): String {
        return pdfConnector.sendRequest(form)
    }
}
