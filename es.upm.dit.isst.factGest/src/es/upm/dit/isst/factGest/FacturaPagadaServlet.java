package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.FacturacionDAO;
import es.upm.dit.isst.factGest.dao.FacturacionDAOImpl;
import es.upm.dit.isst.factGest.dao.PaisDAO;
import es.upm.dit.isst.factGest.dao.PaisDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Facturacion.TipoIVA;
import es.upm.dit.isst.factGest.model.Pais;

public class FacturaPagadaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String errores = "";
		Boolean guardado = true;

		String dominio = req.getParameter("domain");
		double iva = 0;
		TipoIVA tipo = TipoIVA.iva;
		try {
			iva = Double.parseDouble(req.getParameter("iva"));
			tipo = TipoIVA.valueOf(req.getParameter("tipoIva"));
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				errores = errores + "Tipo de IVA incorrecto. ";
			} else if (e instanceof NumberFormatException) {
				errores = errores + "IVA incorrecto. ";
			} else {
				errores = errores + "Campos nulos. ";
			}
			guardado = false;
		}
		String nFactura = req.getParameter("nfactura");
		if (nFactura == null) {
			errores = errores + "Numero de factura nulo";
			guardado = false;
		}
		String codigoPais = req.getParameter("pais");
		Long paisID = 1L;

		if (codigoPais != null) {
			PaisDAO daoPais = PaisDAOImpl.getInstance();
			Pais pais = daoPais.getPaisByCode(codigoPais);
			if (pais != null) {
				paisID = pais.getId();
			} else {
				errores = errores + "Pais no encontrado en la base de datos. ";
				guardado = false;
			}
		} else {
			errores = errores + "Codigo de pais nulo. ";
			guardado = false;
		}

		if (guardado) {
			if (dominio != null) {
				DominioDAO daoDominio = DominioDAOImpl.getInstance();
				Dominio dom = daoDominio.getDominioByName(dominio);
				if (dom != null) {
					FacturacionDAO daoFacturacion = FacturacionDAOImpl
							.getInstance();
					daoFacturacion.add(dom.getUserId(), dominio, iva, paisID,
							nFactura, tipo);
				} else {
					errores = errores
							+ "No existe dominio en la base de datos. ";
					guardado = false;
				}
			}
		} else {
			errores = errores + "Dominio nulo. ";
			guardado = false;
		}

		JSONObject json = new JSONObject();
		try {
			json.put("permitido", guardado);
			json.put("errores", errores);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		resp.getWriter().write(json.toString());
	}
}
