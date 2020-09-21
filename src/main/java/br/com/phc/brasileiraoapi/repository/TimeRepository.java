package br.com.phc.brasileiraoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.brasileiraoapi.entity.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{

	public Optional<Time> findByUuId(String uuId);
}
