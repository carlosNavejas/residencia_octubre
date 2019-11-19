package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
@Query("select u from Usuario u where u.correo=?1")
	public Usuario findByCorreo(String correo);
}
