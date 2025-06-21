package dev.semijoias.silvanasemijoias.Cliente;

import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import dev.semijoias.silvanasemijoias.Usuario.TipoUsuario;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO map(ClienteModel model) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setCpf(model.getCpf());
        dto.setTelefone(model.getTelefone());
        dto.setEndereco(model.getEndereco());
        dto.setValorTotalComprado(model.getValorTotalComprado());
        dto.setNumeroDePedidos(model.getNumeroDePedidos());
        dto.setDataCadastro(model.getDataCadastro());
        dto.setPedidos(model.getPedidos());
        dto.setTipoUsuario(TipoUsuario.CLIENTE);

        return dto;
    }

    public ClienteModel map(ClienteDTO dto) {
        ClienteModel model = new ClienteModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setCpf(dto.getCpf());
        model.setTelefone(dto.getTelefone());
        model.setEndereco(dto.getEndereco());
        model.setValorTotalComprado(dto.getValorTotalComprado());
        model.setNumeroDePedidos(dto.getNumeroDePedidos());
        model.setDataCadastro(dto.getDataCadastro());
        model.setTipoUsuario(TipoUsuario.CLIENTE);

        return model;
    }
}