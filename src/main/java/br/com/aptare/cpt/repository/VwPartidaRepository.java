package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.VwPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VwPartidaRepository extends JpaRepository<VwPartida, Long> {

    @Query("select p from VwPartida p where p.codigoRacha = :idRacha and (:idPartida is null or p.codigo = :idPartida) order by p.data desc, p.codigo")
    List<VwPartida> listarPartidaRacha(@Param("idRacha") Long idRacha, @Param("idPartida") Long idPartida);
}
