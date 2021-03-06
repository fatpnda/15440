import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;

class test {
  public static interface Numberable extends Serializable {
    public void add ();
    public String toString ();
  }

  public static class Numbers implements Serializable, Numberable {
    public int a;
    public double b;
    public char c;
    
    public Numbers (int a, double b, char c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }
    
    public void add () {
      a += 1;
      b += 1;
      c += 1;
    }
    
    public String toString () {
      return "<" + a + "," + b + "," + c + ">";
    }
  }

  public static void main (String[] args) {
    try {
      /*String a = "Asdf";
      Integer b = new Integer (1234);
      b += 1;
      RMIReturnValue rv = new RMIReturnValue ("xs12");
      RMIException e = new RMIException (new Exception ("asdfwer"));
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream ("file"));
      
      out.writeObject (a);
      out.writeObject (b);
      out.flush ();
      out.close ();
      
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("file"));
      System.out.println (in.readObject ());
      System.out.println (in.readObject ());
      in.close ();
      
      rv.sendTo ("file");
      
      in = new ObjectInputStream (new FileInputStream ("file"));
      System.out.println (in.readObject ());
      in.close ();
      
      e.sendTo ("file");
      
      in = new ObjectInputStream (new FileInputStream ("file"));
      Exception ex = (Exception) in.readObject ();
      ex.printStackTrace ();
      in.close ();*/
      
      try {
        Class<?>[] argTypes = { String.class };
        Serializable[] arguments = { "asdf" };
        NEMethodInvocation message =
          new NEMethodInvocation 
            (123, HelloInterface.class, "sayHello", argTypes, arguments); 
        
        Class[] at = message.getArgumentTypes ();
        for (Class c : at) System.out.println (c); 
             
        Method m = message.getObjectType ().getMethod (
          message.getMethodName (), argTypes);
      }
      catch (Exception e) {
        e.printStackTrace ();
      }
      
      /*Numberable n = (Numberable) (new Numbers (10,3.0,'a'));
      FileOutputStream out = new FileOutputStream ("out");
      Class<?> objectType = n.getClass ();
      System.out.println (Numberable.class);
      Class<?>[] argTypes = { };
      boolean[] argRemotes = {  };
      Serializable[] arguments = { };
      NEMarshaller.marshal (
        new NEMethodInvocation 
          (Numberable.class, "add", argTypes, arguments, argRemotes),
        out);
      
      out.close ();
      
      FileInputStream filein = new FileInputStream ("out");
      ObjectInputStream in = new ObjectInputStream (filein);
      NEMethodInvocation message = (NEMethodInvocation) in.readObject ();
      NEMethodCall nn = NEDemarshaller.demarshalMethodInvocation (message);
      nn.invoke (n);
      nn.invoke (n);
      nn.invoke (n);
      System.out.println (n);
      System.out.println (n.getClass ());*/
      
      //in.close ();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}

