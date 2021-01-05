
package lab5Algorithms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lab5Algorithms.model.HistoryData;
import lab5Algorithms.model.Node;

/**
 * A class that stores the history of adding nodes and printing it
 *
 * @author Emilia Gosiewska version 5.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

    /**
     * An empty contructor
     */
    HistoryServlet() {

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> The history: </h1>");
            EntityManager em = Persistence.createEntityManagerFactory("DijkstraBellman").createEntityManager();
            HttpSession session = request.getSession();


            List<HistoryData> hist;
            out.println("<table border=\"1\" cellspacing=\"2\"cellpadding=\"2\">");
            out.println("<thead><tr>");
            out.println("<td> Id </td>");
            out.println("<td> source node </td>");
            out.println("<td> Algorithm </td>");
            out.println("<td> distance</td>");
            out.println("</thead></tr>");
            PrintWriter writer = response.getWriter();
            try{
            hist = em.createQuery("SELECT s FROM HistoryData s", HistoryData.class).getResultList();

            for (HistoryData o : hist) {
                writer.println("<p></p>");
                writer.println("</td><td>" + o.getId() + "</td><td>" + o.getSourceNode() + "</td><td>" + o.getAlgorithm() + "</td><td>" + o.getDistanceValue() + "<br" + "</td><tr>");
            }
            out.println("</body>");
            out.println("</html>");


            List<String> history = (List<String>) request.getSession().getAttribute("show history");

            List<List<Node>> adjList = (List<List<Node>>) session.getAttribute("adjList");

        } catch(Exception ex)
        {
            out.println("Cannot print the query");
            return;
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
