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
@WebServlet("/ControlloRicercaProdottiServlet")
public class ControlloRicercaProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloRicercaProdottiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Questo metodo permette la ricerca dei prodotti per prezzo
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		request.removeAttribute("action");
		if(action.equals("prezzo")) {
		int prezzoMin = -1, prezzoMax = -1;
		if (request.getParameter("prezzoMin") != null) {
			prezzoMin = Integer.parseInt(request.getParameter("prezzoMin"));
			request.removeAttribute("prezzoMin");
		} if (request.getParameter("prezzoMax") != null) {
			prezzoMax = Integer.parseInt(request.getParameter("prezzoMax"));
			request.removeAttribute("prezzoMax");
		}
		
		ProdottiManager model = new ProdottiManager ();
		if (prezzoMin != -1) {
			
			try {
				request.setAttribute("lista", model.ricercaNumeroMin(prezzoMin));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
			
		} else if (prezzoMax != -1) {
			
			try {
				request.setAttribute("lista", model.ricercaNumeroMax(prezzoMax));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
				
		} 
		}
		
		if(action.equals("nome")) {
			String nome = request.getParameter("nome");
			request.removeAttribute("nome");
			
			ProdottiManager model = new ProdottiManager();
			try {
				
				request.setAttribute("lista", model.ricercaNome(nome));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			/* fa il redirect sulla pagina della lista dei prodotti */
			
		}
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher("/index.jsp?IdPage=8");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
