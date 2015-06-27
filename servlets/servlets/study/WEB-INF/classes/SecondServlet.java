import java.io.*;
import javax.servlet.*;

public class SecondServlet implements Servlet
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
  PrintWriter pw = res.getWriter();
  pw.println("<html>");
  pw.println("<body>");
  pw.println("<h1>Servlet is learning to write to the client </h1>");
  pw.println("</body>");
  pw.println("</html>");
  pw.flush();
  pw.close();
 }

 public void destroy()
 {}

 public ServletConfig getServletConfig()
 {
  return config;
 }

 public String getServletInfo()
 {
  return "My First Servlet";   
 }
}//FirstServlet