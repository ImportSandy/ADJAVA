import java.io.*;
import javax.servlet.*;

public class AddNumbers implements Servlet
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
  s1 = req.getParameter("v1");
  s2 = req.getParameter("v2");

  int flag = 0; 
  double d1, d2, ans;
  d1 = d2 = ans = 0;

  try
  {
   d1 = Double.parseDouble(s1.trim());
   d2 = Double.parseDouble(s2.trim());
   ans = d1 +d2;
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
   out.println("<h1> " + d1 + " + " + d2  + " = " + ans  + " </h1>");  
  }
  else
  {
   out.println("<h1> Couldnt fetch data</h1>");  
  }
  out.println("<h2><a href = \"index.html\">Change Numbers</a></h2>");
  out.println("<h2><a href = \"handleNumbers?n1="+d1+"&n2="+d2+"\">Change Process</a></h2>");  out.println("</body>");
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