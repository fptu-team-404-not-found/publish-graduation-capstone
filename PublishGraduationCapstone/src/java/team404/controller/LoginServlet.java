package team404.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.account.AccountDAO;
import team404.account.AccountDTO;
import team404.utils.GoogleHelpers;

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

        String token = request.getParameter("token");

        try {
      
            
            GoogleHelpers googleHelper = new GoogleHelpers();
            String json = googleHelper.getUserInfo(token);
            
            AccountDTO account = googleHelper.getUserFromJson(json);

            AccountDAO accountDAO = new AccountDAO();
            String email = account.getEmail();
            boolean idExisted = accountDAO.checkEmail(email);

            if (!idExisted) {
                accountDAO.createNewAcccount(account);
            }
            int role = accountDAO.getRole(email);
            
            JSONArray jsArr = new JSONArray();
            JSONObject jsObj = new JSONObject();
            jsObj.put("email", account.getEmail());
            jsObj.put(("name"), account.getName());
            jsObj.put("picture", account.getPicture());
            jsObj.put("roleId", account.getRole().getRoleId());
            jsObj.put("token", token);
            jsArr.add(jsObj);
            JSONObject jsObjDAD = new JSONObject();
            jsObjDAD.put("information", jsArr);
            
            
            out.print(jsObjDAD);
            response.flushBuffer();
            out.flush();
//            RequestDispatcher rd = request.getRequestDispatcher("Search-BE.html");
//            rd.forward(request, response);
            
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
