package dev.semijoias.silvanasemijoias.Usuario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "O campo nome é obrigatorio")
    private String nome;
    @Size(min = 11, max = 11)
    private String cpf;
    @Email(message = "O campo email deve ser valido")
    private String email;
    @NotBlank(message = "Senha precisa ter no minimo seis digitos")
    @Size(min = 6, max = 128, message = "Senha precisa ter no minimo seis digitos")
    private String senha;
    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;
    private TipoUsuario tipoUsuario;
    @NotBlank(message = "O usuario precisa de um telefone")
    private String telefone;

}
