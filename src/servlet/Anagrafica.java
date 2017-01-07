package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Utente;
import model.UtenteModel;

@WebServlet("/Anagrafica")
public class Anagrafica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UtenteModel model = new UtenteModel();
       
    public Anagrafica() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = null;
		Utente usr = new Utente();
		
		String username = (String) request.getSession().getAttribute("username");
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		try {
			usr = model.returnInfo(username);
			
			if("amministratore".equals(ruolo) ){
				jsp = "/AnagraficaAmministratore.jsp";
					request.getSession().setAttribute("Dati", usr);
			}else{
					if("utente".equals(ruolo) ){
				jsp = "/AnagraficaUtente.jsp";
				request.getSession().setAttribute("Dati", usr);
					
			} else {
				jsp = "/Login.jsp";
					}
			}
		} catch(SQLException e) {
			jsp = "/DatabaseErrore.jsp";
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

