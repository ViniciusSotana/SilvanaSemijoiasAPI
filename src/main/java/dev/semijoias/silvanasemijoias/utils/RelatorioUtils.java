package dev.semijoias.silvanasemijoias.utils;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class RelatorioUtils {
    public static ResponseEntity<byte[]> gerarRelatorioEmPDF(String nomeArquivo, Map<String, Object> parametrosRelatorio) {
        try {
            Resource resource = new ClassPathResource(nomeArquivo + ".jrxml");
            InputStream inputStream = resource.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametrosRelatorio, new JREmptyDataSource());

            byte[] relatorioBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorioBytes);
        } catch (IOException | JRException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}