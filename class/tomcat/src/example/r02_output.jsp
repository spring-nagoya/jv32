<!DOCTYPE html>
<html>
<head>
  <title>Hello, world!</title>
</head>
<body>
  <%
    String strName = request.getParameter("name");
    if (strName != null) {
      if (strName.equals("")) {
        strName = "未入力";
      }
    }else {
      response.sendRedirect("/example/r02_input.jsp");
    }
%>

  input value: <%= strName %>
</body>
</html>