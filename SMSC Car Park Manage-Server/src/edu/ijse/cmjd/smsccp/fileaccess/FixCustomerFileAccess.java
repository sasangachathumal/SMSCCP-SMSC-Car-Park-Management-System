package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.FixCustomer;
import java.io.*;
import java.rmi.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FixCustomerFileAccess {

    private File userdata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public FixCustomerFileAccess() {
        try {
            this.userdata = FilePathCreater.getFilePath("FixCustomer");
        } catch (IOException ex) {
            Logger.getLogger(FixCustomerFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addFixCustomer(FixCustomer fixCustomer) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!userdata.exists()) {
                    userdata.createNewFile();
                }

                String newData = fixCustomer.getFxCustomerINC() + "#"
                        + fixCustomer.getFxCustomerName() + "#"
                        + fixCustomer.getFxCustomerJobRole() + "#"
                        + fixCustomer.getFxCustomerSection() + "#"
                        + fixCustomer.getFxCustomerTelephone() + "\n";

                fileWriter = new FileWriter(userdata, true);
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

    public boolean updateFixCustomer(FixCustomer fixCustomer) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (fixCustomer.getFxCustomerINC().equals(data[0])) {
                            data[0] = fixCustomer.getFxCustomerINC();
                            data[1] = fixCustomer.getFxCustomerName();
                            data[2] = fixCustomer.getFxCustomerJobRole();
                            data[3] = fixCustomer.getFxCustomerSection();
                            data[4] = fixCustomer.getFxCustomerTelephone();
                        }
                        document += data[0] + "#" + data[1] + "#" + data[2] + "#"
                                + data[3] + "#" + data[4] + "\n";
                    }
                    fileWriter = new FileWriter(userdata, false);
                    bufferWriter = new BufferedWriter(fileWriter);
                    bufferWriter.write(document);
                    bufferWriter.flush();
                }
                return bufferWriter != null;
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferWriter != null) {
                    bufferWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
        } finally {

            lock.writeLock().unlock();
        }
    }

    public boolean deleteFixCustomer(String id) throws RemoteException, ClassNotFoundException, IOException {

        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = "";
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (id.equals(data[0])) {
                            continue;
                        }
                        document += line + "\n";
                    }
                    fileWriter = new FileWriter(userdata, false);
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

    public FixCustomer searchFixCustomer(String id) throws RemoteException, ClassNotFoundException, IOException {

        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            FixCustomer fixCustomer = null;
            try {
                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (id.equals(data[0])) {
                            fixCustomer = new FixCustomer(data[1], data[0], data[2], data[3], data[4]);
                            break;
                        }
                    }
                    return fixCustomer;
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

    public ArrayList<FixCustomer> getAllFixCustomer() throws RemoteException, ClassNotFoundException, IOException {

        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<FixCustomer> fixCustomerList  = null;
            try {
                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    fixCustomerList = new ArrayList<>();
                    String line;
                    FixCustomer fixCustomer;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        fixCustomer = new FixCustomer(data[1], data[0], data[2], data[3], data[4]);
                        fixCustomerList.add(fixCustomer);
                    }
                }
                return fixCustomerList;
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
