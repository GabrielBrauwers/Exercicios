package br.com.mesquita.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mesquita.model.Moto;
import br.com.mesquita.model.Usuario;

@Repository
public interface MotoRepository extends CrudRepository<Moto, Long>{
	public List<Moto> findAllByDono(Usuario dono);
}
