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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String cf = request.getParameter("cf");
		String data = request.getParameter("data");
		String citt‡_nascita = request.getParameter("citt‡N");
		String citt‡_residenza = request.getParameter("citt‡R");
		String cap = request.getParameter("cap");
		String email = request.getParameter("email");
		String provincia = request.getParameter("provincia");
		String via = request.getParameter("via");
		String numeroCivico = request.getParameter("numeroCivico");
		int nc = Integer.parseInt(numeroCivico);
		String password = request.getParameter("password");
		String username = request.getParameter("username");
	
	
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
		bean.setCittaDiNascita(citt‡_nascita);
		bean.setCittaResidenza(citt‡_residenza);
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
		try {
			model.registrazioneUtente(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.removeAttribute("utente");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/AvvenutaRegistrazione.jsp");
		
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
