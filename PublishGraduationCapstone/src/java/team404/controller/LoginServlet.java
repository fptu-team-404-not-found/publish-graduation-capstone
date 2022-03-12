package team404.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.utils.GoogleHelpers;
import team404.utils.MyApplicationConstants;

public class LoginServlet extends HttpServlet {

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

        String code = request.getParameter("code");
        
        try {
            if (code == null || code.isEmpty()) {
            } else {
                HttpSession session = request.getSession();

                GoogleHelpers googleHelper = new GoogleHelpers();
                String accessToken = googleHelper.getToken(code);
                String userJSON = googleHelper.getUserInfo(accessToken);
                AccountDTO accountDTO = googleHelper.getUserFromJson(userJSON);

                AccountDAO accountDAO = new AccountDAO();
                String email = accountDTO.getEmail();
                boolean idExisted = accountDAO.checkEmail(email);

                if (!idExisted) {
                    accountDAO.createNewAcccount(accountDTO);
                }
                Cookie cookie = new Cookie("token", accessToken);
                cookie.setMaxAge(60*60);
                response.addCookie(cookie);

                int role = accountDAO.getRole(email);
                if(role == 1 || role == 2){
                    response.sendRedirect(MyApplicationConstants.GoogleLoginFeatures.HOME_PAGE);
                }
                if(role == 3){
                    response.sendRedirect(MyApplicationConstants.GoogleLoginFeatures.POST_MODE_PAGE);
                }
                if(role == 4){
                    response.sendRedirect(MyApplicationConstants.GoogleLoginFeatures.ADMIN_MODE_PAGE);
                }
            }
        } catch (IOException ex) {
            log("LoginServlet_IO: " + ex.getMessage());
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
