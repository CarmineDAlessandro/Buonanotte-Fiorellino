package utentipackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlloModificaDatiUtentiServlet
 */
@WebServlet("/ControlloModificaDatiUtentiServlet")
public class ControlloModificaDatiUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloModificaDatiUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Modifica un dato a scelta dell'utente.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dato = request.getParameter("dato");
		String action = request.getParameter("action");
		Utente usr = (Utente) request.getSession().getAttribute("utente");
		String username = usr.getUsername();
		request.removeAttribute("dato");
		request.removeAttribute("action");
		request.removeAttribute("utente");
		UtentiManager model = new UtentiManager();
		
		if( dato != null && action != null && username != null) {
			try {
				boolean flag = model.ModificaUtente(username, dato, action);
				if(flag) {
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
					request.getSession().removeAttribute("utente");
					
				}
				else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=1");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
