package br.com.mesquita.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mesquita.model.Usuario;
import br.com.mesquita.repository.UsuarioRepository;

@Service
public class UsuarioDao {
	
	UsuarioRepository usuarioRepository;
	
	public UsuarioDao(@Autowired UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	/**
	 * Insere novo usuário no banco. O id é auto incrementado e não precisa
	 * ser adicionado.
	 * @param usuario
	 * @return true se a operação funcionou.
	 */
	public boolean inserir(Usuario usuario) {
		Usuario usuarioCriado = usuarioRepository.save(usuario);
		return usuarioCriado != null && usuarioCriado.getId() != null;	
	}

	public List<Usuario> buscaPorNome(String nome) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios.addAll(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
		return listaUsuarios;
	}

	public List<Usuario> listar() {
		List<Usuario> listaUsuarios = new ArrayList<>();
		usuarioRepository.findAll().forEach(u -> listaUsuarios.add(u));
		return listaUsuarios;
	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario buscaPorId (Long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return usuario ;
	}
	
	public void salvarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
}
