<!DOCTYPE html>
<html>
<head>
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

    for(int i=0; i < 10; i++) {
      out.println(i);
    }

    int i = 0;
    while (i < 10) {
      out.println("<br /> \n"+ i); 
      i++;
    }

    int code = 404;

    switch (code)  {
      case 404:
        out.println("<br /> Not Found"); 
        break;
      case 500:
        out.println("<br /> Internal Server Error"); 
        break;
      case 200:
        out.println("<br /> Status Ok"); 
        break;
      default:
        out.println("<br /> ");
    }


  %>

 
  <h1><%= "ktok over 180lbs !!!!" %></h1>
</body>
</html>