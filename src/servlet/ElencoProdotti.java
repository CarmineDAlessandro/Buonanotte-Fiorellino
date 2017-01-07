package servlet;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Prodotto;
import model.ProdottiModel;

@WebServlet("/ElencoProdotti")
public  class ElencoProdotti extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
	static ProdottiModel model = new ProdottiModel();
	
    public ElencoProdotti() {
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
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
