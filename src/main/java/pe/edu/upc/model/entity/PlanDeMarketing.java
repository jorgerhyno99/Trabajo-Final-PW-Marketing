package pe.edu.upc.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "planDeMarketing")
public class PlanDeMarketing{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int planDeMarketing_id;
	
	@Column(name = "name", length = 80, nullable = false)
	private String name;
	
	@Column(name = "descripcion", length = 80, nullable = false)
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "areaDeInteres_id")
	private AreaDeInteres areaDeInteres;
	
	@OneToMany(mappedBy = "planDeMarketing", fetch = FetchType.LAZY)
	private List<Campania> campanias;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "planDeMarketing_Objetivo", joinColumns = {@JoinColumn(name = "planDeMarketing_id")}, inverseJoinColumns = {@JoinColumn(name = "objetivo_id")})
	private Set<Objetivo> objetivos;

	public List<Campania> fetchCampanias() {
		return campanias;
	}
	
	/*public Optional<Campania> fetchEmployeeById(Integer id) {
		for (Campania employee : campanias) {
			if( employee.getId().equals( id ) ) {
				return Optional.of(employee);
			}
		}
		return Optional.empty();
	}*/
	
	

	public void addCampania( Campania employee) {
		employee.setPlanDeMarketing(this);
		this.campanias.add(employee);
	}
	
	public PlanDeMarketing() {
		campanias = new ArrayList<>();
		objetivos = new HashSet<>();
	}

	

	public int getPlanDeMarketing_id() {
		return planDeMarketing_id;
	}

	public void setPlanDeMarketing_id(int planDeMarketing_id) {
		this.planDeMarketing_id = planDeMarketing_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public AreaDeInteres getAreadeinteres() {
		return areaDeInteres;
	}

	public void setAreadeinteres(AreaDeInteres areaDeInteres) {
		this.areaDeInteres = areaDeInteres;
	}

	public List<Campania> getCampanias() {
		return campanias;
	}

	public void setCampanias(List<Campania> campanias) {
		this.campanias = campanias;
	}

	public AreaDeInteres getAreaDeInteres() {
		return areaDeInteres;
	}

	public void setAreaDeInteres(AreaDeInteres areaDeInteres) {
		this.areaDeInteres = areaDeInteres;
	}

	public Set<Objetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(Set<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}
	
	
}
