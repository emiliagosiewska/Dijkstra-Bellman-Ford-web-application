
package lab5Algorithms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lab5Algorithms.model.Node;
import lab5Algorithms.model.NegativeNodeException;
import lab5Algorithms.model.Data;
import lab5Algorithms.model.HistoryData;
import lab5Algorithms.model.Model;

/**
 * Class that calls the dijkstra algorithm and takes the source node from the
 * user
 *
 * @author Emilia Gosiewska version 5.0
 */
@WebServlet(name = "Dijkstra", urlPatterns = {"/Dijkstra"})
public class DijkstraServlet extends HttpServlet {

    /**
     * An empty contructor
     */
    DijkstraServlet() {
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dijkstra algorithm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Result of Dijkstra algorithm: </h1>");
            out.println("</body>");
            out.println("</html>");

            int source = Integer.parseInt(request.getParameter("sourceNode"));
            if (source < 0) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Source should be greater than 0!");
                return;
            } else {
                out.println("source equals:" + source + "</p>");
            }
            HttpSession session = request.getSession();
            session.setAttribute("source", source);
            List<Integer> distance = new ArrayList<>();

            List<List<Node>> adjList = (List<List<Node>>) session.getAttribute("adjList");

            Model model = new Model(adjList);
            try {
                distance = model.algoDijkstra(source);
            } catch (NegativeNodeException ex) {
                response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Negative cycle has occured!");
                return;
            }

            out.println("The path using Dijkstra algorithm:");
            for (int i = 0; i < distance.size(); i++) {
                if (distance.get(i) != Integer.MAX_VALUE) {
                    out.printf("%d: %d \n", i, distance.get(i));
                }
            }
            EntityManager em = Persistence.createEntityManagerFactory("DijkstraBellman").createEntityManager();

            try {

                Data data = new Data();
                HistoryData historyData = new HistoryData();
                data.setId(historyData.getId());
                em.getTransaction().begin();
                em.persist(data);
                historyData.setData(data);
                historyData.setSourceNode(source);
                historyData.setAlgorithm("Dijkstra");
                historyData.setDistanceValue(distance.toString().replaceAll("2147483647", ""));
                em.persist(historyData);
                em.getTransaction().commit();
                out.println("Dijkstra was added");

            } catch (Exception e) {
                em.getTransaction().rollback();
            } finally {
                em.close();
            }

            out.println("</body>");
            out.println("</html>");
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
        PrintWriter writer = response.getWriter();
        processRequest(request, response);

//        List<List<Node>> adjList = new LinkedList<>();
//        int source = 0;
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

    /**
     * A method that is responsible for printing the distance between nodes for
     * all two algorithms using Stream()
     *
     * @param distance is a variable that handles distances
     */
}
