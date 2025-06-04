package dev.semijoias.silvanasemijoias.Vendedora;


import dev.semijoias.silvanasemijoias.Usuario.TipoUsuario;
import org.springframework.stereotype.Component;

@Component
public class VendedoraMapper {

    public VendedoraModel map(VendedoraDTO vendedoraDTO) {
        VendedoraModel vendedoraModel = new VendedoraModel();
        vendedoraModel.setId(vendedoraDTO.getId());
        vendedoraModel.setNome(vendedoraDTO.getNome());
        vendedoraModel.setCpf(vendedoraDTO.getCpf());
        vendedoraModel.setEmail(vendedoraDTO.getEmail());
        vendedoraModel.setSenha(vendedoraDTO.getSenha());
        vendedoraModel.setEndereco(vendedoraDTO.getEndereco());
        vendedoraModel.setTipoUsuario(TipoUsuario.VENDEDORA);
        vendedoraModel.setTelefone(vendedoraDTO.getTelefone());
        vendedoraModel.setComissao(vendedoraDTO.getComissao());
        return vendedoraModel;
    }

    public VendedoraDTO map(VendedoraModel vendedoraModel) {
        VendedoraDTO vendedoraDTO = new VendedoraDTO();
        vendedoraDTO.setId(vendedoraModel.getId());
        vendedoraDTO.setNome(vendedoraModel.getNome());
        vendedoraDTO.setCpf(vendedoraModel.getCpf());
        vendedoraDTO.setEmail(vendedoraModel.getEmail());
        vendedoraDTO.setSenha(vendedoraModel.getSenha());
        vendedoraDTO.setEndereco(vendedoraModel.getEndereco());
        vendedoraDTO.setTipoUsuario(TipoUsuario.VENDEDORA);
        vendedoraDTO.setTelefone(vendedoraModel.getTelefone());
        vendedoraDTO.setComissao(vendedoraModel.getComissao());

        if (vendedoraModel.getMaleta() != null) {
            vendedoraDTO.setMaletaId(vendedoraModel.getMaleta().getId());
        }

        return vendedoraDTO;
    }
}
