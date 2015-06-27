import java.net.*;

class MulticastServer implements Runnable
{
 MulticastSocket mSkt;
 InetAddress vh_ip; //virtual host
 int vh_port;//port

 Thread t;

 MulticastServer() throws Exception
 {
  //create a multicast socket that uses
  //anonymous available port for packet tranmissions 
  mSkt = new MulticastSocket();

  vh_ip = InetAddress.getByName("224.5.6.7");//IP OF CLASS D (used for multicasting on n/w's)
  vh_port = 1234;

  t = new Thread(this);
  t.start();
 }

 public void run()
 {
  DatagramPacket p;
  String data;
  int i;
  for(i =20; i>=0; i--)
  {
   try
   {
    System.out.println("Sending : " + i);
    data = String.valueOf(i);
    p = new DatagramPacket(data.getBytes(), data.length(), vh_ip, vh_port);  
    mSkt.send(p);
    Thread.sleep(1000);
   }
   catch(Exception ex)
   {}
  }//while
  close();
 }

 void close()
 {
  try
  {
    mSkt.close(); //close the MulticastSocket
  }
  catch(Exception ex)
  {}
 }

 public static void main(String args[])
 {
  try
  {
   MulticastServer svr = new MulticastServer();
  }
  catch(Exception ex)
  {
   System.out.println("Err : " + ex);
  }
 }//main
}//MulticastServer