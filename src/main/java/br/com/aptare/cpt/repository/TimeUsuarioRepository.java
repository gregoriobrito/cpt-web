package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.dto.InformacoesPerfilDTO;
import br.com.aptare.cpt.entity.TimeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeUsuarioRepository extends JpaRepository<TimeUsuario, Long> {

    @Query(value = """
             select count(distinct(prt.cd_prt)) as totalPartida,
                      sum(case when tim.pnt_tim > (select t.pnt_tim from sc_prt.tbl_tim t where t.cd_prt = prt.cd_prt and t.cd_tim <> tim.cd_tim) then 1 else 0 end) as vitorias,
                      sum(tim.pnt_tim) as pontos
             from sc_prt.tbl_tim_usr tus
                 inner join sc_prt.tbl_tim tim on tim.cd_tim = tus.cd_tim
                 inner join sc_prt.tbl_prt prt on prt.cd_prt = tim.cd_prt
             where tus.cd_usr = :idUsuario
               and prt.cd_rch = :idRacha
               and prt.st_prt = 1
               and tus.st_tim_usr = 1
        """, nativeQuery = true)
    InformacoesPerfilDTO informacoesUsuario(@Param("idRacha") Long idRacha, @Param("idUsuario") Long idUsuario);
}
