package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.PaisDAO;
import es.upm.dit.isst.factGest.dao.PaisDAOImpl;
import es.upm.dit.isst.factGest.model.Pais;

public class CargarPaisesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PaisDAO pdao = PaisDAOImpl.getInstance();

		List<Pais> lpaises = pdao.getPaises();

		if (lpaises.isEmpty()) {
			for(int p =0; p<paises.length;p++){
				pdao.add(paises[p][0], paises[p][1]);
			}
		}
	}

	static String paises[][] = { { "España", "spain" },
			{ "Francia", "france" }, { "Austria", "austria" },
			{ "Italia", "italy" }, { "Alemania", "germany" },
			{ "Belgica", "belgium" }, { "Paises Bajos", "netherlands" },
			{ "Dinamarca", "denmark" }, { "Irlanda", "ireland" },
			{ "Luxemburgo", "luxembourg" }, { "Grecia", "greece" },
			{ "Portugal", "portugal" }, { "Rumania", "romania" },
			{ "Estonia", "estonia" }, { "Letonia", "latvia" },
			{ "Lituania", "lithuania" }, { "Hungria", "hungary" },
			{ "Croacia", "croatia" }, { "Republica Checa", "czechrepublic" },
			{ "Finlandia", "finland" }, { "Malta", "malta" },
			{ "Polonia", "poland" }, { "Eslovaquia", "slovakia" },
			{ "Eslovenia", "slovenia" }, { "Suecia", "sweeden" },
			{ "Reino Unido", "unitedkingdom" } };
}
