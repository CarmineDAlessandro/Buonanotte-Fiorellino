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


@WebServlet("/EliminaOrdine")
public class EliminaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static OrdiniModel model = new OrdiniModel();
	
    public EliminaOrdine() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = null;
		//prende l' id da toglire
		String id=request.getParameter("idOrd");
		response.setContentType("text/html");  
      
    	System.out.println(id);
		try {
			if((model.eliminaOrdine(Integer.parseInt(id)))== true)
			{
				jsp = "/ElencoOrdini";
			}else{
				jsp = "/DatabaseErrore.jsp";
			}
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
