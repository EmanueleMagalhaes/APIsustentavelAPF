package br.com.sustentavel.apisustentavel.repository;

import br.com.sustentavel.apisustentavel.model.AcaoSustentavel;
import br.com.sustentavel.apisustentavel.model.CategoriaAcao;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {
    List<AcaoSustentavel> findByCategoria(CategoriaAcao categoria);
}
