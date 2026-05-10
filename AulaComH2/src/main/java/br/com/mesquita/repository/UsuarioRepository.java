package br.com.mesquita.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mesquita.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	List<Usuario> findAllByNome(String nome);
	
	/**
	 * Este método busca usuário por parte do nome ignorando letras maiúsculas
	 * ou minúsculas.
	 * @param nome
	 * @return Lista de usuários
	 */
	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
}
