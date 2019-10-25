package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Municipio;

public interface IServiciosService {

	public List<Municipio> municipioFindByName(String municipio);

	public Escuela findByClaveEscuela(String clave);

	public void guardarEscuela(Escuela escuela);

	public Escuela findbyIdEscuela(Long id_escuela);

	public Page<Escuela> findAll(Pageable pageable);

	public void deleteEscuela(Long id_escuela);

	public Municipio findMunicipioByID(Long id);

	// Gestion cooperativas
	public List<Cooperativa> findAllCooperativas();

	public Cooperativa findOneCooperativaById(Long id_cooperativa);

	public void deleteCooperativaById(Long id_cooperativa);
	public void deleteCooperativaByCooperativa(Cooperativa cooperativa);

	public void saveCooperativa(Cooperativa cooperativa);
}
