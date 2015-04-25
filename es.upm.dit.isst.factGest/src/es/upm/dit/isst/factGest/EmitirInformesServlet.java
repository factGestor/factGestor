package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.FacturacionDAO;
import es.upm.dit.isst.factGest.dao.FacturacionDAOImpl;
import es.upm.dit.isst.factGest.dao.PaisDAO;
import es.upm.dit.isst.factGest.dao.PaisDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Facturacion;
import es.upm.dit.isst.factGest.model.Pais;
import es.upm.dit.isst.factGest.model.Usuario;

public class EmitirInformesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario u = (Usuario) misession.getAttribute("u");
		Long idUsuario = u.getId();
		FacturacionDAO daoFactura = FacturacionDAOImpl.getInstance();
		String opcion = req.getParameter("tarifa");
		
		if(opcion=="fechaCon"){
			
			Date fecha = new Date();
			
			String dia = req.getParameter("dia");
			String mes = req.getParameter("mes");
			String ano = req.getParameter("ano");
			String fechaCompleta = ano+"/"+mes+"/"+dia;
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			try {
				fecha = formatter.parse(fechaCompleta);
				List<Facturacion> facturas=daoFactura.getFacturasFecha(idUsuario,fecha);
				req.getSession().setAttribute("facturas", facturas);
				RequestDispatcher view = req.getRequestDispatcher("emitirInforme.jsp");
				view.forward(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(opcion=="Pais"){
			PaisDAO daoPais = PaisDAOImpl.getInstance();
			String paisS = req.getParameter("pais");
			Pais pais = daoPais.getPaisByName(paisS);
			Long paisId=pais.getId();
			List<Facturacion> facturas = daoFactura.getFacturasPais(idUsuario,paisId);
			req.getSession().setAttribute("facturas", facturas);
			RequestDispatcher view = req.getRequestDispatcher("emitirInforme.jsp");
			view.forward(req, resp);
		}
		else{
			DominioDAO daoDominio = DominioDAOImpl.getInstance();
			String dominioS = req.getParameter("dominio");
			Dominio dominio = daoDominio.getDominioByName(dominioS);
			Long dominioId=dominio.getId();
			List<Facturacion> facturas=daoFactura.getFacturasDominio(idUsuario,dominioId);
			req.getSession().setAttribute("facturas", facturas);
			RequestDispatcher view = req.getRequestDispatcher("emitirInforme.jsp");
			view.forward(req, resp);
		}
	}
}
