package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaAtualizadoDTO;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaDTO;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaletaResponseDTO {

    private Long id;
    private VendedoraDTO vendedora;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private List<ItemMaletaAtualizadoDTO> itens;

}
