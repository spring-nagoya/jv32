<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Hello, world!</title>
</head>
<body>
  <%
    out.println("hello world");  
    Integer intA = 0;
    Integer IntB = new Integer(0);
    
    String strA = "hoge";
    String strB = new String("fuga");
  
    if (strA == "hoge") {
      out.println("<br />等しい");
    }else {
      out.println("<br />等しくない");
    }
  
    if (strB == "fuga") {
      out.println("<br />等しい");
    }else {
      out.println("<br />等しくない");
    }
  
    if (strB.equals("fuga")) {
      out.println("<br />等しい");
    }else {
      out.println("<br />等しくない");
    }
  %>

 
  <h1><%= "ktok over 180lbs !!!!" %></h1>
</body>
</html>