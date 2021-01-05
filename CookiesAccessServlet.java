
package lab5Algorithms.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.InetAddress;
import javax.servlet.http.Cookie;

/**
 * A class that stores the cookies- the login date of the user and his IP address
 *
 * @author Emilia Gosiewska
 * @version 4.0
 */
public class CookiesAccessServlet extends HttpServlet {
    /**
     * an empty contructor
     */
    CookiesAccessServlet()
    {
        
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request request of the servlet
     * @param response response of the servlet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Your cookies</title>");
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            String lastVisit = "never";
            InetAddress ip = InetAddress.getLocalHost();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("lastVisit")) {
                        lastVisit = cookie.getValue();
                        break;
                    }
                }
            }
            out.println("Your last visit was " + lastVisit);
            out.println("Your IP address: " + ip.getHostAddress());
            out.println("</body>");
            out.println("</html>");

            Cookie cookie = new Cookie("lastVisit", new java.util.Date().toString());
            response.addCookie(cookie);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
