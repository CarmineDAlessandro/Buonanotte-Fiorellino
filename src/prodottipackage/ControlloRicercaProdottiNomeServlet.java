package prodottipackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlloRicercaProdottiNomeServlet
 */
@WebServlet("/ControlloRicercaProdottiNomeServlet")
public class ControlloRicercaProdottiNomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlloRicercaProdottiNomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * Questo metodo permette la ricerca dei prodotti per nome
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("nome") != null) {
			String nome = request.getParameter("nome");
			request.removeAttribute("nome");

			ProdottiManager model = new ProdottiManager();
			try {
				request.setAttribute("lista", model.ricercaNome(nome));
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			/* fa il redirect sulla pagina della lista dei prodotti */
			RequestDispatcher dispatcher = getServletContext().
					getRequestDispatcher("/pages/Negozio.jsp");
			dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ElencoProdottiServlet");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
