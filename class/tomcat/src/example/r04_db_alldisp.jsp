<%@page import="java.util.HashMap"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>r04_db_alldisp.jsp</title>
</head>
<body>
<h4>r04_db_alldisp.jsp</h4>
<%
  //スクリプトレット
  //  Javaプログラムを書くところ
  Connection con = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  Class.forName("com.mysql.jdbc.Driver");
  
  con = DriverManager.getConnection("jdbc:mysql://localhost","admin","mysql");

  stmt = con.createStatement();

  String strSQL = "SELECT * FROM users";

  rs = stmt.executeQuery(strSQL);
  while (rs.next()) {
    out.println(rs.getString("name"));
  }

  rs.close();
  stmt.close();
  con.close();
%>
</body>
</html>



