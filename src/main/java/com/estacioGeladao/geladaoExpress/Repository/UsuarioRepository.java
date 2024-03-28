package com.estacioGeladao.geladaoExpress.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacioGeladao.geladaoExpress.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
