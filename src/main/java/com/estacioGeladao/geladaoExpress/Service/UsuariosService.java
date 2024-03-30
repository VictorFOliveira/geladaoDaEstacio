package com.estacioGeladao.geladaoExpress.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacioGeladao.geladaoExpress.Exceptions.UsuarioException;
import com.estacioGeladao.geladaoExpress.Repository.UsuarioRepository;
import com.estacioGeladao.geladaoExpress.entities.Usuario;

@Service
public class UsuariosService {

	@Autowired
	private UsuarioRepository repository;

	 public Usuario salvarUsuario(Usuario usuario) {
	        return repository.save(usuario);
	    }

	public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
		Usuario usuario = repository.findById(id).orElse(null);

		if (usuario == null) {
			throw new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO);
		}

		usuario.setNome(novoUsuario.getNome());
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setCpf(novoUsuario.getCpf());
		usuario.setSenha(novoUsuario.getSenha());
		usuario.setAcesso(novoUsuario.getAcesso());

		return repository.save(usuario);
	}

	public Usuario buscarUsuarioPorId(Long id) {
		Usuario usuario = repository.findById(id).orElse(null);
		if (usuario == null) {
			throw new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO);
		}
		return usuario;
	}

	public List<Usuario> todosUsuarios() {
		return repository.findAll();
	}

	public void deletarUsuarioId(Long id) {
		Usuario usuario = repository.findById(id).orElse(null);

		if (usuario == null) {
			throw new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO);
		}
		repository.deleteById(usuario.getId());
	}
}
