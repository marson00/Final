<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.OutputStream"%>
<%@page import="Model.ProductDA"%>
<%@page import="Model.Product"%>

<%
    Product prod = new Product();
    ProductDA prodDA = new ProductDA();
    String id = request.getParameter("id");
    
    prod = prodDA.RetrieveProduct(id);
    Blob blob = prod.getImage();
    byte byteArray[] = blob.getBytes(1, (int)blob.length());
    response.setContentType("image/gif");
    OutputStream os = response.getOutputStream();
    os.write(byteArray);
    os.flush();
    os.close();
    response.sendRedirect("DisplayProduct.jsp");
%>
