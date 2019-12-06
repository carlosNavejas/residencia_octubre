package com.bolsadeideas.springboot.app.models.service;

import java.util.Date;
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
import com.bolsadeideas.springboot.app.models.dao.IRolesDao;
import com.bolsadeideas.springboot.app.models.dao.ISociosDao;
import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;
import com.bolsadeideas.springboot.app.models.entity.Municipio;
import com.bolsadeideas.springboot.app.models.entity.Region;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

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
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IRolesDao rolDao;

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

	@Transactional(readOnly = true)
	public Page<Socio> findSocioByCooperativa(Long id_cooperativa, Pageable pageable) {
		return socioDao.findByCooperativa(id_cooperativa, pageable);

	}

	@Transactional(readOnly = true)
	public List<Region> finAllRegiones() {

		return (List<Region>) regionDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Municipio> municipioFindByRegionId(Long id_region) {

		return municipioDao.fechtByIdregion(id_region);
	}

	// CRUD ingresos y egresos
	@Transactional
	public void saveIngresosEgresos(Ingresos_egresos ingresos_egresos) {
		ingresosDao.save(ingresos_egresos);

	}

	@Override
	public void deleteIngresosEgresos(Long id_ingresosegresos) {
		// TODO Auto-generated method stub

	}
@Transactional(readOnly = true)
	@Override
	public Ingresos_egresos findIngresosEgresosByID(Long id_ingresosEgresos) {
		// TODO Auto-generated method stub
		return ingresosDao.findById(id_ingresosEgresos).orElse(null);
	}

	@Override
	public Page<Ingresos_egresos> findAllIngresosEgresos(Long id, Pageable pageable) {

		return ingresosDao.findIngresosByCooperativa(id, pageable);
	}

	@Override
	public Page<Ingresos_egresos> findAllIngresosEgresosByCooperativaWithPeriodo(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

//crud Usuarios 

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		usuarioDao.save(usuario);

	}

	@Override
	@Transactional
	public void deleteUsuarioById(Long id_socio) {
		usuarioDao.deleteById(id_socio);

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Long id_socio) {

		return usuarioDao.findById(id_socio).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAllUsuarios(Pageable pageable) {

		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioByCorreo(String correo) {
		// TODO Auto-generated method stub
		return usuarioDao.findByCorreo(correo);
	}

	@Override
	public void saveRolUser(Rol rol) {
		rolDao.save(rol);

	}

	@Override
	public List<Socio> listarSociosByCooperativa(Long id_cooperativa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contarSociosPorCooperativa(Long id) {

		return socioDao.countSociosByCooperativa(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Ingresos_egresos> buscarIngresosEntreFechas(Long id, Date fechaInit, Date fechaEnd,
			Pageable pageable) {

		return ingresosDao.findIngresosBetweenCiclo(id, fechaInit, fechaEnd, pageable);
	}

}
