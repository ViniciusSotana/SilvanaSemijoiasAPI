package dev.semijoias.silvanasemijoias.relatorios;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRelatorioDTO {

    private String imagemUrl;
    private String tipo;
    private String colecao;
    private Double valorVenda;
    private Integer quantidadeEstoque;
    private LocalDate dataInsercao;

}