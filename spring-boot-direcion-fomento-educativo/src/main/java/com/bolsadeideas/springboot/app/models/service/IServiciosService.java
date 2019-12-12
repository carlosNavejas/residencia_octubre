package com.bolsadeideas.springboot.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Acervo_bibliografico;
import com.bolsadeideas.springboot.app.models.entity.Asignatura;
import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;
import com.bolsadeideas.springboot.app.models.entity.Item_acervo;
import com.bolsadeideas.springboot.app.models.entity.Municipio;
import com.bolsadeideas.springboot.app.models.entity.Region;
import com.bolsadeideas.springboot.app.models.entity.Reparto_utilidades;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

public interface IServiciosService {

	// Regiones
	public List<Region> finAllRegiones();

	// municipios
	public List<Municipio> municipioFindByRegionId(Long id_region);

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

	// bibliotecas
	public void saveBiblioteca(Biblioteca biblioteca);

	public void deleteBibliotecaByID(Long id_biblioteca);

	public Biblioteca findByIdBiblioteca(Long id_biblioteca);

	// Socios de cooperativa
	public void saveSocioDeCooperativa(Socio socio);

	public void deleteSocioById(Long id_socio);

	public Socio findSocioById(Long id_socio);

	public Page<Socio> findAllSocios(Pageable pageable);

	public Page<Socio> findSocioByCooperativa(Long id_cooperativa, Pageable pageable);

	public List<Socio> listarSociosByCooperativa(Long id_cooperativa);

	// CRUD ingresos egresos
	public void saveIngresosEgresos(Ingresos_egresos ingresos_egresos);

	public void deleteIngresosEgresos(Long id_ingresosegresos);

	public Ingresos_egresos findIngresosEgresosByID(Long id_ingresosEgresos);

	public Page<Ingresos_egresos> findAllIngresosEgresos(Long id, Pageable pageable);

	public Page<Ingresos_egresos> findAllIngresosEgresosByCooperativaWithPeriodo(Pageable pageable);

	public Page<Ingresos_egresos> buscarIngresosEntreFechas(Long id, Date fechaInit, Date fechaEnd, Pageable pageable);

//CRUD usuarios
	public void saveUsuario(Usuario usuario);

	public void deleteUsuarioById(Long id_socio);

	public Usuario findUsuarioById(Long id_socio);

	public Page<Usuario> findAllUsuarios(Pageable pageable);

	public Usuario findUsuarioByCorreo(String correo);

	// Save rol
	public void saveRolUser(Rol rol);

	public int contarSociosPorCooperativa(Long id);

	// crud reparto de utilidades
	public void saveRepartoUtilidades(Reparto_utilidades repartin);

	public Reparto_utilidades findByClaveRepartoUtilidades(Long id_reparto);

	public Page<Reparto_utilidades> findRepartoUtilidadesByIdCoopeartiva(Long idbuscar, Pageable pageable);

	// acervo bibliografico
	public void saveAcervoBibliografico(Acervo_bibliografico acervo);

	public Page<Reparto_utilidades> findPageAcervoBibliografico(Long idbuscar, Pageable pageable);

	public void deleteAcervoBibliograficoByID(Long id_acervo);

	// item Acervo bibliografico
	public void saveItemAcervo(Item_acervo item);

	public void deleteItemAcervo(Long id);

	public void finfItemAcervo(Long id);

	public List<Item_acervo> findAllItem_Acervo(Long id);

	// Asignatura del acervo bibliografico
	public void saveAsignatura(Asignatura asignatura);

	public void deleteAsignatura(Asignatura asignatura);

	public void findOneByIdAsignatura(Asignatura asignatura);

	public List<Asignatura> findAllByIdAsignatura(Long id_biblioteca);
}
