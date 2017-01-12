package ordinepackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carrelloPackage.Carrello;

/**
 * Servlet implementation class ControlloPagamentoServlet
 */
@WebServlet("/ControlloPagamentoServlet")
public class ControlloPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloPagamentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**Metodo che permette di concludere il pagamente e finalizzare l'ordine
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		Ordine ordine = (Ordine) request.getSession().getAttribute("ordine");
		String iban = request.getParameter("iban");
		request.removeAttribute("iban");
		OrdineManager model = new OrdineManager();
		boolean flag = true;
		try {
			flag = model.inserisciIban(ordine, carrello, iban);
			if(flag) {
				response.setStatus(HttpServletResponse.SC_CREATED);
				request.getSession().removeAttribute("carrello");
				request.getSession().removeAttribute("ordine");
				
			}
			else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=1");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
