package br.com.mesquita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mesquita.dao.MotoDao;
import br.com.mesquita.dao.UsuarioDao;
import br.com.mesquita.model.Moto;
import br.com.mesquita.model.Usuario;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/moto")
public class MotoController {
	
	private UsuarioDao usuarioDao;
	private MotoDao motoDao;
	
	public MotoController(@Autowired UsuarioDao usuarioDao, MotoDao motoDao) {
		this.usuarioDao = usuarioDao;
		this.motoDao = motoDao;
	}
	

	@GetMapping
	public String inserir(HttpServletRequest request, Model model) {
		model.addAttribute("idUsuario", request.getParameter("id_usuario"));
	    return "moto/criar";
	}

	@PostMapping("/salvar")
	public String salvar(HttpServletRequest request, Model model) {
		Usuario dono = usuarioDao.buscaPorId(Long.parseLong(request.getParameter("idDono")));
		
		Moto moto = new Moto(request.getParameter("modelo"), 
				request.getParameter("cor"), 
				Integer.parseInt(request.getParameter("ano")),
				dono);
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			moto.setId(Long.parseLong(id));
		}
		
		motoDao.salvar(moto);
	    return "redirect:/usuario/editar?id=" + dono.getId();
	}
}
