package dev.semijoias.silvanasemijoias.relatorios;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.Cliente.ClienteRepository;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaModel;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaRepository;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoRepository;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraRepository;
import dev.semijoias.silvanasemijoias.utils.RelatorioUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RelatorioService {

    private final ClienteRepository clienteRepository;
    private final VendedoraRepository vendedoraRepository;
    private final JoiaRepository joiaRepository;
    private final ItemMaletaRepository itemMaletaRepository;

    public ResponseEntity<byte[]> gerarRelatorioDeClientes() {
        List<ClienteModel> clientes = clienteRepository.findAllByOrderByValorTotalCompradoDesc();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(clientes));

        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioClientes", parametros);
    }


    public ResponseEntity<byte[]> gerarRelatorioDeVendedoras() {
        List<VendedoraModel> vendedoras = vendedoraRepository.findAllByOrderByComissaoDesc();

        if (vendedoras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(vendedoras));

        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioVendedoras", parametros);
    }


    public ResponseEntity<byte[]> gerarRelatorioDeJoiasEsgotadas() {
        List<JoiaModel> joiasEsgotadas = joiaRepository.findByQuantidadeEstoque(0);

        if (joiasEsgotadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(joiasEsgotadas));

        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioJoiasEsgotadas", parametros);
    }


    public ResponseEntity<byte[]> gerarRelatorioDeJoiasPorTipo(String descricao) {
        List<JoiaModel> joiasPorTipo = joiaRepository.findByTipoDescricao(descricao);

        if (joiasPorTipo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(joiasPorTipo));

            return RelatorioUtils.gerarRelatorioEmPDF("RelatorioJoiasPorTipo", parametros);
    }


    public ResponseEntity<byte[]> gerarRelatorioDeMaletaPorVendedora(MaletaModel maleta) {
        List<ItemMaletaModel> itensPorMaleta = itemMaletaRepository.findByMaleta(maleta);

        if (itensPorMaleta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ItemRelatorioDTO> itensRelatorio = itensPorMaleta.stream()
                .map(item -> {
                    JoiaModel joia = item.getJoia();
                    if (joia == null) {
                        return null;
                    }

                    String imagemUrl = joia.getImagemUrl();
                    String tipo = joia.getTipo().getDescricao();
                    String colecao = joia.getColecao().getNome();
                    Double valorVenda = joia.getValorVenda();
                    Integer quantidadeEstoque = joia.getQuantidadeEstoque();
                    LocalDate dataInsercao = item.getDataInsercao();

                    return new ItemRelatorioDTO(
                            imagemUrl,
                            tipo,
                            colecao,
                            valorVenda,
                            quantidadeEstoque,
                            dataInsercao
                    );
                })
                .filter(Objects::nonNull)
                .toList();

        if (itensRelatorio.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(itensRelatorio));

        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioDeMaletaPorVendedora", parametros);
    }


}
