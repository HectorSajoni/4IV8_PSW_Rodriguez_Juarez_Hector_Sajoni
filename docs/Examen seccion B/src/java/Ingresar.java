
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
@WebServlet(urlPatterns = {"/Ingresar"})
public class Ingresar extends HttpServlet {

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
    private ResultSet rs, res;
    
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
            int usuario=1;
            String contraseña, user;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Página de alumno</title>");  
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            
            user = request.getParameter("boleta");
            try
            {
                usuario = Integer.parseInt(user);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                out.println("<h1 class='blanco'>Escribe un número en el campo de boleta</h1>");
            }
            contraseña = request.getParameter("password");

            if("".equals(contraseña))
            {
                out.println("<h1 class='blanco'>El campo de contraseña está vacío<h1>");
            }
            
            try{
                int bol;
                String cont;
                
                String q = "select * from alumno";

                rs = set.executeQuery(q);
                
                while(rs.next()){
                    bol = rs.getInt("boleta");
                    cont = rs.getString("password");
                    
                    if(usuario == bol)
                    {
                        if(contraseña.equals(cont))
                        {
                            //Prácticamente toda la página va aquí
                            
                            String nombre, appat, apmat, grupo;
                            int idGrupo;
                            
                            nombre = rs.getString("nombre");
                            appat = rs.getString("appat");
                            apmat = rs.getString("apmat");
                            idGrupo = rs.getInt("grupo_idgrupo");
                            
                            String q2 = "select * from grupo where idgrupo="+idGrupo;
                            res = set.executeQuery(q2);
                            res.next();
                            grupo = res.getString("grupo");
                            
                            out.println("<h2>Información del alumno</h2>");
                            out.println("<h3>Nombre del alumno: "+nombre+"</h3>");
                            out.println("<h3>Apellido paterno: "+appat+"</h3>");
                            out.println("<h3>Apellido materno: "+apmat+"</h3>");
                            out.println("<h3>Boleta: "+bol+"</h3>");
                            out.println("<h3>Grupo: "+grupo+"</h3>");
                            out.println("<a href='index.html'>Regresar</a><br><br>");
                            
                            out.println("<h2>Problemas</h2>");
                            out.println("<form action='RegistrarProblema' method='post'>");
                            out.println("<input type='hidden' name='boleta' value='"+bol+"'>");
                            out.println("<label>Descripción del problema(No escriba más de 200 caractéres)</label><br>");
                            out.println("<textarea name='problema'></textarea><br><br>");
                            out.println("<label>Fecha</label><br>");
                            out.println("<input type='date' name='fecha'><br><br>");
                            out.println("<label>Hora</label><br>");
                            out.println("<input type='time' name='hora'><br><br>");
                            out.println("<label>Laboratorio</label><br>");
                            out.println("<select name='laboratorio'>"
                                    + "<option value='1'>Laboratorio de nuevas tecnologías</option>"
                                    + "<option value='2'>Laboratorio de Base de datos</option>"
                                    + "<option value='3'>Laboratorio del fondo</option>"
                                    + "</select><br><br>");
                            
                            out.println("<input type='submit' value='Registrar problema'>");
                            out.println("</form><br>");
                            
                        }else
                        {
                            out.println("<h1>El usuario fue encpntrado, pero la contraseña es incorrecta</h1>");
                        }
                    }
                }
                
                rs.close();
                set.close();
                System.out.println("Se cerró el flujo");
            }catch(Exception e)
            {
                System.out.println("Esta es la excepción");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
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
