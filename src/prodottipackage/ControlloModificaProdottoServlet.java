package prodottipackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/**
 * Servlet implementation class ControlloModificaProdottoServlet
 */
@WebServlet("/ControlloModificaProdottoServlet")
@MultipartConfig

public class ControlloModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloModificaProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *	Questo metodo permette la modifica di un prodotto
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = null;
		String action = request.getParameter("action");
		if(!action.equals("img")) {
			dato = request.getParameter("dato");
		}
		if(action.equals("img")) {
			
			boolean flag = true;
			final Part filePart = request.getPart("dato");
			if(filePart==null || "".equals(filePart.getSubmittedFileName().trim())) {//verifica se l'immagine è stata inserita
				flag =false; //se non è stata inserita false
			}
			String ext=filePart.getContentType();
			if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")||ext.equals("image/jpg"))) //verifica sull'estensione del file
				flag = false; //estensione non valida
			
			if(filePart.getSize()>10*1024*1024) //verifica dimensione
				flag = false; //immagine troppo grande
			
			String path = this.getServletContext().getRealPath(""); //path in cui salvare l'immagine
			String img = "./Immagini/"+filePart.getSubmittedFileName(); //nome file da salvare
			
			if(!flag) {
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
				RequestDispatcher dispatcher = getServletContext().
						getRequestDispatcher("/index.jsp?IdPage=8");
				dispatcher.forward(request, response);
				return;
			}
			if(flag) { //se l'immagine è verificata, allora posso salvare l'immagine
				OutputStream out = null;
				InputStream filecontent = null;
				try {
					out = new FileOutputStream(new File(path + File.separator + img)); //apro lo stream sul file al percorso stabilito
					filecontent = filePart.getInputStream();
					int read = 0;
					final byte[] bytes = new byte[1024];
					while ((read = filecontent.read(bytes)) != -1) { //copio il file inserito nel file creato
						out.write(bytes, 0, read);
					}
				} 
				finally {
					if (out != null) {
						out.close();
					}
					if (filecontent != null) {
						filecontent.close();
					}
				}
			}
			dato = img;
		}
		
		
		int idProdotto = Integer.parseInt((String) request.getSession().getAttribute("id"));
		boolean flag = true;
		request.getSession().removeAttribute("id");
		request.removeAttribute("dato");
		request.removeAttribute("action");
		request.removeAttribute("id");
		
		if (dato != null && action != null && idProdotto != 0) {
			ProdottiManager model = new ProdottiManager ();
			try {
				
				flag = model.modificaProdotto(idProdotto, dato, action);
				
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.
						SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}
		if (flag == false) {
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}
		
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher("/index.jsp?IdPage=8");
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
