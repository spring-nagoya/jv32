<%@page import="myBean.UserBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>r06_result.jsp</title>
<%
  String strPath = request.getContextPath();
%>
</head>
<body>
<h4>r06_result.jsp</h4>
<%
	//Servletから値の受け取り
	ArrayList<HashMap<String, String>> aryData =
		(ArrayList<HashMap<String, String>>)
			request.getAttribute("dataRows");

	if(aryData !=null){
		for(HashMap<String,String> map : aryData){
			out.println(map.get("f_name"));
			out.println("<br />");
		}
	
	}
	
	//Beanクラスのプログラム
	ArrayList< UserBean > aryDataBean = (ArrayList< UserBean >) request.getAttribute("beanAry");
	
	if (aryDataBean != null){
		out.println("*** Beanで出力 ***<br/>");
		for( UserBean bean1 : aryDataBean ){
			out.println( bean1.getName() );
			out.println( "<br />" );
		}
	}

%>

</body>
</html>