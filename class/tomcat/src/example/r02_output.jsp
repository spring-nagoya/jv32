<!DOCTYPE html>
<html>
<head>
  <title>Hello, world!</title>
</head>
<body>
  <%
    String strID = request.getParameter("txtId");
    String strPass = request.getParameter("txtPass");

    if (strID == null || strPass == null) {
      response.sendRedirect("/example/r03_input.jsp");
    }

    String actualID = "admin";
    String actualPass = "1234";
    
    if (!strID.equals(actualID) || !strPass.equals(actualPass)) {
      out.println("authenticate failed");
      return;
    }

    out.println("authenticate successful");

%>

  input value: <%= strName %>
</body>
</html>