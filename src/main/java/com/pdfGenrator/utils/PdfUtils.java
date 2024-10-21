package com.pdfGenrator.utils;


import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.UnitValue;
import com.pdfGenrator.model.PdfRequest;

import java.io.IOException;

public class PdfUtils {

    public static void createPdf(String pdfFilePath, PdfRequest request) throws IOException {
        // Create PDF writer and document
        PdfWriter writer = new PdfWriter(pdfFilePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add title
        // Create a table for seller and buyer details (2 columns)
        Table detailsTable = new Table(2);
        detailsTable.setWidth(UnitValue.createPercentValue(100));

        // Seller Details
        detailsTable.addCell(new Cell()
                .add(new Paragraph("Seller Details").setBold())
                );
        detailsTable.addCell(new Cell()
                .add(new Paragraph("Buyer Details").setBold())
                );

        detailsTable.addCell(new Cell().add(new Paragraph("Name: " + request.getSeller())));
        detailsTable.addCell(new Cell().add(new Paragraph("Name: " + request.getBuyer())));

        detailsTable.addCell(new Cell().add(new Paragraph("GSTIN: " + request.getSellerGstin())));
        detailsTable.addCell(new Cell().add(new Paragraph("GSTIN: " + request.getBuyerGstin())));

        detailsTable.addCell(new Cell().add(new Paragraph("Address: " + request.getSellerAddress())));
        detailsTable.addCell(new Cell().add(new Paragraph("Address: " + request.getBuyerAddress())));

        // Add seller and buyer details table to the document
        document.add(detailsTable);
        document.add(new Paragraph("\n")); // Add a blank line for spacing

        // Create a table for items (4 columns)
        Table itemTable = new Table(4);
        itemTable.setWidth(UnitValue.createPercentValue(100));

        // Add table header for items
        itemTable.addHeaderCell(new Cell().add(new Paragraph("Item Name")));
        itemTable.addHeaderCell(new Cell().add(new Paragraph("Quantity")));
        itemTable.addHeaderCell(new Cell().add(new Paragraph("Rate")));
        itemTable.addHeaderCell(new Cell().add(new Paragraph("Amount")));

        // Add item rows
        for (var item : request.getItems()) {
            itemTable.addCell(new Cell().add(new Paragraph(item.getName())));
            itemTable.addCell(new Cell().add(new Paragraph(item.getQuantity())));
            itemTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getRate()))));
            itemTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getAmount()))));
        }

        // Add the items table to the document
        document.add(itemTable);

        // Close the document
        document.close();
    }
}
