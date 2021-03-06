/*
 * Class      : NERemoteObjectStub.java
   * Authors    : Eugene Choi, Norbert Chu
   * Andrew IDs : dechoi, nrchu
 * Description: This class encapsulates all exceptions generated during the
 *              communication between the client and server side of RMI.
 */

import java.io.*;
import java.net.*;
import java.lang.reflect.*;

public class NERemoteObjectStub implements NERemote {
  protected String hostAddress;
  protected int port;
  protected int key;
  protected String remoteInterfaceName;
  
  public void init (NERemoteObjectReference objectRef) {
    hostAddress = objectRef.getHostAddress ();
    port = objectRef.getPort ();
    key = objectRef.getKey ();
    remoteInterfaceName = objectRef.getRemoteInterfaceName ();
  }
  
  public NERemoteObjectReference getRemoteObjectReference () {
    return new NERemoteObjectReference 
        (hostAddress, port, key, remoteInterfaceName);
  }
  
  public int getKey () {
    return key;
  }
}
