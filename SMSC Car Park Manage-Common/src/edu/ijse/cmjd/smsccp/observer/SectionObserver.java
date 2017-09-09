package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.Section;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SectionObserver extends Remote{
    public void setMessage(String message) throws RemoteException;
    public void setSection(Section section) throws RemoteException;
}
