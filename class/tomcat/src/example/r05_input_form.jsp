<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>r05_input_form.jsp</title>
</head>
<body>
<h4>r05_input_form.jsp</h4>
<%
  //スクリプトレット
  //  Javaプログラムを書くところ
  
%>
<form action="r05_db_insert.jsp"
   method="get">
ＩＤ： <input type="text" 
          name="txtId"><br>
名前： <input type="text" 
          name="txtName"><br>
年齢： <input type="text" 
          name="txtAge"><br>
ＰＷ： <input type="text" 
          name="txtPass"><br>
<input type="submit" 
    value="ぼたん">
</form>


</body>
</html>
