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
 * Servlet implementation class ControlloModificaProdottoServlet
 */
@WebServlet("/ControlloModificaProdottoServlet")
public class ControlloModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloModificaProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *	Questo metodo permette la modifica di un prodotto
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("dato");
		String action = request.getParameter("action");
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		boolean flag = true;
		
		request.removeAttribute("dato");
		request.removeAttribute("action");
		request.removeAttribute("idProdotto");
		
		if (dato != null && action != null && idProdotto != 0) {
			ProdottiManager model = new ProdottiManager ();
			try {
				
				flag = model.modificaProdotto(idProdotto, dato, action);
				
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.
						SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}
		if (flag == false) {
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}
		
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher("/pages/Negozio.jsp");
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
