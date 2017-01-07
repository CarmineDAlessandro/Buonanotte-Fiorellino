package servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Ordine;
import model.OrdiniModel;

@WebServlet("/ElencoOrdini")
public class ElencoOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static OrdiniModel model = new OrdiniModel();
	
    public ElencoOrdini() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = null,ruolo = null;
		ArrayList<Ordine> elenco = new ArrayList<Ordine>();
		
		try {
			ruolo = (String) request.getSession().getAttribute("ruolo");
			String username = (String) request.getSession().getAttribute("username");
			
			
			if("utente".equals(ruolo))
			{
				elenco = model.returnOrdiniUtente(username);
				
				jsp = "/OrdineCliente.jsp";
			}else
			{
				jsp = "/OrdineAmministratore.jsp";
				elenco = model.returnOrdini();
			}
	
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

