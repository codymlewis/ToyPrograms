/**
 * IdentityService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.IdentityService.IdentityService_jws;

public interface IdentityService extends java.rmi.Remote {
    public boolean keyInUse(java.lang.String key) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public boolean logout(java.lang.String key) throws java.rmi.RemoteException;
}
