package com.pdfGenrator.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private com.pdfGenrator.service.PdfService pdfService;

    public PdfController(com.pdfGenrator.service.PdfService pdfService) {
        this.pdfService = pdfService;
    }


    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestBody com.pdfGenrator.model.PdfRequest request) {
        try {
            String pdfPath = pdfService.generatePdf(request);
            return ResponseEntity.ok(pdfPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating PDF: " + e.getMessage());
        }
    }
}
