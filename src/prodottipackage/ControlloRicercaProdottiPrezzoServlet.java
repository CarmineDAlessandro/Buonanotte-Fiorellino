package prodottipackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlloRicercaProdottiPrezzoServlet
 */
@WebServlet("/ControlloRicercaProdottiPrezzoServlet")
public class ControlloRicercaProdottiPrezzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloRicercaProdottiPrezzoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Questo metodo permette la ricerca dei prodotti per prezzo
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prezzoMin = 0, prezzoMax = 0;
		if (request.getParameter("prezzoMin") != null) {
			prezzoMin = Integer.parseInt(request.getParameter("prezzoMin"));
			request.removeAttribute("prezzoMin");
		} if (request.getParameter("prezzoMax") != null) {
			prezzoMax = Integer.parseInt(request.getParameter("prezzoMax"));
			request.removeAttribute("prezzoMax");
		}
		
		ProdottiManager model = new ProdottiManager ();
		if (prezzoMin != 0) {
			
			try {
				request.setAttribute("lista", model.ricercaNumeroMin(prezzoMin));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
			RequestDispatcher dispatcher = getServletContext().
					getRequestDispatcher("/pages/Negozio.jsp");
			dispatcher.forward(request, response);	
		} else if (prezzoMax != 0) {
			
			try {
				request.setAttribute("lista", model.ricercaNumeroMax(prezzoMax));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
			RequestDispatcher dispatcher = getServletContext().
					getRequestDispatcher("/pages/Negozio.jsp");
			dispatcher.forward(request, response);	
		} else {
			RequestDispatcher dispatcher = getServletContext().
					getRequestDispatcher("/ElencoProdottiServlet");
			dispatcher.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
