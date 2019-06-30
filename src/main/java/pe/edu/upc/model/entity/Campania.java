package pe.edu.upc.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "campania")
public class Campania{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	@Column(name = "name", length = 80, nullable = false)
	private String name;

	
	@Column(name = "descripcion", length = 80, nullable = false)
	private String descripcion;
	
	
	@Past(message = "No puedes seleccionar un dia que todavia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio;

	@Past(message = "No puedes seleccionar un dia que todavia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "planDeMarketing_id")
	private PlanDeMarketing planDeMarketing;
	
	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;

	
	
	
	
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

	public PlanDeMarketing getPlanDeMarketing() {
		return planDeMarketing;
	}

	public void setPlanDeMarketing(PlanDeMarketing planDeMarketing) {
		this.planDeMarketing = planDeMarketing;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}



	
}
