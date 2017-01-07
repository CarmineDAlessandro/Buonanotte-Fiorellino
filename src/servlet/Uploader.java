package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Uploader() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	
	     if (!ServletFileUpload.isMultipartContent(request)) {
	         throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
	     }

	     ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
	     PrintWriter writer = response.getWriter();
	   //[prova]cambiare path
	     System.out.println(new File("E:/workspace team/fiorazon/Fiorazon/WebContent/Immagini/"));
	   
	     try {
	         List<FileItem> items = uploadHandler.parseRequest(new ServletRequestContext(request));
	         
	         for (FileItem item : items) {
	             if (!item.isFormField()) {
	            	 //cambiare path
	                     File file = new File("E:/workspace team/fiorazon/Fiorazon/WebContent/Immagini/", item.getName());
	                     item.write(file);
	                     
	                     System.out.println("uploaded");
	             }
	         }
	     } catch (FileUploadException e) {
	             throw new RuntimeException(e);
	     } catch (Exception e) {
	             throw new RuntimeException(e);
	     } finally {
	        
	         writer.close();
	     }
	     
	} 

}
