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


@WebServlet("/ModificaUtente2")
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UtenteModel model = new UtenteModel();
	static UtenteModel account = new UtenteModel();
	static UtenteModel control = new UtenteModel();
	static UtenteModel login = new UtenteModel();
	
    public ModificaUtente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = null;
		String user = (String) request.getSession().getAttribute("username");
		
		Utente usr = new Utente();
		usr.setRuolo(request.getParameter("ruolo"));
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
			if(user.equals(usr.getUsername()))
			{
				//non modifica username
				
				model.ModificaUtente(usr,user);
				jsp = "/ModificaUtenteS.jsp";
			}else{
			if(control.controllUser(usr.getUsername()))
			{
				
				//controlla se l' username e stato cambiato con uno esistente
				jsp = "/ModificaUtenteUserE.jsp";
			}else{
				//caso cambiato username con uno non esistene
				
			account.ModificaAccount(usr,user);
			//effettua logout
			request.getSession().removeAttribute("username");
			request.getSession().invalidate();
			//effettua login
			request.getSession().getAttribute("username");
			request.getSession().getAttribute("ruolo");
			try {
				String ruolo = login.verifyAccess(usr.getUsername(), usr.getPassword());
				if("amministratore".equals(ruolo)) {	
					request.removeAttribute("username");
					request.getSession().setAttribute("username", usr.getUsername());
					request.removeAttribute("ruolo");
					request.getSession().setAttribute("ruolo", ruolo);
				} else if("utente".equals(ruolo)) {
					request.removeAttribute("username");
					request.getSession().setAttribute("username", usr.getUsername());
					request.removeAttribute("ruolo");
					request.getSession().setAttribute("ruolo", ruolo);
				} else {
					request.removeAttribute("username");
					request.getSession().setAttribute("username", null);
					request.removeAttribute("ruolo");
					request.getSession().setAttribute("ruolo", null);
				} 
			} catch (SQLException e) {
				e.getMessage();
				jsp = "/DatabaseErrore.jsp";	
			}
			
			jsp = "/ModificaUtenteS.jsp"; }}
		} catch(SQLException e) {
			jsp = "/ModificaUtenteE.jsp";
		}
			
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}