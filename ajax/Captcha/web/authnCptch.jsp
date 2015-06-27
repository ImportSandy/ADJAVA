<jsp:useBean id="imgGen" scope="session" class="captcha.ImageGenerator"/>

<%
//fetch captcha text
    String s = request.getParameter("cptch");
    String captchaText = imgGen.getCaptchaText();
    if(s.equals(captchaText))
        out.print("1");
    else
        out.print("0");

%>