package com.estacioGeladao.geladaoExpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacioGeladao.geladaoExpress.Service.UsuariosService;
import com.estacioGeladao.geladaoExpress.entities.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuariosService service;

	@PostMapping("/cadastroUser")
	public ResponseEntity<Usuario> registroUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioRegistrado = service.salvarUsuario(usuario);
			if (usuarioRegistrado != null) {
				System.out.println("Usuario cadastrado");
				return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			e.getCause();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/buscarUsuario/{id}")
	public Usuario buscarUsuario(@PathVariable Long id) {
		Usuario usuario = service.buscarUsuarioPorId(id);
		return usuario;
	}
	
	@GetMapping("/todosUsuarios")
	public List<Usuario> listarUsuarios(){
		List<Usuario> usuarios = service.todosUsuarios();
		return usuarios;
	}
}
