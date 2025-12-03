package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("select p from Partida p where p.codigoRacha = :idRacha and p.situacao = 1")
    List<Partida> listarPartidaRacha(@Param("idRacha") Long idRacha);

    @Query(value = "select count(*) from sc_prt.tbl_prt p where p.cd_rch = :idRacha and p.st_prt = 1 and p.dt_prt >= :dataInicio and p.dt_prt <= :dataFim", nativeQuery = true)
    Integer quantidadePartida(@Param("idRacha") Long idRacha, @Param("dataInicio")Date dataInicio, @Param("dataFim") Date dataFim);

}
