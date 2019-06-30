package pe.edu.upc.model.entity;

import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name  = "objetivo")
public class Objetivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", length = 80, nullable = false)
	private String name;
	
	@Column(name = "descripcion", length = 80, nullable = false)
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "objetivos")
	private Set<PlanDeMarketing> planesDeMarketing;
	
	@OneToMany(mappedBy = "objetivo", fetch = FetchType.LAZY)
	private List<Estrategia>estrategias;
	
	public Objetivo() {
		 planesDeMarketing = new HashSet<>();
		 estrategias = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<PlanDeMarketing> getPlanesDeMarketing() {
		return planesDeMarketing;
	}

	public void setPlanesDeMarketing(Set<PlanDeMarketing> planesDeMarketing) {
		this.planesDeMarketing = planesDeMarketing;
	}

	public List<Estrategia> getEstrategias() {
		return estrategias;
	}

	public void setEstrategias(List<Estrategia> estrategias) {
		this.estrategias = estrategias;
	}
	
}
