package dev.semijoias.silvanasemijoias.relatorios;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.Cliente.ClienteRepository;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraRepository;
import dev.semijoias.silvanasemijoias.utils.RelatorioUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RelatorioService {

    private final ClienteRepository clienteRepository;
    private final VendedoraRepository vendedoraRepository;

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

}
