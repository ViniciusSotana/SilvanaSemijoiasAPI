package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaletaDTO {

    @NotNull(message = "Id obrigatorio")
    private Long id;
    @NotNull(message = "Vendedora obrigatorio")
    private VendedoraModel vendedora;
    @NotNull(message = "Status obrigatorio")
    private String status;
    @NotNull(message = "Data de entrega obrigatorio")
    private Date dataEntrega;
    @NotNull(message = "Data de devolucao obrigatorio")
    private Date dataDevolucao;


}
