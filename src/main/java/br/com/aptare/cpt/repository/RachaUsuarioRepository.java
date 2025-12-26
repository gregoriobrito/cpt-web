package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.RachaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RachaUsuarioRepository extends JpaRepository<RachaUsuario, Long> {
}