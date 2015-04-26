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

		HttpSession misession = (HttpSession) req.getSession();
		Usuario u = (Usuario) misession.getAttribute("u");
		Long idUsuario = u.getId();
		FacturacionDAO daoFactura = FacturacionDAOImpl.getInstance();
		DominioDAO daoDominio = DominioDAOImpl.getInstance();
		PaisDAO daoPais = PaisDAOImpl.getInstance();
		String opcion = req.getParameter("tarifa");

		System.out.println(opcion);

		if (opcion.equals("FechaCon")) {
			String dia = req.getParameter("dia");
			String mes = req.getParameter("mes");
			String ano = req.getParameter("ano");
			String fechaCompleta = ano + "-" + mes + "-" + dia;

			try {
				System.out.println(fechaCompleta);
				Date fecha = Facturacion.sdf.parse(fechaCompleta);
				System.out.println(Facturacion.sdf.format(fecha));
				List<Facturacion> facturas = daoFactura.getFacturasFecha(
						idUsuario, fecha);
				for (Facturacion factura : facturas) {
					factura.setDominio(daoDominio.getDominio(
							factura.getDomain()).getDomain());
					factura.setPais(daoPais.getPais(factura.getPaisId())
							.getName());
				}
				req.getSession().removeAttribute("facturas");
				req.getSession().setAttribute("facturas", facturas);
				RequestDispatcher view = req
						.getRequestDispatcher("emitirInforme.jsp");
				view.forward(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (opcion.equals("Pais")) {
			String paisS = req.getParameter("pais");
			Pais pais = daoPais.getPaisByCode(paisS.toString());
			Long paisId = pais.getId();
			List<Facturacion> facturas = daoFactura.getFacturasPais(idUsuario,
					paisId);
			for (Facturacion factura : facturas) {
				factura.setDominio(daoDominio.getDominio(factura.getDomain())
						.getDomain());
				factura.setPais(daoPais.getPais(factura.getPaisId()).getName());
			}
			req.getSession().removeAttribute("facturas");
			req.getSession().setAttribute("facturas", facturas);
			RequestDispatcher view = req
					.getRequestDispatcher("emitirInforme.jsp");
			view.forward(req, resp);
		}
		
		if(opcion.equals("Dominio")) {
			System.out.println("entrodominio");
			String dominioS = req.getParameter("dominio");
			Dominio dominio = daoDominio.getDominioByName(dominioS);
			Long dominioId = dominio.getId();
			List<Facturacion> facturas = daoFactura.getFacturasDominio(
					idUsuario, dominioId);
			for (Facturacion factura : facturas) {
				factura.setDominio(daoDominio.getDominio(factura.getDomain())
						.getDomain());
				factura.setPais(daoPais.getPais(factura.getPaisId()).getName());
			}
			req.getSession().removeAttribute("facturas");
			req.getSession().setAttribute("facturas", facturas);
			RequestDispatcher view = req
					.getRequestDispatcher("emitirInforme.jsp");
			view.forward(req, resp);
		}
	}
}
