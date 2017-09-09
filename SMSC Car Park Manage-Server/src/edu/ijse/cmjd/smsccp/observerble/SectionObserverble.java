package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.Section;
import edu.ijse.cmjd.smsccp.observer.SectionObserver;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SectionObserverble {
    
    private String message;
    
    private Section section;
    
    private static ArrayList<SectionObserver> observerList = new ArrayList<>();
    
    public void addSectionObserver(SectionObserver sectionObserver) {
        observerList.add(sectionObserver);
    }
    
    public void removeSectionObserver(SectionObserver sectionObserver) {
        observerList.remove(sectionObserver);
    }
    
    public void notifySectionMessages() throws RemoteException {
        for (SectionObserver sectionObserver : observerList) {
            sectionObserver.setMessage(message);
        }
    }
    
    public void notifySection() throws RemoteException {
        for (SectionObserver sectionObserver : observerList) {
            sectionObserver.setSection(section);
        }
    }
    
    public void setMessage(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifySectionMessages();
        }
    }
    
    public void setSection(Section section) throws RemoteException {
        if(this.section != section){
            this.section = section;
            notifySection();
        }
    }
    
}
