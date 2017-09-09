package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.Section;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SectionFileAccess {

    private File Sectiondata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public SectionFileAccess() {
        try {
            this.Sectiondata = FilePathCreater.getFilePath("Section");
        } catch (IOException ex) {
            Logger.getLogger(SectionFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addSection(Section section) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!Sectiondata.exists()) {
                    Sectiondata.createNewFile();
                }

                String newData = section.getSectionName() + "#"
                        + section.getDate() + "\n";

                fileWriter = new FileWriter(Sectiondata, true);
                printWriter = new PrintWriter(fileWriter);

                printWriter.write(newData);

            } finally {
                if (fileWriter != null && printWriter != null) {
                    fileWriter.close();
                    printWriter.close();
                }
            }

            return true;

        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean updateSection(Section section) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (Sectiondata.exists()) {
                    fileReader = new FileReader(Sectiondata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (section.getSectionName().equals(data[0])) {
                            data[0] = section.getSectionName();
                            data[1] = section.getDate();
                        }
                        document += data[0] + "#" + data[1] + "\n";
                    }
                    fileWriter = new FileWriter(Sectiondata, false);
                    bufferWriter = new BufferedWriter(fileWriter);
                    bufferWriter.write(document);
                    bufferWriter.flush();
                }
                return bufferWriter != null;
            } finally {
                if (bufferedReader != null && fileReader != null
                        && bufferWriter != null && fileWriter != null) {
                    bufferedReader.close();
                    fileReader.close();
                    bufferWriter.close();
                    fileWriter.close();
                }
            }
        } finally {

            lock.writeLock().unlock();
        }
    }

    public boolean deleteSection(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (Sectiondata.exists()) {
                    fileReader = new FileReader(Sectiondata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = "";
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (name.equals(data[0])) {
                            continue;
                        }
                        document += line + "\n";
                    }
                    fileWriter = new FileWriter(Sectiondata, false);
                    bufferWriter = new BufferedWriter(fileWriter);
                    bufferWriter.write(document);
                    bufferWriter.flush();
                }
                return bufferWriter != null;
            } finally {
                if (bufferedReader != null && fileReader != null
                        && bufferWriter != null && fileWriter != null) {
                    bufferedReader.close();
                    fileReader.close();
                    bufferWriter.close();
                    fileWriter.close();
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Section searchSection(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            Section section = null;
            try {
                if (Sectiondata.exists()) {
                    fileReader = new FileReader(Sectiondata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (name.equals(data[0])) {
                            section = new Section(data[0], data[1]);
                            break;
                        }
                    }
                    return section;
                } else {
                    return null;
                }

            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public ArrayList<Section> getAllSections() throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<Section> sectionList = null;
            try {
                if (Sectiondata.exists()) {
                    fileReader = new FileReader(Sectiondata);
                    bufferedReader = new BufferedReader(fileReader);
                    sectionList = new ArrayList<>();
                    String line;
                    Section section = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        section = new Section(data[0], data[1]);
                        sectionList.add(section);
                    }
                }
                return sectionList;
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

}
