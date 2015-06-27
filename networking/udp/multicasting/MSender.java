import java.net.*;

class MSender implements Runnable
{
 MulticastSocket mSkt;
 Thread mcastThrd;

 MSender() throws Exception
 {
 //open a multicast socket and
 //bind it to any available port on
 //this computer
  mSkt = new MulticastSocket();

  //create a new thread for packet transmission  
  mcastThrd = new Thread(this);
  mcastThrd.start(); 
 }

 public void run()
 {
  try
  {
   int i;
   byte buff[];
   InetAddress dIp = InetAddress.getByName("224.5.6.7");
   int anyPort = 7890;
   DatagramPacket pkt;

   for(i =0; i< 20; i++)
   {
    try
    {
      //msg for student
      System.out.println("Transmitting " + i);
      //create a packet 
      buff = String.valueOf(i).getBytes();
      pkt = new DatagramPacket(buff, buff.length, dIp, anyPort); 

      //send the packet
      mSkt.send(pkt);       

      //delay
      Thread.sleep(500);//500 millisec
    }  
    catch(Exception e)
    {}
   }//for
   mSkt.close();//close the port
  }
  catch(Exception ex)
  {}
 }//run
 
 public static void main(String args[])
 {
  try
  {
    new MSender();
  }
  catch(Exception ex)
  {}
 }
}//MSender