package dev.semijoias.silvanasemijoias.Vendedora;


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
        vendedoraModel.setTipoUsuario(vendedoraDTO.getTipoUsuario());
        vendedoraModel.setIdMaleta(vendedoraDTO.getIdMaleta());
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
        vendedoraDTO.setTipoUsuario(vendedoraModel.getTipoUsuario());
        vendedoraDTO.setIdMaleta(vendedoraModel.getIdMaleta());
        vendedoraDTO.setComissao(vendedoraModel.getComissao());
        return vendedoraDTO;
    }
}
