import java.net.*;

class MReceiver
{
 MulticastSocket mSkt;
 
 MReceiver() throws Exception
 {
  //Open a multicast socket and 
  //bind to the same port to which
  //the packets are designated

  int anyPort  = 7890; 
  mSkt = new MulticastSocket(anyPort);
  
  //join the multicast group
  InetAddress dIp = InetAddress.getByName("224.5.6.7");
  mSkt.joinGroup(dIp);
   
 }

 void receivePackets() throws Exception
 {
  int i;
  DatagramPacket pkt;
  byte buff[];  
  for(i =0; i< 20; i++)
  {
   //make a packet for receiption
   buff = new byte[100];
   pkt = new DatagramPacket(buff, buff.length);
   
   //receive the packet
   mSkt.receive(pkt);//blocks the current thread
   String s = new String(pkt.getData(), 0, pkt.getLength());
   System.out.println("Received : " + s);  

  }//for
  mSkt.close();
 }//receivePackets

 public static void main(String args[])
 {
  try
  {
    MReceiver mrcv = new MReceiver();
    mrcv.receivePackets();
  }
  catch(Exception ex)
  {}
 }//main
}