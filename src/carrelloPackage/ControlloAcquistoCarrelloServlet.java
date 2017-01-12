package carrelloPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ordinepackage.Ordine;
import ordinepackage.OrdineManager;
import utentipackage.Utente;

/**
 * Servlet implementation class ControlloAcquistoCarrelloServlet
 */
@WebServlet("/ControlloAcquistoCarrelloServlet")
public class ControlloAcquistoCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloAcquistoCarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**Metodo che permette l'acquisto del carrello.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		OrdineManager model = new OrdineManager();
		
		try {
			Ordine ordine = model.creaOrdine(carrello,utente);
			request.getSession().setAttribute("ordine", ordine);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = null;
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=11");
		
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
