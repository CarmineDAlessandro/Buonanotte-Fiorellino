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

@WebServlet("/Registration")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	static UtenteModel control = new UtenteModel();
	
    public Registrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = null;
		
		Utente usr = new Utente();
		usr.setRuolo("Utente");
		usr.setNome(request.getParameter("nome"));
		usr.setCognome(request.getParameter("cognome"));
		usr.seteMail(request.getParameter("eMail"));
		usr.setCodiceFiscale(request.getParameter("codiceFiscale"));
		usr.setDataNascita(request.getParameter("dataNascita"));
		usr.setCittaNascita (request.getParameter("cittaNascita"));
		usr.setCittaResidenza(request.getParameter("cittaResidenza"));
		usr.setVia(request.getParameter("via"));
		usr.setNumeroCivico (Integer.parseInt(request.getParameter("numeroCivico")));
		usr.setCap (Integer.parseInt(request.getParameter("cap")));
		usr.setUsername(request.getParameter("username"));
		usr.setPassword(request.getParameter("password1"));
		
		try {
			if(control.controllUser(usr.getUsername()))
			{jsp = "/RegistrazioneEsistente.jsp";
			}else{
			control.InsertUser(usr);
			jsp = "/RegistrazioneSuccesso.jsp"; }
		} catch(SQLException e) {
			jsp = "/RegistrazioneFallita.jsp";
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

