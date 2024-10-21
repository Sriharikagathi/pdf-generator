package com.pdfGenrator.pdfUtils;

import com.pdfGenrator.model.PdfRequest;
import com.pdfGenrator.utils.PdfUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PdfUtilsTest {

    @Test
    public void testCreatePdf() throws IOException {
        // Arrange
        String pdfFilePath = "C:/generated_pdfs/test_invoice.pdf";
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

        // Act
        PdfUtils.createPdf(pdfFilePath, request);

        // Assert
        File pdfFile = new File(pdfFilePath);
        assertTrue(pdfFile.exists(), "PDF should be created successfully.");

        // Clean up - delete the test PDF
        pdfFile.delete();
    }
}
