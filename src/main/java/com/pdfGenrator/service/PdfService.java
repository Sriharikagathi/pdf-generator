package com.pdfGenrator.service;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class PdfService {
    private final String PDF_DIRECTORY = "C:\\generated_pdfs\\";

    public String generatePdf(com.pdfGenrator.model.PdfRequest request) throws IOException {
        // Create directory if not exists
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Create PDF file path
        String pdfFilePath = PDF_DIRECTORY + request.getSellerGstin() + ".pdf";

        // Check if PDF already exists
        if (new File(pdfFilePath).exists()) {
            return pdfFilePath; // Return existing PDF path
        }

        // Generate new PDF
        com.pdfGenrator.utils.PdfUtils.createPdf(pdfFilePath, request);
        return pdfFilePath;
    }
}

