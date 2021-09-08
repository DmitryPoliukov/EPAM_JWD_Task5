package by.epamtc.poliukov.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "LoadServlet", urlPatterns = "/load")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Collection<Part> parts = request.getParts();

        System.out.println("Request content type: " + request.getContentType());
        System.out.println("Is multipart: "
                + request.getContentType().startsWith("multipart/form-data"));
        System.out.println("# of parts: " + parts.size());

        int i = 0;
        for (Part part : parts) {
            System.out.println("Part #" + ++i + ":");
            System.out.println(part.getName());
            System.out.println(part.getSize());
            System.out.println(part.getContentType());
        }

        Part image = request.getPart("data");
        System.out.println(image.getSize());
        System.out.println(image.getContentType());



        response.sendRedirect(request.getContextPath() + "/flowers.jsp");
    }
}
