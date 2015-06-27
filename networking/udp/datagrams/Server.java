import java.util.*;
import java.io.*;
import java.net.*;

class Server implements Runnable
{
 Thread ioThread;
 boolean ioFlag; 

 DatagramSocket dSkt;
 LinkedList<String> states;
 LinkedList<Integer> numbers;

 Server(int port) throws Exception
 {
  //open and bind to the specified port using a DatagramSocket 
  dSkt = new DatagramSocket(port);

  //fetch the data for service
  Processor p = new Processor();
  numbers = p.populateNumberList();
  states = p.populateStateList("states.txt");

  //create a thread for io
  ioFlag = true;
  ioThread = new Thread(this);
  ioThread.start();
 }

 public void run()
 {
  while(ioFlag)
  {
   try
   {
   //create a datagram for reading
   byte buff[] = new byte[1024];
   DatagramPacket dp1 = new DatagramPacket(buff, buff.length);
   
   //read a datagram
   System.out.println("waiting for a datagram ...");
   dSkt.receive(dp1);//blocks the current thread until a datagram is received

   System.out.println("... got one");
   byte content[] = dp1.getData();
   int len = dp1.getLength();
   String request = new String(content, 0, len);

   System.out.println("request : "+ request.length() + " " + request);
   
   String reply; 
   if(request.equalsIgnoreCase("states"))
   {
    if(states.size() > 0)
      reply = states.remove();//remove head node and return its data
    else
      reply = "Data Not Available";
   }
   else if(request.equalsIgnoreCase("numbers"))
   {
    if(numbers.size() > 0)
      reply = numbers.remove().toString();//remove head node and return its data
    else
      reply = "Number Not Available";
   }
   else
   {
     reply = "UNKNOWN REQUEST"; 
   }
 
   //create a datagram for transmission
   DatagramPacket dp2 = new DatagramPacket(reply.getBytes(), reply.length(), dp1.getAddress(), dp1.getPort());
   
   //send it
   dSkt.send(dp2);
   }
   catch(Exception ex)
   {}
  }//while
 }//run

 public static void main(String args[]) 
 {
  try
  {
   Server s = new Server(1234);
  } 
  catch(Exception ex)
  {
   System.out.println("Err :" + ex);
  }
 }
}//Server

class Processor
{
 LinkedList<String> populateStateList()
 {
  LinkedList<String> states = new LinkedList<String>();
  states.add("Maharashtra");
  states.add("Gujrat");
  states.add("MP");
  states.add("UP");
  states.add("JK");
  return states;
 }

 LinkedList<String> populateStateList(String fname)
 {
  try
  {
   LinkedList<String> states =  new LinkedList<String>();

   //open the file
   FileReader fr = new FileReader(fname);
   BufferedReader br = new BufferedReader(fr);

   //read it line by line
   String s ;

   while( (s = br.readLine()) != null )//readLine() ideally returns a String, but at EOF returns null
   {
    //put the read line in the linked list
    states.add(s);
   }
   //close the file
   br.close();
   return states;
  }
  catch(IOException ex)
  {
   return populateStateList();
  }
 }

 LinkedList<Integer> populateNumberList()
 {
  LinkedList<Integer> numbers  = new LinkedList<Integer>();   
  Random r = new Random();
  Integer intgr;
  for(int i = 0; i< 10; i++)
  {
    intgr = new Integer(r.nextInt(100));//generate any random int value in range 0-99
    numbers.add(intgr); 
  }//for
  return numbers;

 }//populateNumberList

}