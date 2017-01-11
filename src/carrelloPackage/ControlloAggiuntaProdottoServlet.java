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
 * Servlet implementation class ControlloAggiuntaProdottoServlet
 */
@WebServlet("/ControlloAggiuntaProdottoServlet")
public class ControlloAggiuntaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloAggiuntaProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String idProdottoS = request.getParameter("id");
		int idProdotto = Integer.parseInt(idProdottoS);
		String quantit‡S = request.getParameter("quantit‡");
		int quantit‡ = Integer.parseInt(quantit‡S);
		request.removeAttribute("username");
		request.removeAttribute("id");
		request.removeAttribute("quantit‡");
		CarrelloManager model = new CarrelloManager();
		try {
			boolean flag=model.aggiungiProdottoCarrello(username, idProdotto, quantit‡);
			if(!flag) {
				
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = null;
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=8");
		
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
