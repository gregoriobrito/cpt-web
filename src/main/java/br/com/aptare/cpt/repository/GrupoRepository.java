package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query(
    """
        select g
        from Grupo g
          left join g.listaGrupoUsuario gu
          left join gu.usuario u
        where g.situacao = 1
          and g.codigoRacha = :idRacha
        order by g.descricao, u.apelido
    """)
    List<Grupo> findWithListaTimeByCodigoRacha(@Param("idRacha") Long idRacha);

}
