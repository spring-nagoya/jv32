<!DOCTYPE html>
<html>
<head>
  <title>Hello, world!</title>
</head>
<body>
  <%@page import="java.util.*"%>
  <%
    String strID = request.getParameter("txtId");
    String strPass = request.getParameter("txtPass");

    if (strID == null || strPass == null) {
      response.sendRedirect("/example/r03_input.jsp");
    }
    
    HashMap<String, String> usersMap;
    usersMap = new HashMap<String, String>();

    usersMap.put("kataoka","ktok");
    usersMap.put("ataoka","ktok");

    

    if (!usersMap.containsKey(strID)) {
      out.println("failed to authentication.");
      return;
    }


    if (!usersMap.get(strID).equals(strPass)) {
      out.println("failed to authentication.");
      return;
    }

    out.println("authenticate successful");
%>

</body>
</html>