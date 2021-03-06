package co.edu.usbcali.demo.modelo;
// Generated 6/05/2017 09:20:10 AM by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Email;

/**
 * Clientes generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable {

	@NotNull
	private long cliId;
	@NotNull
	private TiposDocumentos tiposDocumentos;
	@NotNull
	private String cliNombre;
	@NotNull
	private String cliDireccion;
	@NotNull
	private String cliTelefono;
	
	@Email
	@NotNull
	private String cliMail;
	
	private Set<Cuentas> cuentases = new HashSet<Cuentas>(0);

	public Clientes() {
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono, String cliMail, Set<Cuentas> cuentases) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.cuentases = cuentases;
	}

	@Id

	@Column(name = "cli_id", unique = true, nullable = false, precision = 10, scale = 0)
	public long getCliId() {
		return this.cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdoc_codigo", nullable = false)
	public TiposDocumentos getTiposDocumentos() {
		return this.tiposDocumentos;
	}

	public void setTiposDocumentos(TiposDocumentos tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	@Column(name = "cli_nombre", nullable = false, length = 50)
	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	@Column(name = "cli_direccion", nullable = false, length = 50)
	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	@Column(name = "cli_telefono", nullable = false, length = 50)
	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	@Column(name = "cli_mail", length = 50)
	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public Set<Cuentas> getCuentases() {
		return this.cuentases;
	}

	public void setCuentases(Set<Cuentas> cuentases) {
		this.cuentases = cuentases;
	}

}
