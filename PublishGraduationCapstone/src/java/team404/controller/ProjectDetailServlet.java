package team404.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;

@WebServlet(name = "ProjectDetailServlet", urlPatterns = {"/ProjectDetailServlet"})
public class ProjectDetailServlet extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String projectId = request.getParameter("ProjectId");
        try {
            // count view features
            HttpSession session = request.getSession();
            ProjectDAO dao = new ProjectDAO();
            if (session.isNew()) {
                dao.updateView(projectId);
            }
            int view = dao.showView(projectId);
            ProjectDTO dto = new ProjectDTO(view);
            JSONObject jobject = new JSONObject();
            jobject.put("ViewNumber", dto.getViewNumber());

            out.print(jobject);
            response.flushBuffer();
            out.flush();
            // dùng xmlhttprequest bên servlet trả json ra FE lụm 
            // từng project sẽ là thẻ <a> truyền vào servlet và projectId
        } catch (SQLException ex) {
            log("ProjectDetailServlet   SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ProjectDetailServlet   Naming: " + ex.getMessage());
        } finally {
            out.close();
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