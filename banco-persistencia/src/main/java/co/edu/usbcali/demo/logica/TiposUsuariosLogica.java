package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class TiposUsuariosLogica implements ITiposUsuariosLogica {

	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;
	
	@Override
	@Transactional(readOnly = true)
	public TiposUsuarios consultarPorId(long tdocCodigo) {
		return tiposUsuariosDAO.consultarPorId(tdocCodigo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TiposUsuarios> consultar() {
		return tiposUsuariosDAO.consultar();
	}

}
