package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

/**
 *
 * @author Marson(TingLe)
 */
@WebServlet(name = "DisplayImgServlet", urlPatterns = {"/DisplayImgServlet"})
public class DisplayImgServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("prodID");
    
        String host = "jdbc:derby://localhost:1527/javaassignmentdb";
        String user = "javadb";
        String password = "javadb";
        Connection conn;
        PreparedStatement stmt;
    
        try{

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(host, user, password);

            stmt = conn.prepareStatement("SELECT * FROM PRODUCT_TABLE WHERE PRODUCTID = ?");
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Blob blob = rs.getBlob("IMAGEURL");
                byte byteArray[] = blob.getBytes(1, (int)blob.length());
                response.setContentType("image/gif");
                OutputStream os = response.getOutputStream();
                os.write(byteArray);
                os.flush();
                os.close();
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    

}
