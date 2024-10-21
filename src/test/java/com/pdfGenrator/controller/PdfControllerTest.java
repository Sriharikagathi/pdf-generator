package com.pdfGenrator.controller;


import com.pdfGenrator.model.PdfRequest;
import com.pdfGenrator.service.PdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PdfControllerTest {

    @InjectMocks
    private PdfController pdfController;

    @Mock
    private PdfService pdfService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGeneratePdf() throws IOException {
        // Arrange
        PdfRequest request = new PdfRequest();
        request.setSeller("XYZ Pvt. Ltd.");
        request.setSellerGstin("29AABBCCDD121ZD");
        request.setSellerAddress("New Delhi, India");
        request.setBuyer("Vedant Computers");
        request.setBuyerGstin("29AABBCCDD131ZD");
        request.setBuyerAddress("New Delhi, India");
        request.setItems(Arrays.asList(
                new PdfRequest.Item("Product 1", "12 Nos", 123.00, 1476.00),
                new PdfRequest.Item("Product 2", "5 Nos", 200.00, 1000.00)
        ));

        when(pdfService.generatePdf(request)).thenReturn("C:/generated_pdfs/29AABBCCDD121ZD.pdf");

        // Act
        ResponseEntity<String> response = pdfController.generatePdf(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("C:/generated_pdfs/29AABBCCDD121ZD.pdf", response.getBody());
    }
}
