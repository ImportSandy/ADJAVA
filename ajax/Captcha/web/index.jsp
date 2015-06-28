<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="imgGen" scope="session" class="captcha.ImageGenerator"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>
            var req;
            var res;
            function authenticateCaptcha( captchaText)
            {
                //create an object of XMLHttpRequest
                req = new XMLHttpRequest();           
                //form the query string 
                var data = "cptch="+captchaText;
                //set the url
                var url = "authnCptch.jsp?"+data;
                //open the request
                req.open("GET", url, false);//set request method, target url, asynchronous flag
                //register the function to receive reponse
                req.onreadystatechange = updateResult;
                //send the req
                req.send();
            }
            
            function updateResult()
            {
                if(req.readyState === 4)//reponse ready
                {
                    if(req.status === 200)//OK
                    {
                        //fetch the reponse
                        res = req.responseText;
                    }
                    else
                    {
                        res = "0";
                    }
                }
            }
            
            function showErr(id, errText)
            {
                var e = document.getElementById(id);
                e.innerHTML = "<b>" + errText + "</b>";
                return false;
            }
            function check()
            {
                //fetch all inputs 
                var a, b, c;
                a = document.f.name.value;
                b = document.f.age.value;
                c = document.f.captcha.value;

                if(a.length ===0 )
                {
                    return showErr("divName", "Name Missing");
                }
                else if(b.length ===0)
                {
                    return showErr("divAge", "Age Missing");
                }
                else if(c.length ===0)
                {
                    return showErr("divCaptcha", "Captcha Missing");
                }
                
                if(b <0)
                {
                    return showErr("divAge", "Negative Age");
                }
                
                authenticateCaptcha(c);
                var q = eval(res);
                if(q === 1)
                    return true;//submit 
                else
                {
                    return showErr("divCaptcha", "Invalid Captcha");
                }
            }
            
            function clearDiv(id)
            {
                var e = document.getElementById(id);
                e.innerHTML= "";
            }
            
            function checkAge()
            {
                var a = document.f.age.value;
                if(a < 0)
                {
                    showErr("divAge", "Negative Age");
                }
            }
            function updateImage2(imgPath)
            {
                var i = document.getElementById("img2");
                i.src = imgPath;
            }
            
            function getNewCaptcha()
            {
                updateImage2("images/updating.png");
                req = new XMLHttpRequest();
                var url = "generateNewCaptcha.jsp";
                req.open("GET", url, true);
                req.onreadystatechange = updateThisCaptcha;
                req.send();
                
            }
            
            function updateThisCaptcha()
            {
                if(req.readyState === 4)
                {
                    if(req.status === 200)
                    {
                        var response = req.responseText;
                        var e1 = document.getElementById("img1");
                        e1.src = response;
                        
                        updateImage2("images/refresh.png");
                    }
                }
            }
            
        </script> 
    </head>
    <body>
        <h1>Captcha Authentication Using AJAX</h1>
        <form name = "f" method="post" action="processForm.jsp" onsubmit="return check()">
            <table>
                <tr>
                    <td>Enter Name</td>
                    <td><input type="text" name="name" onfocus='clearDiv("divName")'/></td>
                    <td><div id="divName"></div></td>
                </tr>
                <tr>
                    <td>Enter Age</td>
                    <td><input type="number" name="age" onfocus='clearDiv("divAge")' onblur="checkAge()"/></td>
                    <td><div id="divAge"></div></td>
                </tr>
                <tr>
                    <td><img id ="img1" src="<%=imgGen.generateCaptcha()%>" width="420" height="120"/></td>
                    <td><input type="text" name="captcha" onfocus='clearDiv("divCaptcha")'/> </td>
                    <td><div><img id = "img2" src = "images/refresh.png" onclick="getNewCaptcha()" /></div><div id="divCaptcha"></div></td>
                </tr>
                

                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name="bttnSubmit" value="Process"/></td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
