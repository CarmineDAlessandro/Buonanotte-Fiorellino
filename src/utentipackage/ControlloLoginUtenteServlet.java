package utentipackage;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControlloLoginUtenteServlet
 */
@WebServlet("/ControlloLoginUtenteServlet")
public class ControlloLoginUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloLoginUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo che permette il login dell'utente.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		if (request.getParameter("utente") != null) {
			
		UtentiManager model = new UtentiManager();
			
			if (user != null && password != null) {
				HttpSession session = request.getSession();
				
				try {
					
					session.setAttribute("utente", model.loginUtente(user,password));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Utente bean = (Utente) request.getSession().getAttribute("utente");
			
			if (bean.getUsername() == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				request.getSession().removeAttribute("utente");
			}
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=1");
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
