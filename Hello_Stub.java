// Decompiled by DJ v3.2.2.67 Copyright 2002 Atanas Neshkov  Date: 1/22/03 1:40:21 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 

import java.io.*;
import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

public final class Hello_Stub extends NERemoteObjectStub implements HelloInterface, NERemote {
  public String sayHello (String s) throws NERemoteException {
    try {
      Class<?>[] argTypes = { String.class };
      boolean[] argRemotes = { false };
      Serializable[] arguments = { s };
      NEMessageable message =
        new NEMethodInvocation 
          (HelloInterface.class, "sayHello", argTypes, arguments, argRemotes);
      
      FileOutputStream out = new FileOutputStream ("out");      
      NEMarshaller.marshal (message, out);
      Scanner inp = new Scanner (System.in);
      inp.nextLine ();
      
      FileInputStream filein = new FileInputStream ("in");
      ObjectInputStream in = new ObjectInputStream (filein);
      NEReturnValue rv = (NEReturnValue) in.readObject ();
      String result = (String) NEDemarshaller.demarshalReturnValue (rv);
      return result;   
    }
    catch (RuntimeException runtimeexception) {
      throw runtimeexception;
    }
    catch (NERemoteException remoteexception) {
      throw remoteexception;
    }
    catch (Exception exception) {
      //throw new UnexpectedException("undeclared checked exception", exception);
    }
    return null;
  }
}
