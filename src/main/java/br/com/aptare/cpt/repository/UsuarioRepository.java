package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    @Query("select u from Usuario u where u.codigo in (:idUsuario)")
    List<Usuario> listarIn(@Param("idUsuario") List<Long> idUsuario);


}
