package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.ICooperativaDao;
import com.bolsadeideas.springboot.app.models.dao.IEscuelaDao;
import com.bolsadeideas.springboot.app.models.dao.IMunicipioDao;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Municipio;

@Service
public class ServiciosServiceImpl implements IServiciosService {
	@Autowired
	IMunicipioDao municipioDao;
	@Autowired
	IEscuelaDao escuelaDao;
	@Autowired
	ICooperativaDao cooperativaDao;

	public List<Municipio> municipioFindByName(String municipio) {
		//return municipioDao.findByMunicipioRegionId(municipio);
		return null;
	}

	@Transactional(readOnly = true)
	public Escuela findByClaveEscuela(String clave) {

		return escuelaDao.findByClaveescuela(clave);
	}

	public void guardarEscuela(Escuela escuela) {
		escuelaDao.save(escuela);

	}

	public Page<Escuela> findAll(Pageable pageable) {
		return escuelaDao.findAll(pageable);
	}

	public Escuela findbyIdEscuela(Long id_escuela) {

		return escuelaDao.findById(id_escuela).orElse(null);
	}

	public void deleteEscuela(Long id_escuela) {
		escuelaDao.deleteById(id_escuela);
	}

	public Municipio findMunicipioByID(Long id) {
		return municipioDao.findById(id).orElse(null);
	}

	
	//Gesction cooperaticas
	
	
	public List<Cooperativa> findAllCooperativas() {
		// TODO Auto-generated method stub
		return (List<Cooperativa>) cooperativaDao.findAll();
	}

	public Cooperativa findOneCooperativaById(Long id_cooperativa) {
		// TODO Auto-generated method stub
		return cooperativaDao.findById(id_cooperativa).orElse(null);
	}

	public void deleteCooperativaById(Long id_cooperativa) {
	cooperativaDao.deleteById(id_cooperativa);
	
		
	}

	public void saveCooperativa(Cooperativa cooperativa) {
		cooperativaDao.save(cooperativa);
		
	}

	public void deleteCooperativaByCooperativa(Cooperativa cooperativa) {
		cooperativaDao.delete(cooperativa);
		
	}

}
