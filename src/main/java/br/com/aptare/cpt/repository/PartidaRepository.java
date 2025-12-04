package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Time;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("select p from Partida p where p.situacao = 1 and p.codigoRacha = :idRacha and (:idPartida is null or p.codigo = :idPartida) order by p.dataSemHora desc, p.codigo")
    List<Partida> listarPartidaRacha(@Param("idRacha") Long idRacha, @Param("idPartida") Long idPartida);

    @Query(value = "select count(*) from sc_prt.tbl_prt p where p.cd_rch = :idRacha and p.st_prt = 1 and p.dt_prt >= :dataInicio and p.dt_prt <= :dataFim", nativeQuery = true)
    Integer quantidadePartida(@Param("idRacha") Long idRacha, @Param("dataInicio")Date dataInicio, @Param("dataFim") Date dataFim);

    @EntityGraph(attributePaths = { "listaTime", "racha" })
    Optional<Partida> findWithListaTimeByCodigo(Long codigo);

}
