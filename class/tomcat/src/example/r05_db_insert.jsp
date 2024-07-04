<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>r05_db_insert.jsp</title>
</head>
<body>
<h4>r05_db_insert.jsp</h4>
<%
  //スクリプトレット
  //  Javaプログラムを書くところ
  
  //受け取るパラメーターの文字コードを変更
  request.setCharacterEncoding("UTF-8");
  
  //r05_input_form.jspから値の受け取り
  String strId = request.getParameter("txtId");
  String strName = request.getParameter("txtName");
  String strAge = request.getParameter("txtAge");
  String strPass = request.getParameter("txtPass");  
  
  //DB連携の変数定義
  Connection con = null;
  Statement stmt = null;
  
  //①Driverの読み込み
  Class.forName("com.mysql.jdbc.Driver");
  
  //②DB接続
  con = DriverManager.getConnection
         ("jdbc:mysql://localhost/2024jv32?useSSL=false", 
          "root", "Passw0rd");
  
  //③SQL実行変数の作成
  stmt = con.createStatement();
  
  //④SQL文の作成
  String strSQL = " insert into t_user ";
  
  strSQL += "VALUES (";
  strSQL += "'" + strId + "', ";
  strSQL += "'" + strName + "', ";
  strSQL += "'" + strPass + "', ";
  strSQL += "'" + strAge + "'";
  strSQL += ")";
  
  //⑤SQL文の実行
  //  stmt.executeQuery : select文
  //  stmt.executeUpdate: select文以外
  int intRows = stmt.executeUpdate(strSQL);

  out.println("insertが完了"+intRows+"件");
  
  //  stmtの解放
  stmt.close();
  //  DB切断
  con.close();
%>
</body>
</html>



