package utentipackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControlloLoginAmministratoreServlet
 */
@WebServlet("/ControlloLoginAmministratoreServlet")
public class ControlloLoginAmministratoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloLoginAmministratoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if (request.getParameter("amministratore") != null) {
		
		UtentiManager model = new UtentiManager();
			
			if (user != null && password != null) {
				HttpSession session = request.getSession();
				session.removeAttribute("amministratore");
				try {
					System.out.println(user+password);
					session.setAttribute("amministratore", model.loginAmministratore(user,password));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Amministratore bean = (Amministratore) request.getSession().getAttribute("amministratore");
			
			if (bean.getUsername() == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				request.getSession().removeAttribute("amministratore");
			}
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?IdPage=1");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
