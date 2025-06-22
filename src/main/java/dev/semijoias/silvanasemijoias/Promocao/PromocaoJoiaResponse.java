package dev.semijoias.silvanasemijoias.Promocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocaoJoiaResponse {

    private Long id;
    @NotBlank(message = "Nome obrigatorio")
    private String nome;
    @NotNull(message = "Percentual de desconto obrigatorio")
    private Double percentualDesconto;
    @NotNull(message = "Data de inicio obrigatorio")
    private LocalDate dataInicio;
    @NotNull(message = "Data de fim obrigatorio")
    private LocalDate dataFim;

}
