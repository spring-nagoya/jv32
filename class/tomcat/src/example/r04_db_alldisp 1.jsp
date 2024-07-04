<%@page import="java.io.StringBufferInputStream"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashMap"%>
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
//DB連携の変数定義
Connection con = null;
Statement stmt = null;
ResultSet rs = null;

//①Driverの読み込み
Class.forName("com.mysql.jdbc.Driver");

//②DB接続
con = DriverManager.getConnection("jdbc:mysql://localhost/2024jv32?useSSL=false", "root", "Passw0rd");
//SQL:alter user 'root'@' localhost' identified with mysql_native_password by 'Passw0rd';

//③SQL実行変数の作成
stmt = con.createStatement();

//④SQL文の作成
String strSQL = " select * from t_user ";

//⑤SQL
//stmt.executeQuery : select文
//stmt.executeUpdate : select文以外
rs = stmt.executeQuery(strSQL);

while(rs.next()){
	out.println(rs.getString("f_name"));
	out.println("<br/>");
}

//rsの解放
rs.close();
//stmtの解放
stmt.close();
//DB切断
con.close();

%>

</body>
</html>