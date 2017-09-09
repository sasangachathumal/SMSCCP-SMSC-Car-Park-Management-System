package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.Section;
import edu.ijse.cmjd.smsccp.observer.SectionObserver;
import edu.ijse.cmjd.smsccp.view.InputCustomers;
import edu.ijse.cmjd.smsccp.view.inputSection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SectionObserverImpl extends UnicastRemoteObject implements SectionObserver {

    private inputSection inputsection;
    private InputCustomers inputCustomers;
    
    public SectionObserverImpl(InputCustomers inputCustomers) throws RemoteException {
        this.inputCustomers = inputCustomers;
    }
    
    public SectionObserverImpl(inputSection inputsection) throws RemoteException {
        this.inputsection = inputsection;
    }
    
    @Override
    public void setMessage(String message) throws RemoteException {
        inputCustomers.loadSections();
        inputsection.setMessageForNewSection(message);
    }

    @Override
    public void setSection(Section section) throws RemoteException {
        inputCustomers.setNewSectio();
    }
    
}
