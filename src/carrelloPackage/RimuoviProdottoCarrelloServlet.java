package carrelloPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RimuoviProdottoCarrelloServlet
 */
@WebServlet("/RimuoviProdottoCarrelloServlet")
public class RimuoviProdottoCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviProdottoCarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idProdotto = request.getParameter("idProdotto");
		String idCarrelloS = request.getParameter("idCarrello");
		request.removeAttribute("idProdotto");
		request.removeAttribute("idCarrello");
		
		int idCarrello = Integer.parseInt(idCarrelloS);
		String action = request.getParameter("action");
		CarrelloManager model = new CarrelloManager();
		if(action.equals("Rimuovi")) {
			try {
				model.eliminaProdottoCarrello(idCarrello, idProdotto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = null;
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=10");
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
