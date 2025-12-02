package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}
