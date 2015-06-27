<%-- 
JSP STUDY APPLICATION
Demonstrates all kinds of JSP tags 

read JSP.txt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />
        <%--        
        <%
            //java code here
            int i;
            for(i =0 ;i< 10; i++)
            {
                if(i %2 == 0)
                {
                    out.println("<h2>Hello JSP</h2>");
                }
                else
                {
                    out.println("<h2>Hello Student</h2>");
                }
            }
        %>
        --%>
        
        <%
            int i;
            for(i =0; i< 10; i++)
            {
                if(i == 0)
                {
                    %>
                    <h2>Hello JSP</h2>
                    <%
                }
                else
                {
                    %>
                    <h2> Hello Student</h2>
                    <%
                }
            }
                    
        %>
    </body>
</html>
