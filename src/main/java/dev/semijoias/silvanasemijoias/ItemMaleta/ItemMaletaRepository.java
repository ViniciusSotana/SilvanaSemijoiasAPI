package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMaletaRepository extends JpaRepository<ItemMaletaModel, Long> {

    List<ItemMaletaModel> findByMaleta(MaletaModel maleta);

}
