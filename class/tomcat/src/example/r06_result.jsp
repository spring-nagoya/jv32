<%@page import="java.util.HashMap" %>
<%@page import="java.util.ArrayList" %>
<%@page lnaguage="java" contentType="text/html; charset=UTF-9" %>
<!DOCTYPE html>
<html>
<head>
  <title>r06_result.jsp</title>
<%
  String strPath = request.getContextPath();
%>
</head>
<body>

<%
ArrayList<HashMap<String, String>> aryData = (ArrayList<HashMap<String, String>>) request.getAttribute("dataRows");

if (aryData != null) {
  for (int i = 0; i < aryData.size(); i++) {
    HashMap<String,String> map = aryData(i);
    out.println(aryData.get(i).get("f_name"));
    out.println("<br>");
  }
}
%>
</body>
</html>