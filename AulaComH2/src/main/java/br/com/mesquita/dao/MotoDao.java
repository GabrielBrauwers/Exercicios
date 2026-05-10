package br.com.mesquita.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mesquita.model.Moto;
import br.com.mesquita.model.Usuario;
import br.com.mesquita.repository.MotoRepository;

@Service
public class MotoDao {

	MotoRepository motoRepository;
	
	public MotoDao(@Autowired MotoRepository motoRepository) {
		this.motoRepository = motoRepository;
	}
	
	public List<Moto> listar(){
		List<Moto> listaDeMotos = new ArrayList<>();
		motoRepository.findAll().forEach(m -> listaDeMotos.add(m));
		return listaDeMotos;
	}
	
	public boolean salvar(Moto moto) {
		return motoRepository.save(moto) == null ? false : true;
	}
	
	public void deletar(Long id) {
		motoRepository.deleteById(id);
	}
	
	public Moto buscarPorId(Long id) {
		return motoRepository.findById(id).get();
	}

	public List<Moto> buscarPorDono(Usuario dono) {
		return motoRepository.findAllByDono(dono);
	}
}
