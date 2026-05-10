package br.com.mesquita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mesquita.dao.MotoDao;
import br.com.mesquita.dao.UsuarioDao;
import br.com.mesquita.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	private MotoDao motoDao;
	
	public UsuarioController(@Autowired UsuarioDao usuarioDao, MotoDao motoDao) {
		this.usuarioDao = usuarioDao;
		this.motoDao = motoDao;
	}

	@GetMapping
	public String home(Model model) {
		model.addAttribute("listaUsuario", usuarioDao.listar());
	    return "index";
	}

	@PostMapping("/criar")
	public String criar(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Usuario usuario = new Usuario(nome, email);
		usuarioDao.inserir(usuario);
	    return "redirect:/usuario";
	}

	@PostMapping("/buscar")
	public String buscar(HttpServletRequest request, Model model) {
		String nome = request.getParameter("nome");
		IO.print(usuarioDao.buscaPorNome(nome).toString());
		model.addAttribute("listaUsuario", usuarioDao.buscaPorNome(nome));
	    return "index";
	}
	
	@GetMapping("/editar")
	public String editar(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		Usuario usuario = usuarioDao.buscaPorId(Long.parseLong(id));
		model.addAttribute("id", id);
		model.addAttribute("nome",usuario.getNome());
		model.addAttribute("email", usuario.getEmail());
		model.addAttribute("listaMoto", motoDao.buscarPorDono(usuario));
		
	    return "usuario/form";
	}
	
	@GetMapping("/deletar")
	public String deletar(HttpServletRequest request, Model model) {
		usuarioDao.deletar(Long.parseLong(request.getParameter("id")));
	    return "redirect:/usuario";
	}
	
	@PostMapping("/salvar")
	public String salvar(HttpServletRequest request) {
		Usuario usuario = new Usuario(request.getParameter("nome"), request.getParameter("email"));
		usuario.setId(Long.parseLong(request.getParameter("id")));
		usuarioDao.salvarUsuario(usuario);
		return "redirect:/usuario";
	}
}
