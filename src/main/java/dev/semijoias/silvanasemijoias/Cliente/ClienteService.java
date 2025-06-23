package dev.semijoias.silvanasemijoias.Cliente;

import dev.semijoias.silvanasemijoias.Usuario.TipoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDTO criar(ClienteDTO dto) {
        ClienteModel model = clienteMapper.map(dto);
        model.setDataCadastro(java.time.LocalDate.now());
        model.setTipoUsuario(TipoUsuario.CLIENTE);
        ClienteModel salvo = clienteRepository.save(model);
        return clienteMapper.map(salvo);
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::map)
                .toList();
    }

    public ClienteDTO buscarPorId(Long id) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return clienteMapper.map(cliente);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        ClienteModel atualizado = clienteRepository.save(cliente);
        return clienteMapper.map(atualizado);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
