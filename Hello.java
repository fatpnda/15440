import java.rmi.*;
import java.rmi.server.*;

class Hello extends UnicastRemoteObject implements HelloInterface
{
  private static final String serverName = "hello";
  private int count;

  public Hello() throws RemoteException
  {
    count = 0;
  }

  // This is actually the starting point that registers the object, &c
  public static void main (String []args)
  {
    try
    {
      // Instantiate an instance of this class -- create a Hello object
      Hello server = new Hello();

      // Tie the name "Hello" to the Hello object we just created
      //Naming.rebind (serverName, server);

      // Just a console message
      System.out.println ("Hello Server ready");
      while (true) {
        Thread.sleep (1000);
        System.out.println ("here");
      }
    }
    catch (Exception e)
    {
      // Bad things happen to good people
      e.printStackTrace();
    }
  }

  // This is the one real method
  public String sayHello(String name) throws RemoteException
  {
    count++;
    return "Hello World " + count +  "! Hello " + name; 
  }
}
