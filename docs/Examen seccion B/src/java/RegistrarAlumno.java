
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
@WebServlet(urlPatterns = {"/RegistrarAlumno"})
public class RegistrarAlumno extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param scg
     * @throws ServletException if a servlet-specific error occurs
     */
    
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
            out.println("<title>Registrar Alumno</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try
            {
                String nombre, appat, apmat, grupo, password, str;
                int boleta, labBD, labNV, labFondo;
                int llaveGrupo;
                
                nombre = request.getParameter("nombre");
                appat = request.getParameter("appat");
                apmat = request.getParameter("apmat");
                grupo = request.getParameter("grupo");
                password = request.getParameter("password");
                
                str = request.getParameter("boleta");
                boleta = Integer.parseInt(str);
                
                str = request.getParameter("labBD");
                labBD = Integer.parseInt(str);
                
                str = request.getParameter("labNV");
                labNV = Integer.parseInt(str);
                
                str = request.getParameter("labFondo");
                labFondo = Integer.parseInt(str);
                
                System.out.println(nombre);
                System.out.println(appat);
                System.out.println(apmat);
                System.out.println(boleta);
                System.out.println(grupo);
                System.out.println(password);
                System.out.println(labNV);
                System.out.println(labBD);
                System.out.println(labFondo);
                
                switch(grupo)
                {
                    case "4IV7":
                        llaveGrupo = 1;
                        break;
                    case "4IV8":
                        llaveGrupo = 2;
                        break;
                    case "4IV9":
                        llaveGrupo = 3;
                        break;
                    case "4IV10":
                        llaveGrupo = 4;
                        break;
                    default:
                        llaveGrupo = 100;
                        out.println("<h1>Grupo no disponible</h1>");
                        break;
                }
                String q = "insert into alumno (boleta, nombre, appat, apmat, password, grupo_idgrupo)"
                        + "values("+boleta+", '"+nombre+"', '"+appat+"', '"+apmat+"', '"+password+"', "+llaveGrupo+")";
                set.executeUpdate(q);
                
                //registro de la computadora de laboratorio de nuevas tecnologías
                q = "select * from computadora where numero="+labNV+" and laboratorio_idlaboratorio=1";
                rs = set.executeQuery(q);
                rs.next();
                labNV = rs.getInt("idcomputadora");
                q = "insert into computadora_has_alumno values("+labNV+", "+boleta+")";
                set.executeUpdate(q);
                
                //registro de la computadora de laboratorio de base de datos
                q = "select idcomputadora from computadora where numero="+labBD+" and laboratorio_idlaboratorio=2";
                rs = set.executeQuery(q);
                rs.next();
                labBD = rs.getInt("idcomputadora");
                System.out.println(labBD);
                q = "insert into computadora_has_alumno values("+labBD+", "+boleta+")";
                set.executeUpdate(q);
                
                //registro de la computadora de laboratorio del fondo
                q = "select idcomputadora from computadora where numero="+labFondo+" and laboratorio_idlaboratorio=3";
                rs = set.executeQuery(q);
                rs.next();
                labFondo = rs.getInt("idcomputadora");
                q = "insert into computadora_has_alumno values("+labFondo+", "+boleta+")";
                set.executeUpdate(q);
                System.out.println(labFondo);
                
                out.println("<h1>Tu cuenta ha sido registrada con éxito!</h1>");
                out.println("<form>");
                out.println("<input type='hidden' name='boleta' value='"+boleta+"'>");
                out.println("<input type='hidden' name='password' value='"+password+"'>");
                out.println("<input type='submit' value='ingresar'>");
                out.println("</form>");
                out.println("<a href='index.html'>Volver a página de inicio</a>");
                System.out.println("Dato registrado");
                
            }catch(Exception e)
            {
                System.out.println("No se pudo registrar verificar los datos de entrada");
                System.out.println(e.getMessage());
                out.println("<h1>Alumno No se pudo Registrar, hay un error</h1>");
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
