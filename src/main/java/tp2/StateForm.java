/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;
/**
 *
 * @author hbroucke
 */
@WebServlet(name = "StateForm", urlPatterns = {"/StateForm"})
public class StateForm extends HttpServlet {

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
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        String state = request.getParameter("state");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StateForm " + request.getContextPath() + "</h1>");
            try{
               // out.println("<form action='State'>");
                out.println("<select>");
                for(CustomerEntity e:dao.customersInState(state)){
                out.println("<option value="+e.>State</option>");
                }

                for(String s : dao.customersInState()){
                    out.println("<option value="+s+">"+s+"</option>");
                }
                out.println("</select>");
                out.println("<input type='submit'>");
                out.println("</form>");
            }
            catch(Exception ex){
                out.println("<p>"+ex+"</p>");
            }
            
            if(!state.isEmpty()){
                state=state.toUpperCase();
                out.println("<p>Les clients en "+state+"</p>");
                try{
                    out.println("<table border='1'>");
                    out.println("<tr><th>ID</th>\n<th>Name</th>\n<th>Address</th></tr>");
                    for(CustomerEntity e:dao.customersInState(state)){
                        out.println("<tr><td>"+e.getCustomerId()+"</td>");
                        out.println("<td>"+e.getName()+"</td>");
                        out.println("<td>"+e.getAddressLine1()+"</td></tr>");
                    }
                    out.println("</table>");
                }
                catch (DAOException ex) {
                    out.println("<p>"+ex+"</p>");
                }
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
