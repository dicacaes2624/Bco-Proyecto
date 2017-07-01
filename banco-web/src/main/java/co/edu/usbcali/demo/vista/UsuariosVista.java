package co.edu.usbcali.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.IUsuariosLogica;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class UsuariosVista {

	@ManagedProperty("#{usuariosLogica}")
	private IUsuariosLogica usuariosLogica;

	@ManagedProperty("#{tiposUsuariosLogica}")
	private ITiposUsuariosLogica tiposUsuariosLogica;

	private List<Usuarios> losUsuarios;

	private InputText txtUsuCedula;
	private SelectOneMenu somTiposUsuarios;
	private InputText txtUsuNombre;
	private InputText txtUsuLogin;
	private InputText txtUsuPass;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;

	private List<SelectItem> losTiposUsuariosSelectItem;

	public List<SelectItem> getLosTiposUsuariosSelectItem() {
		if (losTiposUsuariosSelectItem == null) {
			losTiposUsuariosSelectItem = new ArrayList<SelectItem>();
			List<TiposUsuarios> losTiposUsuarios = tiposUsuariosLogica.consultar();
			for (TiposUsuarios tiposUsuarios : losTiposUsuarios) {
				losTiposUsuariosSelectItem.add(new SelectItem(tiposUsuarios.getTusuCodigo(),
						tiposUsuarios.getTusuCodigo() + "-" + tiposUsuarios.getTusuNombre()));
			}
		}
		return losTiposUsuariosSelectItem;
	}

	public String crearAction() {
		try {
			Long usuId = Long.parseLong(txtUsuCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuId);
			usuarios.setUsuNombre(txtUsuNombre.getValue().toString());
			usuarios.setUsuLogin(txtUsuLogin.getValue().toString());
			usuarios.setUsuClave(txtUsuPass.getValue().toString());

			Long tdocCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tdocCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.crear(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se creo con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String modificarAction() {
		try {
			Long usuId = Long.parseLong(txtUsuCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuId);
			usuarios.setUsuNombre(txtUsuNombre.getValue().toString());
			usuarios.setUsuLogin(txtUsuLogin.getValue().toString());
			usuarios.setUsuClave(txtUsuPass.getValue().toString());

			Long tdocCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tdocCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.modificar(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se modifico con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String borrarAction() {
		try {
			Long usuId = Long.parseLong(txtUsuCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuId);
			usuarios.setUsuNombre(txtUsuNombre.getValue().toString());
			usuarios.setUsuLogin(txtUsuLogin.getValue().toString());

			Long tdocCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tdocCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.borrar(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se borro con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String limpiarAction() {
		txtUsuCedula.resetValue();
		losUsuarios = null;
		limpiar();
		return "";
	}

	public void txtIdentificacionListener() {
		Long usuId = 0L;
		Usuarios entity = null;
		try {
			usuId = Long.parseLong(txtUsuCedula.getValue().toString());
			entity = usuariosLogica.consultarPorId(usuId);
		} catch (Exception e) {
		}

		if (entity == null) {
			limpiar();

		} else {
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
			btnBorrar.setDisabled(false);

			txtUsuCedula.setValue(entity.getUsuCedula());
			txtUsuLogin.setValue(entity.getUsuLogin());
			txtUsuNombre.setValue(entity.getUsuNombre());
			txtUsuPass.setValue(entity.getUsuClave());

			somTiposUsuarios.setValue(entity.getTiposUsuarios().getTusuCodigo());

		}

	}

	private void limpiar() {
		btnCrear.setDisabled(false);
		btnModificar.setDisabled(true);
		btnBorrar.setDisabled(true);

		txtUsuLogin.resetValue();
		txtUsuNombre.resetValue();
		txtUsuPass.resetValue();
		somTiposUsuarios.resetValue();
	}

	public IUsuariosLogica getUsuariosLogica() {
		return usuariosLogica;
	}

	public void setUsuariosLogica(IUsuariosLogica usuariosLogica) {
		this.usuariosLogica = usuariosLogica;
	}

	public ITiposUsuariosLogica getTiposUsuariosLogica() {
		return tiposUsuariosLogica;
	}

	public void setTiposUsuariosLogica(ITiposUsuariosLogica tiposUsuariosLogica) {
		this.tiposUsuariosLogica = tiposUsuariosLogica;
	}

	public List<Usuarios> getLosUsuarios() {
		if (losUsuarios == null) {
			losUsuarios = usuariosLogica.consultar();
		}
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuarios> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public InputText getTxtUsuCedula() {
		return txtUsuCedula;
	}

	public void setTxtUsuCedula(InputText txtUsuCedula) {
		this.txtUsuCedula = txtUsuCedula;
	}

	public SelectOneMenu getSomTiposUsuarios() {
		return somTiposUsuarios;
	}

	public void setSomTiposUsuarios(SelectOneMenu somTiposUsuarios) {
		this.somTiposUsuarios = somTiposUsuarios;
	}

	public InputText getTxtUsuNombre() {
		return txtUsuNombre;
	}

	public void setTxtUsuNombre(InputText txtUsuNombre) {
		this.txtUsuNombre = txtUsuNombre;
	}

	public InputText getTxtUsuLogin() {
		return txtUsuLogin;
	}

	public void setTxtUsuLogin(InputText txtUsuLogin) {
		this.txtUsuLogin = txtUsuLogin;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public void setLosTiposUsuariosSelectItem(List<SelectItem> losTiposUsuariosSelectItem) {
		this.losTiposUsuariosSelectItem = losTiposUsuariosSelectItem;
	}

	public InputText getTxtUsuPass() {
		return txtUsuPass;
	}

	public void setTxtUsuPass(InputText txtUsuPass) {
		this.txtUsuPass = txtUsuPass;
	}

}
