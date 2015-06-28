<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        int flag = 1;
        Object temp = session.getAttribute("attempt");
        if(temp != null)
        {
            int x= Integer.parseInt(temp.toString());
            if(x == 3)
            {//threshold reached
                response.sendRedirect("passwordLost.jsp");
            }
            else
            {
                flag = 0;
            }
        }
        else
        {
            flag = 0;
        }
        if(flag == 0)
        {
        %>
        <h1>Login</h1>
        <form name="f" method="post" action="validate.jsp">
            <table>
                <tr>
                    <td>Uid</td>
                    <td><input type="text" name = "uid" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name = "pass" /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name = "bttnSubmit" value = "Login"/></td>
                </tr>
            </table>
        </form>
        <%
        }//if(flag == 0)
        %>
    </body>
</html>
