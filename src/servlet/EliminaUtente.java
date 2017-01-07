package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteModel;

@WebServlet("/EliminaUtente")
public class EliminaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UtenteModel model = new UtenteModel();
	
    public EliminaUtente() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = null;
		
		response.setContentType("text/html");  
        String username = (String) request.getSession().getAttribute("username");
    	
		try {
			if((model.eliminaUtente(username))== true)
			{
				request.getSession().removeAttribute("username");
				request.getSession().invalidate();
				
				
				jsp = "/EliminazioneUtenteSuc.jsp";
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
