package dev.semijoias.silvanasemijoias.relatorios;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.Cliente.ClienteRepository;
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

//    public ResponseEntity<byte[]> gerarRelatorioDeClientes(){
//
//        ClienteModel cliente = this.clienteRepository.findById(1L)
//                .orElse(null);
//
//        Map<String, Object> parametros = new HashMap<>();
//
//        parametros.put("NOME", cliente.getNome());
//        parametros.put("CPF", cliente.getCpf());
//        parametros.put("EMAIL", cliente.getEmail());
//        parametros.put("VALORTOTAL", cliente.getValorTotalComprado());
//        parametros.put("TELEFONE", cliente.getTelefone());
//        parametros.put("ENDERECO", cliente.getEndereco());
//        parametros.put("LISTA", new JRBeanCollectionDataSource(clienteRepository.findAll()));
//        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioClientes", parametros);
//    }

    public ResponseEntity<byte[]> gerarRelatorioDeClientes() {
        List<ClienteModel> clientes = clienteRepository.findAllByOrderByValorTotalCompradoDesc();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LISTA", new JRBeanCollectionDataSource(clientes));

        return RelatorioUtils.gerarRelatorioEmPDF("RelatorioClientes", parametros);
    }

}
