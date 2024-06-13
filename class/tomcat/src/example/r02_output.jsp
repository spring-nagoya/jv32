<!DOCTYPE html>
<html>
<head>
  <title>Hello, world!</title>
</head>
<body>
  <%

  String name = request.getParameter("name");

  if (name == null) {
    response.sendRedirect("/example/r02_input.jsp");
  }

  out.println("your name :"+ name);

%>

  input value: <%= strName %>
</body>
</html>