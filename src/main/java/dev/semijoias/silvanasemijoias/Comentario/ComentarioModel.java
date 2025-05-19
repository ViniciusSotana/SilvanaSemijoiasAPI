package dev.semijoias.silvanasemijoias.Comentario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long joiaId;
    private Long usuarioId;
    private String emailUsuario;
    private String comentario;

}
