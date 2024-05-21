package com.talentob.pdfgenerator.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.talentob.pdfgenerator.dto.Product;
import com.talentob.pdfgenerator.dto.PurchaseOrderDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

@Service
public class PdfGeneratorService {
    public ByteArrayInputStream generatePdf(PurchaseOrderDTO order) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            DecimalFormat currencyFormat = new DecimalFormat("$###,###.00");

            Paragraph header = new Paragraph("MECATICO COMPANY")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(header);

            Paragraph subHeader = new Paragraph("Orden de compra:")
                    .setFontSize(14)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);
            document.add(subHeader);

            document.add(new Paragraph("Cédula/NIT: " + order.getCedula()).setMarginBottom(5));
            document.add(new Paragraph("Número de Teléfono: " + order.getTelefono()).setMarginBottom(15));

            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 1, 1}));
            table.setWidth(UnitValue.createPercentValue(100));
            table.setMarginBottom(20);

            table.addHeaderCell(new Paragraph("Nombre").setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Precio Unitario").setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Cantidad").setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Total").setBold().setTextAlignment(TextAlignment.CENTER));

            double total = 0;

            for (Product producto : order.getProductos()) {
                table.addCell(new Paragraph(producto.getNombre()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(currencyFormat.format(producto.getPrecioUnitario())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.valueOf(producto.getCantidad())).setTextAlignment(TextAlignment.CENTER));
                double totalProducto = producto.getPrecioUnitario() * producto.getCantidad();
                total += totalProducto;
                table.addCell(new Paragraph(currencyFormat.format(totalProducto)).setTextAlignment(TextAlignment.CENTER));
            }

            document.add(table);

            Paragraph totalParagraph = new Paragraph("Total de la orden: " + currencyFormat.format(total))
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(totalParagraph);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
