package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.dto.RelatorioDTO;
import br.com.aptare.cpt.dto.ResultadoDTO;
import br.com.aptare.cpt.entity.Partida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioRepository extends Repository<Partida, Long> {


    @Query(value = """
             select 'GERAL' as agrupador,
                     usr.apl_usr as descricao,
                     usr.nm_usr as nome,
                     sum(tim.pnt_tim) as pontuacao
             from sc_sgr.tbl_usr usr
                inner join sc_prt.tbl_tim_usr tus on tus.cd_usr = usr.cd_usr
                inner join sc_prt.tbl_tim tim on tim.cd_tim = tus.cd_tim
                inner join sc_prt.tbl_prt prt on prt.cd_prt = tim.cd_prt
                inner join sc_rch.tbl_rch rch on rch.cd_rch = prt.cd_rch
             where rch.cd_rch = :idRacha
             group by descricao, nome
             order by pontuacao desc
        """, nativeQuery = true)
    List<ResultadoDTO> listarRelatorio(@Param("idRacha") Long idRacha);

    @Query(value = """
             select to_char(prt.dt_prt, 'dd/MM/yyyy') as agrupador,
                     usr.apl_usr as descricao,
                     usr.nm_usr as nome,
                     sum(tim.pnt_tim) as pontuacao
             from sc_sgr.tbl_usr usr
                inner join sc_prt.tbl_tim_usr tus on tus.cd_usr = usr.cd_usr
                inner join sc_prt.tbl_tim tim on tim.cd_tim = tus.cd_tim
                inner join sc_prt.tbl_prt prt on prt.cd_prt = tim.cd_prt
                inner join sc_rch.tbl_rch rch on rch.cd_rch = prt.cd_rch
             where rch.cd_rch = :idRacha
             group by descricao, nome, agrupador
            order by agrupador desc, pontuacao desc
        """, nativeQuery = true)
    List<ResultadoDTO> listarRelatorioData(@Param("idRacha") Long idRacha);
}
