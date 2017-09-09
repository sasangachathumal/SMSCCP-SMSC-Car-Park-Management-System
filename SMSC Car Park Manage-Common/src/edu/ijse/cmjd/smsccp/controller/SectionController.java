package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.Section;
import edu.ijse.cmjd.smsccp.observer.SectionObserver;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SectionController extends Remote{
       public boolean addSection(Section section)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateSection(Section section)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteSection(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public Section searchSection(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<Section> getAllSection()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveSection(String id) throws RemoteException, IOException;
    
    public boolean releaseSection(String id) throws RemoteException, IOException;
    
    public void addSectionObserver(SectionObserver sectionObserver)throws 
            RemoteException, IOException;
    
    public void removeSectionObserver(SectionObserver sectionObserver)throws 
            RemoteException, IOException;
}
