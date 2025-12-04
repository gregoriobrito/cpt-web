package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Racha;
import br.com.aptare.cpt.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RachaRepository extends JpaRepository<Racha, Long> {

    @Query(value = "select distinct rch.* \n" +
                    "from sc_rch.tbl_rch rch \n" +
                    "  inner join sc_rch.tbl_rch_usr rus on rus.cd_rch = rch.cd_rch \n" +
                    "  inner join sc_sgr.tbl_usr usr on usr.cd_usr = rus.cd_usr \n" +
                    "where rch.st_rch = 1 \n" +
                    "  and rus.st_rch_usr = 1 \n" +
                    "  and usr.st_usr = 1 \n" +
                    "  and usr.cd_usr = :idUsuario \n" +
                    "order by rch.nm_rch ", nativeQuery = true)
    List<Racha> listarPorUsuario(@Param("idUsuario") Long idUsuario);


    @Query(value = "select distinct usr.* \n" +
            "from sc_rch.tbl_rch rch \n" +
            "  inner join sc_rch.tbl_rch_usr rus on rus.cd_rch = rch.cd_rch \n" +
            "  inner join sc_sgr.tbl_usr usr on usr.cd_usr = rus.cd_usr \n" +
            "where rch.st_rch = 1 \n" +
            "  and rus.st_rch_usr = 1 \n" +
            "  and usr.st_usr = 1 \n" +
            "  and rch.cd_rch = :idRacha \n" +
            "order by usr.nm_usr ", nativeQuery = true)
    List<Usuario> listarUsuario(@Param("idRacha") Long idRacha);

}
