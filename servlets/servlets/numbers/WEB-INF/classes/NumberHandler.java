import java.io.*;
import javax.servlet.*;

public class NumberHandler implements Servlet
{
 ServletConfig config;
 public void init(ServletConfig cg)
 {
  config = cg;
 }

 public void destroy()
 {}

 public void service(ServletRequest req, ServletResponse res) throws IOException , ServletException
 {
  //fetch request data
  String s1, s2; 
  s1 = req.getParameter("n1");
  s2 = req.getParameter("n2");

  //sanitize the input  
  double d1= 0, d2=0;
  int flag = 0 ;
  try
  {
    d1 = Double.parseDouble(s1.trim()); 
    d2 = Double.parseDouble(s2.trim()); 
  }
  catch(Exception ex)
  {
   flag = 1;
  }

  //output
  res.setContentType("text/html");
  PrintWriter out = res.getWriter();
  
  out.println("<html>");
  out.println("<body>");
  if(flag == 0)
  {
   out.println("<h1> n1 : "+ d1 +"</h1>");  
   out.println("<h1> n2 : "+ d2 +"</h1>");  

   out.println("<h2><a href = \"AddNumbers?v1="+d1+"&v2="+d2+" \"> Add </a> </h2>");
   out.println("<h2>");
   out.println("<form method = \"post\" action = \"SubtractNumbers\">");
   out.println("<input type = \"hidden\" value = "+d1+" name = \"v1\" >");
   out.println("<input type = \"hidden\" value = "+d2+" name = \"v2\" >");
   out.println("<input type = \"submit\" value = \"Subtract\">");
   out.println("</form>");
   out.println("</h2>");

  }//if(flag == 0)
  else
  {
   out.println("Invalid Input");
   out.println("<br>");
   out.println("<a href = \"index.html\" >Click Here To RETRY </a>");
  }
  out.println("<h2><a href = \"index.html\">Change Numbers</a></h2>");

  out.println("</body>");
  out.println("</html>");
  out.flush();
  out.close();  
 }

 public ServletConfig getServletConfig()
 {
  return config;
 }

 public String getServletInfo()
 {
  return "Number Processor";
 }

}//NumberHandler