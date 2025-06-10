package dev.semijoias.silvanasemijoias.Vendedora;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedoraRepository extends JpaRepository<VendedoraModel, Long> {

    List<VendedoraModel> findAllByOrderByComissaoDesc();
}
