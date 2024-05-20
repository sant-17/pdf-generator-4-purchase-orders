package com.talentob.pdfgenerator.service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.talentob.pdfgenerator.dto.Product;
import com.talentob.pdfgenerator.dto.PurchaseOrderDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {
    public ByteArrayInputStream generatePdf(PurchaseOrderDTO order) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("MECATICO COMPANY"));
            document.add(new Paragraph("Orden de compra:"));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Cédula/NIT: " + order.getCedula()));
            document.add(new Paragraph("Número de Teléfono: " + order.getTelefono()));
            document.add(new Paragraph(" "));

            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 1, 1}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Paragraph("Nombre").setBold());
            table.addHeaderCell(new Paragraph("Precio Unitario").setBold());
            table.addHeaderCell(new Paragraph("Cantidad").setBold());
            table.addHeaderCell(new Paragraph("Total").setBold());

            double total = 0;

            for (Product producto : order.getProductos()) {
                table.addCell(new Paragraph(producto.getNombre()));
                table.addCell(new Paragraph("$" + producto.getPrecioUnitario()));
                table.addCell(new Paragraph(String.valueOf(producto.getCantidad())));
                total = total + (producto.getPrecioUnitario() * producto.getCantidad());
                table.addCell(new Paragraph("$" + producto.getPrecioUnitario() * producto.getCantidad()));
            }

            document.add(table);

            document.add(new Paragraph("Total de la orden: $" + total));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
