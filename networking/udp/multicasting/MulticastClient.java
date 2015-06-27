import java.net.*;

class MulticastClient
{
 MulticastSocket mSkt;

 MulticastClient() throws Exception
 {
  //create a multicast socket that uses
  //the same port as used for packet 
  //tranmissions by the multicast sender.

  int vh_port = 1234;
  InetAddress vh_ip = InetAddress.getByName("224.5.6.7");

  mSkt = new MulticastSocket(vh_port);
  mSkt.joinGroup(vh_ip);

 }

 void fetch() throws Exception
 {
  DatagramPacket p;
  String data = "";
  do
  {
   p = new DatagramPacket(new byte[128], 128);
   mSkt.receive(p);

   byte mdata[] = p.getData();
   int mlen = p.getLength();
   data = new String(mdata, 0, mlen);

   System.out.println("Received : " + data);

  }while(!data.equals("0"));
 }

 void close()throws Exception
 {
  mSkt.close();
 }
 public static void main(String args[])
 {
  try
  {
   MulticastClient client = new MulticastClient();
   client.fetch();
   client.close();
  }
  catch(Exception ex)
  {}
 }//main
}//MulticastClient