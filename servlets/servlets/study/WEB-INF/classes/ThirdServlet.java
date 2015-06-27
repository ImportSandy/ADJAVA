import java.io.*;
import javax.servlet.*;

public class ThirdServlet implements Servlet
{
 private ServletConfig config;

 //override 3 life cycle and
 //2 ancillary methods

 public void init(ServletConfig c)
 {
  config = c;
  System.out.println("in init");
 }

 public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException
 {
  res.setContentType("text/html");
  PrintWriter out = res.getWriter();
  out.println("<html>");
  out.println("<body>");  
  int i;
  String s ;
  out.println("<h1>Messages</h1>");

  out.println("<ul>");
  for(i =0 ; i< 10; i++)
  {
   if(i %2 == 0)
     s = "Hello Web Applications";
   else 
     s = "Hello Competitors";

     out.println("<li>"+s+"</li>");
  }
  out.println("</ul>");
  out.println("</body>");  
  out.println("</html>");
  out.flush();
  out.close();
 }

 public void destroy()
 {}

 public ServletConfig getServletConfig()
 {
  return config;
 }

 public String getServletInfo()
 {
  return "My Third Servlet";   
 }
}//FirstServlet