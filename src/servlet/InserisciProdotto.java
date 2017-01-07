package servlet;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Prodotto;
import model.ProdottiModel;


@WebServlet(name = "/InserisciP", urlPatterns = { "/InserisciP" }, initParams = {@WebInitParam(name = "file-upload", value = "tmpDir") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,  maxRequestSize = 1024 * 1024 * 50) 
public class InserisciProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottiModel model = new ProdottiModel();
	static String SAVE_DIR = "";
	Prodotto prd = new Prodotto();
	String Url = null,jsp = null;
	String rinomina= null ;
	
	public void init() {
		// Get the file location where it would be stored
		SAVE_DIR = getServletConfig().getInitParameter("file-upload");
	}
	
    public InserisciProdotto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		/************
		 * immagine *
		 ************/
		
		response.setContentType("text/plain");
		//cambiare path
		String savePath = "E:/workspace team/fiorazon/Fiorazon/WebContent/Immagini/";
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			if (fileName != null && !fileName.equals("")) {
				rinomina = request.getParameter("nome")+extractEstenzione(fileName);
				part.write(savePath + rinomina);
				
				Url  = null;
				Url="./Immagini/"+rinomina;
			}
		}
		
		
		/************
		 * database *
		 ************/
		
		prd.setUrlImmagine(Url);
		prd.setNome(request.getParameter("nome"));
		prd.setDescrizione(request.getParameter("descrizione"));
		prd.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		prd.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
			
		
		
		
		try {
			model.InsertProdotto(prd);
			jsp = "/InserimentoPSuccesso.jsp";
		} catch(SQLException e) {
			jsp = "/DatabaseErrore.jsp";
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
		/************
		 * funzione *
		 ************/
	private String extractFileName(Part part) {
		//content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	//funzione ritorna stringa
	private String extractEstenzione(String nomeFile){
		int index = nomeFile.indexOf(".");
		String estensione = nomeFile.substring(index);
		return estensione;
	}
}

