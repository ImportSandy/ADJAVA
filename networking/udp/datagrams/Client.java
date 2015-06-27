import java.net.*;

class Client
{
 String req;
 DatagramSocket ds; 

 Client(String input) throws Exception
 {
   if(input.equalsIgnoreCase("states"))
     req = input;
   else if(input.equalsIgnoreCase("numbers"))
     req = input;
   else
     req = "states";

   //open and binds to any anonymous port on this computer.
   ds = new DatagramSocket();   

 }//Client

 void performIO() throws Exception
 {
  int i;
  byte arr[] = req.getBytes();
  InetAddress ip = InetAddress.getByName("127.0.0.1");
  int port = 1234;

  for(i =0; i< 3;  i++)
  {
   DatagramPacket dp1 = new DatagramPacket(arr, arr.length, ip, port);
   ds.send(dp1);//transmit the packet

   byte arr1[] = new byte[1024];
   DatagramPacket dp2 = new DatagramPacket(arr1, arr1.length);
   ds.receive(dp2); //blocks for packet

   //response available
   byte response[] = dp2.getData();   
   int len = dp2.getLength();
   String resp = new String(response, 0, len);
   System.out.println("Server Says : " + resp);
  }//for  
 }//performIO

 void close() throws Exception
 {
   //close the datagram socket
   ds.close();
 }

 public static void main(String args[])
 {
  try
  {
   String temp = "no data";
   if(args.length > 0)
     temp = args[0];
   
   Client c = new Client(temp);
   c.performIO();   
   c.close();
  }
  catch(Exception ex)
  {
   System.out.println("Err : " +ex);
  }
 }
}