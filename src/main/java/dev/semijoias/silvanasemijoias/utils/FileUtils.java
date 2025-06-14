package dev.semijoias.silvanasemijoias.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static void salvarPDFNoDisco(byte[] pdfBytes, String nomeArquivo) {
        try {
            String userHome = System.getProperty("user.home");
            Path diretorio = Paths.get(userHome, "Downloads");

            if (!diretorio.toFile().exists()) {
                diretorio.toFile().mkdirs();
            }

            Path caminhoArquivo = diretorio.resolve(nomeArquivo + ".pdf");

            try (FileOutputStream fos = new FileOutputStream(caminhoArquivo.toFile())) {
                fos.write(pdfBytes);
                System.out.println("✅ Relatório salvo com sucesso em: " + caminhoArquivo.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar o PDF na pasta Downloads: " + e.getMessage());
        }
    }
}