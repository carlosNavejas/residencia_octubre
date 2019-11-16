package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IBibliotecaDao;
import com.bolsadeideas.springboot.app.models.dao.ICooperativaDao;
import com.bolsadeideas.springboot.app.models.dao.IEscuelaDao;
import com.bolsadeideas.springboot.app.models.dao.IIngresosEgresosDao;
import com.bolsadeideas.springboot.app.models.dao.IMunicipioDao;
import com.bolsadeideas.springboot.app.models.dao.IRegionDao;
import com.bolsadeideas.springboot.app.models.dao.ISociosDao;
import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;
import com.bolsadeideas.springboot.app.models.entity.Municipio;
import com.bolsadeideas.springboot.app.models.entity.Region;
import com.bolsadeideas.springboot.app.models.entity.Socio;

@Service
public class ServiciosServiceImpl implements IServiciosService {
	@Autowired
	private IMunicipioDao municipioDao;
	@Autowired
	private IEscuelaDao escuelaDao;
	@Autowired
	private ICooperativaDao cooperativaDao;
	@Autowired
	private IBibliotecaDao bibliotecaDao;
	@Autowired
	private ISociosDao socioDao;
	@Autowired
	private IRegionDao regionDao;
	@Autowired
	private IIngresosEgresosDao ingresosDao;

	@Transactional(readOnly = true)
	public List<Municipio> municipioFindByName(String municipio) {
		// return municipioDao.findByMunicipioRegionId(municipio);
		return null;
	}

	@Transactional(readOnly = true)
	public Escuela findByClaveEscuela(String clave) {

		return escuelaDao.findByClaveescuela(clave);
	}

	@Transactional
	public void guardarEscuela(Escuela escuela) {
		escuelaDao.save(escuela);

	}

	@Transactional(readOnly = true)
	public Page<Escuela> findAll(Pageable pageable) {
		return escuelaDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Escuela findbyIdEscuela(Long id_escuela) {

		return escuelaDao.findById(id_escuela).orElse(null);
	}

	@Transactional
	public void deleteEscuela(Long id_escuela) {
		escuelaDao.deleteById(id_escuela);
	}

	@Transactional(readOnly = true)
	public Municipio findMunicipioByID(Long id) {
		return municipioDao.findById(id).orElse(null);
	}

	// Gesction cooperaticas

	@Transactional(readOnly = true)
	public List<Cooperativa> findAllCooperativas() {
		// TODO Auto-generated method stub
		return (List<Cooperativa>) cooperativaDao.findAll();
	}

	@Transactional(readOnly = true)
	public Cooperativa findOneCooperativaById(Long id_cooperativa) {
		// TODO Auto-generated method stub
		return cooperativaDao.findById(id_cooperativa).orElse(null);
	}

	@Transactional
	public void deleteCooperativaById(Long id_cooperativa) {
		cooperativaDao.deleteById(id_cooperativa);

	}

	@Transactional
	public void saveCooperativa(Cooperativa cooperativa) {
		cooperativaDao.save(cooperativa);

	}

	@Transactional
	public void deleteCooperativaByCooperativa(Cooperativa cooperativa) {
		cooperativaDao.delete(cooperativa);

	}

	// biblitecas
	@Transactional
	public void saveBiblioteca(Biblioteca biblioteca) {
		bibliotecaDao.save(biblioteca);

	}

	@Transactional
	public void deleteBibliotecaByID(Long id_biblioteca) {
		bibliotecaDao.deleteById(id_biblioteca);

	}

	@Transactional(readOnly = true)
	public Biblioteca findByIdBiblioteca(Long id_biblioteca) {

		return bibliotecaDao.findById(id_biblioteca).orElse(null);
	}

//Socios de cooperativa
	@Transactional
	public void saveSocioDeCooperativa(Socio socio) {
		socioDao.save(socio);

	}

	@Transactional
	public void deleteSocioById(Long id_socio) {
		socioDao.deleteById(id_socio);

	}

	@Transactional(readOnly = true)
	public Socio findSocioById(Long id_socio) {
		return socioDao.findById(id_socio).orElse(null);
	}

	@Transactional(readOnly = true)
	public Page<Socio> findAllSocios(Pageable pageable) {

		return socioDao.findAll(pageable);
	}

	public Page<Socio> findSocioByCooperativa(Long id_cooperativa, Pageable pageable) {
		return socioDao.findByCooperativa(id_cooperativa, pageable);

	}

	public List<Region> finAllRegiones() {

		return (List<Region>) regionDao.findAll();
	}

	public List<Municipio> municipioFindByRegionId(Long id_region) {

		return municipioDao.fechtByIdregion(id_region);
	}

	// CRUD ingresos y egresos
	public void saveIngresosEgresos(Ingresos_egresos ingresos_egresos) {
		ingresosDao.save(ingresos_egresos);

	}

	public void deleteIngresosEgresos(Long id_ingresosegresos) {
		ingresosDao.deleteById(id_ingresosegresos);

	}

	public Ingresos_egresos findIngresosEgresosByID(Long id_ingresosEgresos) {

		return ingresosDao.findById(id_ingresosEgresos).orElse(null);
	}

	public Page<Ingresos_egresos> findAllIngresosEgresos(Pageable pageable) {

		return ingresosDao.findAll(pageable);
	}

	public Page<Ingresos_egresos> findAllIngresosEgresosByCooperativaWithPeriodo(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Socio> listarSociosByCooperativa(Long id_cooperativa) {

		return socioDao.findByCooperativaa(id_cooperativa);
	}

}
