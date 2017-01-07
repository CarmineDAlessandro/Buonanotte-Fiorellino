package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdiniModel;

@WebServlet("/AvanzaStato")
public class AvanzaStato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static OrdiniModel model = new OrdiniModel();
       
    public AvanzaStato() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = null;
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		
		String stato = request.getParameter("stato");
		int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
		try {
			if("amministratore".equals(ruolo)){
			model.avanzaStato(stato, idOrdine);
			jsp="/ElencoOrdini";
			}
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
