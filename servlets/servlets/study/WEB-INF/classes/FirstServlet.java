import java.io.*;
import javax.servlet.*;

public class FirstServlet implements Servlet
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
  Thread t = Thread.currentThread();
  System.out.println("Service Exceuted By : " + t);
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