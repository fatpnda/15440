import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.util.*;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NENaming {
  private static NEURLInfo parseURL (String url) 
      throws NEMalformattedURLException {
    String host, name;
    int port = NERegistry.REGISTRY_PORT;
    if (! url.startsWith ("//")) throw new NEMalformattedURLException ();
    String s = url.substring (2);
    String[] hostAndStuff = s.split (":");
    if (hostAndStuff.length < 2) throw new NEMalformattedURLException ();
    host = hostAndStuff[0];
    s = hostAndStuff[1];
    String[] portAndName = s.split ("/", 2);
    if (portAndName.length < 2) throw new NEMalformattedURLException ();
    try {
      port = Integer.parseInt (portAndName[0]);
      name = portAndName[1];
    }
    catch (NumberFormatException e) {
      port = NERegistry.REGISTRY_PORT;
      name = s;
    }
    
    NEURLInfo info = new NEURLInfo ();
    info.name = name;
    info.host = host;
    info.port = port;
    
    return info;
  }

  public static NERemote lookup (String url)
      throws NEMalformattedURLException, NERemoteException, NENotBoundException, 
             NEAccessException, NERegistryNotFoundException {
    NEURLInfo info = parseURL (url);
    
    NERegistry reg = NERegistryLocator.getRegistry (info.name, info.port);
    return reg.lookup (info.name);
  }
  
  public static void bind (String url, NERemoteObjectReference object)
      throws NEMalformattedURLException, NERemoteException, NEAlreadyBoundException, 
             NEAccessException, NERegistryNotFoundException {
    NEURLInfo info = parseURL (url);
    
    NERegistry reg = NERegistryLocator.getRegistry (info.name, info.port);
    reg.bind (info.name, object);
  } 
  
  public static void rebind (String url, NERemoteObjectReference object)
      throws NEMalformattedURLException, NERemoteException, NEAccessException,
             NERegistryNotFoundException {
    NEURLInfo info = parseURL (url);
    
    NERegistry reg = NERegistryLocator.getRegistry (info.name, info.port);
    reg.rebind (info.name, object);
  }   
  
  public static void unbind (String url)
      throws NEMalformattedURLException, NERemoteException, NENotBoundException, 
             NEAccessException, NERegistryNotFoundException {
    NEURLInfo info = parseURL (url);

    NERegistry reg = NERegistryLocator.getRegistry (info.name, info.port);    
    reg.unbind (info.name);
  }    
  
  private static class NEURLInfo {
    public String host;
    public String name;
    public int port;
  }
}
