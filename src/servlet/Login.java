package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteModel;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UtenteModel model = new UtenteModel();
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().getAttribute("username");
		request.getSession().getAttribute("ruolo");
		
		String jsp = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			String ruolo = model.verifyAccess(username, password);
			if("amministratore".equals(ruolo)) {	
				jsp="/HomeAmministratore.jsp";
				request.removeAttribute("username");
				request.getSession().setAttribute("username", username);
				request.removeAttribute("ruolo");
				request.getSession().setAttribute("ruolo", ruolo);
			} else if("utente".equals(ruolo)) {
				jsp="/HomeUtenteLog.jsp";
				request.removeAttribute("username");
				request.getSession().setAttribute("username", username);
				request.removeAttribute("ruolo");
				request.getSession().setAttribute("ruolo", ruolo);
			} else {
				jsp="/LoginErrore.jsp";
				request.removeAttribute("username");
				request.getSession().setAttribute("username", null);
				request.removeAttribute("ruolo");
				request.getSession().setAttribute("ruolo", null);
			} 
		} catch (SQLException e) {
			e.getMessage();
			jsp = "/DatabaseErrore.jsp";	
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
