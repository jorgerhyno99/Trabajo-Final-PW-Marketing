package pe.edu.upc.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "areaDeInteres")
public class AreaDeInteres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", length = 80, nullable = false)
	private String name;

	@Column(name = "descripcion", length = 80, nullable = false)
	private String descrpcion;

	@Column(name = "activo", nullable = false)
	private Boolean activo;
	
	@OneToMany(mappedBy = "areaDeInteres", fetch = FetchType.LAZY)
	private List<PlanDeMarketing> planesDeMarketing;
	
	public AreaDeInteres() {
		planesDeMarketing = new ArrayList<>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrpcion() {
		return descrpcion;
	}

	public void setDescrpcion(String descrpcion) {
		this.descrpcion = descrpcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public List<PlanDeMarketing> getPlanesdemarketing() {
		return planesDeMarketing;
	}
	public void setPlanesdemarketing(List<PlanDeMarketing> planesDeMarketing) {
		this.planesDeMarketing = planesDeMarketing;
	}
	public List<PlanDeMarketing> getPlanesDeMarketing() {
		return planesDeMarketing;
	}
	public void setPlanesDeMarketing(List<PlanDeMarketing> planesDeMarketing) {
		this.planesDeMarketing = planesDeMarketing;
	}

}
