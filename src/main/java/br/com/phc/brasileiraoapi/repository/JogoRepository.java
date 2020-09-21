package br.com.phc.brasileiraoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.brasileiraoapi.entity.Jogo;
import br.com.phc.brasileiraoapi.entity.Time;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>{

	public Optional<Jogo> findByUuId(String uuId);
	
	public List<Jogo> findByTimeCasaAndEncerrado(Time time, Boolean encerrado);
	
    public List<Jogo> findByTimeVisitanteAndEncerrado(Time time, Boolean encerrado);
}
