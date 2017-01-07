package servlet;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottiModel;

@WebServlet("/EliminaProdotto")
public class EliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottiModel model = new ProdottiModel();
	
    public EliminaProdotto() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = null;
		String id=request.getParameter("idP");
		String url=request.getParameter("urlP");
		String path ="E:/workspace team/fiorazon/Fiorazon/WebContent/Immagini";
		response.setContentType("text/html");  
		int index =0;
		
		//trova url
		
		index =url.lastIndexOf("/");
		if( index  >= 0 )
		{
		String estensione = url.substring(index);
		path = path+estensione;
		//elimina file
		File a = new File(path);
		a.delete(); 
		}
		try {
			
			if((model.eliminaProdotto(id))== true)
			{
				
				
				jsp = "/EliminazionePSuc.jsp";
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
