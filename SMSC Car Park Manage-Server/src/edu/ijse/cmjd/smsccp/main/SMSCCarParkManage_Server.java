package edu.ijse.cmjd.smsccp.main;

import edu.ijse.cmjd.smsccp.remotefactory.impl.RemoteFactoryImpl;
import java.rmi.*;
import java.rmi.registry.*;

public class SMSCCarParkManage_Server {

    private static boolean ifServerStart;

    public static boolean startServer() {
        try {
            Registry thogaKadeRegistry = LocateRegistry.createRegistry(5050);
            thogaKadeRegistry.rebind("SMSCCP", new RemoteFactoryImpl());
            ifServerStart = true;
        } catch (RemoteException ex) {
            ifServerStart = false;
        }
        return ifServerStart;
    }

}
