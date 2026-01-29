package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.dto.RachaDTO;
import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.entity.RachaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RachaUsuarioRepository extends JpaRepository<RachaUsuario, Long> {

    @Query("select p from RachaUsuario p where p.situacao = 1 and p.codigoRacha = :idRacha and p.codigoUsuario = :idUsuario")
    RachaUsuario findByRachaUsuario(@Param("idRacha") Long idRacha, @Param("idUsuario") Long idUsuario);

    @Modifying
    @Transactional
    @Query("update RachaUsuario p set p.situacao = 2 where p.codigoRacha = :idRacha and p.codigoUsuario = :idUsuario")
    int desvincularRacha(@Param("idRacha") Long idRacha, @Param("idUsuario") Long idUsuario);

    @Query(value = """
            select distinct rch.cd_rch as codigo,
                            rch.nm_rch as nome,
                            rus.fg_adm_rch_usr as flagUsuarioAdmin
            from sc_rch.tbl_rch_usr rus
              inner join sc_rch.tbl_rch rch on rus.cd_rch = rch.cd_rch
              inner join sc_sgr.tbl_usr usr on usr.cd_usr = rus.cd_usr
            where rch.st_rch = 1
              and rus.st_rch_usr = 1
              and usr.st_usr = 1
              and usr.cd_usr = :idUsuario
            order by rch.nm_rch
            """, nativeQuery = true)
    List<RachaDTO> listarPorUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Transactional
    @Query("update RachaUsuario p set p.flagAdministrador = :administrador where p.codigoRacha = :idRacha and p.codigoUsuario = :idUsuario")
    int tornarRetirarAdministrador(@Param("idRacha") Long idRacha, @Param("idUsuario") Long idUsuario, @Param("administrador") String administrador);

}