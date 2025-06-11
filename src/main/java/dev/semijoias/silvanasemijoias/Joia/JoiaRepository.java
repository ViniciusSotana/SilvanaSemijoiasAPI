package dev.semijoias.silvanasemijoias.Joia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoiaRepository extends JpaRepository<JoiaModel, Long> {

    List<JoiaModel> findByQuantidadeEstoque(int quantidadeEstoque);

    List<JoiaModel> findByTipoDescricao(String descricao);
}
