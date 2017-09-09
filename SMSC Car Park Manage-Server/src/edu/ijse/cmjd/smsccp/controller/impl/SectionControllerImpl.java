package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.SectionController;
import edu.ijse.cmjd.smsccp.fileaccess.SectionFileAccess;
import edu.ijse.cmjd.smsccp.model.Section;
import edu.ijse.cmjd.smsccp.observer.SectionObserver;
import edu.ijse.cmjd.smsccp.observerble.SectionObserverble;
import edu.ijse.cmjd.smsccp.reserve.SectionReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SectionControllerImpl extends UnicastRemoteObject implements SectionController {

    private final SectionFileAccess access = new SectionFileAccess();
    private static final SectionObserverble OBSERVERBLE = new SectionObserverble();
    private static final SectionReserver RESERVER = new SectionReserver();
    
    public SectionControllerImpl() throws RemoteException {
        
    }
    
    @Override
    public boolean addSection(Section section) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddSection = access.addSection(section);
        if (isaddSection) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setMessage(new String("New Section Add !!!"));
                        OBSERVERBLE.setSection(section);
                    } catch (RemoteException ex) {
                        Logger.getLogger(SectionControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        } 
        return isaddSection;
    }

    @Override
    public boolean updateSection(Section section) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveSection(section.getSectionName(), this)) {
                boolean isupdateSection = access.updateSection(section);
                if (isupdateSection) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A Section Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(SectionControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateSection;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseSection(section.getSectionName(), this);
        }
    }

    @Override
    public boolean deleteSection(String nic) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveSection(nic, this)) {
                boolean isdeleteSection = access.deleteSection(nic);
                if (isdeleteSection) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A Section Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(SectionControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteSection;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseSection(nic, this);
        }
    }

    @Override
    public Section searchSection(String nic) throws RemoteException, ClassNotFoundException, IOException {
        return access.searchSection(nic);
    }

    @Override
    public ArrayList<Section> getAllSection() throws RemoteException, ClassNotFoundException, IOException {
        return access.getAllSections();
    }

    @Override
    public boolean reserveSection(String id) throws RemoteException, IOException {
        return RESERVER.reserveSection(id, this);
    }

    @Override
    public boolean releaseSection(String id) throws RemoteException, IOException {
        return RESERVER.releaseSection(id, this);
    }

    @Override
    public void addSectionObserver(SectionObserver sectionObserver) throws RemoteException, IOException {
        OBSERVERBLE.addSectionObserver(sectionObserver);
    }

    @Override
    public void removeSectionObserver(SectionObserver sectionObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeSectionObserver(sectionObserver);
    }
    
}
