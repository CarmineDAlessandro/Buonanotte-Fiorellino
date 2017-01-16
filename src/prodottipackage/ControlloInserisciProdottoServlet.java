package prodottipackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import utentipackage.Amministratore;



/**
 * Servlet implementation class ControlloInserisciProdottoServlet
 */
@WebServlet("/ControlloInserisciProdottoServlet")
@MultipartConfig
public class ControlloInserisciProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlloInserisciProdottoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * Questo metodo permette l'inserimento di un nuovo prodotto
	 *     
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = true;
		
		
		final Part filePart = request.getPart("img");
		if(filePart==null || "".equals(filePart.getSubmittedFileName().trim())) //verifica se l'immagine è stata inserita
			flag =false; //se non è stata inserita false
		String ext=filePart.getContentType();
		if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")||ext.equals("image/jpg"))) //verifica sull'estensione del file
			flag = false; //estensione non valida
		if(filePart.getSize()>10*1024*1024) //verifica dimensione
			flag = false; //immagine troppo grande
		path = this.getServletContext().getRealPath(""); //path in cui salvare l'immagine
		
		
		String img = "./Immagini/"+filePart.getSubmittedFileName(); //nome file da salvare
		
		
		
		
		String nome = request.getParameter("nome");
		System.out.println(nome);
		if (nome.length() > 30)
			flag = false;
		for (int i = 0; i < nome.length(); i++) {
			if (!Character.isLetter(nome.charAt(i))&& 
					!Character.isWhitespace(nome.charAt(i)) && 
					nome.charAt(i) !='\'' &&
					!Character.isDigit(nome.charAt(i))) {
				flag = false;
			}
		} request.removeAttribute("nome");
		
		String descrizione = request.getParameter("descrizione");
		if (descrizione.length() > 300)
			flag = false;
	 request.removeAttribute("descrizione");
	 

		int prezzo = Integer.parseInt(request.getParameter("prezzo"));
		if (prezzo < 0)
			flag = false;
		request.removeAttribute("prezzo");
		
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		if (quantita < 0)
			flag = false;
		request.removeAttribute("quantita");
		
		Prodotto bean = new Prodotto();
		bean.setDescrizione(descrizione);
		bean.setNome(nome);
		bean.setPrezzo(prezzo);
		bean.setQuantita(quantita);
		bean.setUrlImmagine(img);

		ProdottiManager model = new ProdottiManager();
		if (flag) {
			try {
				flag = model.aggiungiProdotto(bean);
			} catch (SQLException e) {
				/*arriva qui se non ha potuto aggiungere il prodotto*/
				response.setStatus(HttpServletResponse.
						SC_INTERNAL_SERVER_ERROR);
				RequestDispatcher dispatcher = getServletContext().
						getRequestDispatcher("/index.jsp?IdPage=8");
				dispatcher.forward(request, response);	
				return;
			}
			/*arriva qui se è andato tutto bene*/
			if(!flag) response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED); //se c'è un prodotto con lo stesso nome
			if(flag) { //se il prodotto è stato aggiunto, allora posso salvare l'immagine
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
			RequestDispatcher dispatcher = getServletContext().
					getRequestDispatcher("/index.jsp?IdPage=8");
			dispatcher.forward(request, response);
			return;
		}
		/*arriva qui se ci sono parametri formalmente scorretti*/
		response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher("/index.jsp?IdPage=8");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
