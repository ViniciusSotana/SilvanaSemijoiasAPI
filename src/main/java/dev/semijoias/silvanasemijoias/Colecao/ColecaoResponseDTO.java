package dev.semijoias.silvanasemijoias.Colecao;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColecaoResponseDTO {
    private Long id;
    private String nome;
    private List<JoiaRequestDTO> joias = new ArrayList<>();
    private LocalDate dataLancamento;
}