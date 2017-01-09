package utentipackage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControlloRegistrazioneServlet
 */
@WebServlet("/ControlloRegistrazioneServlet")
public class ControlloRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloRegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo che permette la registrazione di un utente.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag = true;
		String nome = request.getParameter("nome");
		if (nome.length() > 30) flag = false;
		for (int i=0; i < nome.length(); i ++) {
			if(!Character.isLetter(nome.charAt(i))) {
				flag = false; 
				
			}
		}
		String cognome = request.getParameter("cognome");
		if (cognome.length() > 30) flag = false;
		for (int i=0; i < cognome.length(); i ++) {
			if(!Character.isLetter(cognome.charAt(i))) {
				flag = false;
				
			}
		}
		String cf = request.getParameter("cf");
		if(cf.length() != 16) flag = false;
		for (int i=0; i < cf.length(); i ++) {
			if(!Character.isLetterOrDigit(cf.charAt(i))) {
				flag = false;
				
			}
		}
		String data = request.getParameter("data");
		String città_nascita = request.getParameter("cittàN");
		if(città_nascita.length() > 40) flag = false;
		for (int i=0; i < città_nascita.length(); i ++) {
			if(!Character.isLetter(città_nascita.charAt(i))) {
				flag = false;
				
			}
		}
		String città_residenza = request.getParameter("cittàR");
		if(città_residenza.length() > 40) flag = false;
		for (int i=0; i < città_residenza.length(); i ++) {
			if(!Character.isLetter(città_residenza.charAt(i))) {
				flag = false;
			}
		}
		String cap = request.getParameter("cap");
		if(cap.length() != 5) flag = false;
		for (int i=0; i < cap.length(); i ++) {
			if(!Character.isDigit(cap.charAt(i))) {
				flag = false;
			}
		}
		String email = request.getParameter("email");
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!email.matches(emailPattern)) flag = false;
		if(email.length()>30) flag = false;
		String provincia = request.getParameter("provincia");
		if(provincia.length() != 2) flag = false;
		for (int i=0; i < provincia.length(); i ++) {
			if(!Character.isLetter(provincia.charAt(i))) {
				flag = false;
			}
		}
		String via = request.getParameter("via");
		if(via.length() > 30) flag = false;
		for (int i=0; i < via.length(); i ++) {
			if(!Character.isLetterOrDigit(via.charAt(i)) && !Character.isWhitespace(via.charAt(i))) {
				flag = false;
			}
		}
		String numeroCivico = request.getParameter("numeroCivico");
		for (int i=0; i < numeroCivico.length(); i ++) {
			if(!Character.isDigit(numeroCivico.charAt(i))) {
				flag = false;
			}
		}
		int nc = 0; 
		String password = request.getParameter("password");
		if(password.length() > 30) flag = false;
		String username = request.getParameter("username");
		if(username.length() > 30) flag = false;
		if(flag) nc = Integer.parseInt(numeroCivico);
	
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date data_nascita = null;
		try {
			data_nascita = df.parse(data);
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		Utente bean = new Utente();
		bean.setCap(cap);
		bean.setCittaDiNascita(città_nascita);
		bean.setCittaResidenza(città_residenza);
		bean.setCodiceFiscale(cf);
		bean.setCognome(cognome);
		bean.setDataDiNascita(data_nascita);
		bean.seteMail(email);
		bean.setNome(nome);
		bean.setNumeroCivico(nc);
		bean.setPassword(password);
		bean.setProvincia(provincia);
		bean.setUsername(username);
		bean.setVia(via);
		
		
		UtentiManager model = new UtentiManager();
		if(flag) {
			boolean flag2 = false;
			try {
				flag2 = model.registrazioneUtente(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.removeAttribute("utente");
			RequestDispatcher dispatcher = null;
			if(flag2)
			dispatcher = getServletContext().getRequestDispatcher("/pages/AvvenutaRegistrazione.jsp");
			else
			dispatcher = getServletContext().getRequestDispatcher("/pages/RegistrazioneFallita.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			request.removeAttribute("utente");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/RegistrazioneFallita.jsp");
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
