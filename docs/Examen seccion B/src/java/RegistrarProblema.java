
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Veronica
 */
@WebServlet(urlPatterns = {"/RegistrarProblema"})
public class RegistrarProblema extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    @Override
    public void init(ServletConfig scg) throws ServletException
    {
        String url = "jdbc:mysql:3306//localhost/saec";
        String username = "root";
        String password = "n0m3l0";
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost/saec";
            con = DriverManager.getConnection(url, username, password);
            set = con.createStatement();
            
            System.out.println("Si conectó a la BD");
            
        }catch(Exception e)
        {
            System.out.println("No conectó, :(");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registrar problema</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try
            {
                String str = request.getParameter("boleta");
                int boleta = Integer.parseInt(str);
                int i, nLab, idCompu, idGrupo;
                String problema, fecha, hora, laboratorio, password, consulta, act;
                problema = request.getParameter("problema");
                fecha = request.getParameter("fecha");
                hora = request.getParameter("hora");
                laboratorio = request.getParameter("laboratorio");
                nLab = Integer.parseInt(laboratorio);
                
                consulta = "select * from computadora_has_alumno where alumno_boleta="+boleta;
                rs = set.executeQuery(consulta);
                for(i=0; i<nLab; i++)
                {
                    rs.next();
                }
                idCompu = rs.getInt("computadora_idcomputadora");
                
                consulta = "select * from alumno where boleta="+boleta;
                rs = set.executeQuery(consulta);
                rs.next();
                idGrupo = rs.getInt("grupo_idgrupo");
                password = rs.getString("password");
                
                act = "insert into problema (problema, fecha, hora, computadora_idcomputadora, alumno_boleta, alumno_grupo_idgrupo)"
                        + " values('"+problema+"', '"+fecha+"', '"+hora+"', "+idCompu+", "+boleta+", "+idGrupo+")";
                set.executeUpdate(act);
                out.println("<h1>Problema registrado exitosamente!</h1>");
                out.println("<h1>Tu cuenta ha sido registrada con éxito!</h1>");
                out.println("<form>");
                out.println("<input type='hidden' name='boleta' value='"+boleta+"'>");
                out.println("<input type='hidden' name='password' value='"+password+"'>");
                out.println("<input type='submit' value='Volver'>");
                out.println("</form>");
                out.println("<a href='index.html'>Volver a página de inicio</a>");
                System.out.println("Dato registrado");
                
            }catch(Exception e)
            {
                System.out.println("No se pudo registrar verificar los datos de entrada");
                System.out.println(e.getMessage());
                out.println("<h1>Problema no se pudo registrar, hay un error</h1>");
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
