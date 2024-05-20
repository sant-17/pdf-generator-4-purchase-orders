package com.talentob.pdfgenerator.controller;

import com.talentob.pdfgenerator.dto.PurchaseOrderDTO;
import com.talentob.pdfgenerator.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @PostMapping("/generate-pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody PurchaseOrderDTO order) {
        ByteArrayInputStream bis = pdfGeneratorService.generatePdf(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=orden_compra.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
