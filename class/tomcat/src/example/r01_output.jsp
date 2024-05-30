<!DOCTYPE html>
<html>
<head>
  <title>Hello, world!</title>
</head>
<body>
  <%
    String strName = request.getParameter("name");

    if (strName == null) {
      strName = ""
    }

    if (strName.equals("")) {
      strName = "未入力";
    }
  %>

  入力値：<%= strName %>
</body>
</html>