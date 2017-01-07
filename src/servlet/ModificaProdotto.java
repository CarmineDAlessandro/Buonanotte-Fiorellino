package servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Prodotto;
import model.ProdottiModel;


@WebServlet("/ModificaP")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottiModel model = new ProdottiModel();
	Prodotto usr = new Prodotto();
	String jsp = null;
    public ModificaProdotto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 jsp = null;
		
		usr.setNome(request.getParameter("nome"));
		usr.setDescrizione(request.getParameter("Descrizione"));
		usr.setIdProdotto(Integer.parseInt(request.getParameter("idProdotto")));
		usr.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		usr.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
		
			try {
				model.modificaProdotto(usr.getIdProdotto(), usr);
				jsp ="/ModificaProdottoS.jsp";
			} catch (SQLException e) {
				e.getMessage();
				jsp = "/DatabaseErrore.jsp";	
			}
			
	
			
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}