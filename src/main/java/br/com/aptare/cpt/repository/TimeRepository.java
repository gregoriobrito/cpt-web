package br.com.aptare.cpt.repository;

import br.com.aptare.cpt.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
