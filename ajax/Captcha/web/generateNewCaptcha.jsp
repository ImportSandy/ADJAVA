<jsp:useBean id="imgGen" scope="session" class="captcha.ImageGenerator"/>
<%
    imgGen.deleteCaptcha();
%>
<%=imgGen.generateCaptcha()%>