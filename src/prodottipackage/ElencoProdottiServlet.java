package prodottipackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ElencoProdottiServlet")
public class ElencoProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static ProdottiManager model = new ProdottiManager();
	
    public ElencoProdottiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = null;
		ArrayList<Prodotto> elenco = new ArrayList<Prodotto>();
		
		try {
			elenco = model.returnProdotti();
			jsp = "/Catalogo.jsp";
			request.getSession().setAttribute("Elenco", elenco);
		} catch(SQLException e) {
			jsp = "/DatabaseErrore.jsp";
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
