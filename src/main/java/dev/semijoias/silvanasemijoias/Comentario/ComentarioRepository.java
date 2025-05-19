package dev.semijoias.silvanasemijoias.Comentario;

import dev.semijoias.silvanasemijoias.Administrador.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<ComentarioModel, Long> {
}
