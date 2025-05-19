package dev.semijoias.silvanasemijoias.Administrador;

import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<AdministradorModel, Long> {
}
