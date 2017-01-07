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

@WebServlet("/modificapinit")
public class ModificaProdottoInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProdottiModel model = new ProdottiModel();
       
    public ModificaProdottoInit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = null,idS = null;
		Prodotto usr = new Prodotto();
		
		 idS = (String) request.getParameter("idP");
		 int id= Integer.parseInt(idS);
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		try {
			usr = model.returnInfo(id);
			
			
			if("amministratore".equals(ruolo) ){
				jsp = "/ModificaProdotto.jsp";
					request.getSession().setAttribute("Dati", usr);
			}else{
					if("utente".equals(ruolo) ){
				jsp = "/HomeUtenteLog.jsp";
					
			} else {
				jsp = "/Login.jsp";
					}
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
