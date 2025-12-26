package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Partida;
import br.com.aptare.cpt.entity.RachaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RachaUsuarioRepository extends JpaRepository<RachaUsuario, Long> {

    @Query("select p from RachaUsuario p where p.situacao = 1 and p.codigoRacha = :idRacha and p.codigoUsuario = :idUsuario")
    RachaUsuario findByRachaUsuario(@Param("idRacha") Long idRacha, @Param("idUsuario") Long idUsuario);

}