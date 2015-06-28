<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="imgGen" scope="session" class="captcha.ImageGenerator"/>
<%
    imgGen.deleteCaptcha();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PROCESS FORM</h1>
    </body>
</html>
