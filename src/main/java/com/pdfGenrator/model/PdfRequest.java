package com.pdfGenrator.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
public class PdfRequest {
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<Item> items;

    // Getters and Setters

    public static class Item {
        private String name;
        private String quantity;
        private double rate;
        private double amount;

        public Item(String name, String quantity, double rate, double amount) {
            this.name = name;
            this.quantity = quantity;
            this.rate = rate;
            this.amount = amount;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }


        // Getters and Setters
    }
}
