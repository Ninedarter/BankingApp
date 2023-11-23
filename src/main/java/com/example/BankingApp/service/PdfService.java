//package com.example.BankingApp.service;
//
//import com.example.BankingApp.entity.Bank;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Iterator;
//
//public class PdfService {
//
//    public static void writeToAFile(Bank bank) throws IOException, DocumentException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("bankReport.pdf"));
//        document.open();
//        Paragraph top = new Paragraph("Report of a bank! + new Date()");
//        top.setAlignment(Paragraph.ALIGN_CENTER);
//        document.top();
//        document.add(new Paragraph(" "));
//        document.add(new Paragraph(" "));
//
//        // Create a table with 3 columns
//        PdfPTable table = new PdfPTable(3);
//
//        // Add headers
//        table.addCell("Id");
//        table.addCell("Full name");
//        table.addCell("Balance");
//
//        Iterator<BankAccount> iterator = bank.getBankAccounts().iterator();
//        while (iterator.hasNext()) {
//            BankAccount currentBankAccount = iterator.next();
//            table.addCell(String.valueOf(currentBankAccount.getId()));
//            table.addCell(currentBankAccount.getFullName());
//            table.addCell(String.valueOf(currentBankAccount.getBalance()) + "€");
//        }
//
//
//        // Add the table to the document
//        document.add(table);
//
//        // Add image
//        String imageUrl = "https://shorturl.at/airET";
//        Image image = Image.getInstance(new URL(imageUrl));
//        float width = 300f;
//        float height = 400f;
//        image.scaleAbsolute(width, height);
//        document.add(image);
//        document.close();
//    }
//}
