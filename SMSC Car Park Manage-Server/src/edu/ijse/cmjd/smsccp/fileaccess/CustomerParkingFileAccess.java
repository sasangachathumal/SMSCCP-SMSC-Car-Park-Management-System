package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.CustomerParking;
import java.io.*;
import java.rmi.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerParkingFileAccess {

    private File customerParkingdata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public CustomerParkingFileAccess() {
        try {
            this.customerParkingdata = FilePathCreater.getFilePath("CustomerParking");
        } catch (IOException ex) {
            Logger.getLogger(CustomerParkingFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addCustomerParking(CustomerParking customerParking) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!customerParkingdata.exists()) {
                    customerParkingdata.createNewFile();
                }

                String newData = customerParking.getCustomerNIC() + "#"
                        + customerParking.getPlaceId() + "#"
                        + customerParking.getDate() + "#"
                        + customerParking.getArrivalTime() + "#"
                        + customerParking.getLeaveTime() + "\n";

                fileWriter = new FileWriter(customerParkingdata, true);
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

    public boolean updateCustomerParking(CustomerParking customerParking) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (customerParkingdata.exists()) {
                    fileReader = new FileReader(customerParkingdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (customerParking.getCustomerNIC().equals(data[0])) {
                            data[0] = customerParking.getCustomerNIC();
                            data[1] = customerParking.getPlaceId();
                            data[2] = customerParking.getDate();
                            data[3] = customerParking.getArrivalTime();
                            data[4] = customerParking.getLeaveTime();
                        }
                        document += data[0] + "#" + data[1] + "#" + data[2] + "#"
                                + data[3] + "#" + data[4] + "\n";
                    }
                    fileWriter = new FileWriter(customerParkingdata, false);
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

    public boolean deleteCustomerParking(String placeid, String custid, String date) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (customerParkingdata.exists()) {
                    fileReader = new FileReader(customerParkingdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = "";
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (custid.equals(data[0]) && placeid.equals(data[1]) && date.equals(data[2])) {
                            continue;
                        }
                        document += line + "\n";
                    }
                    fileWriter = new FileWriter(customerParkingdata, false);
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

    public ArrayList<CustomerParking> searchCustomerParking(String custid, String date) throws RemoteException, ClassNotFoundException, IOException {
            try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<CustomerParking> customerParkingList = null;
            try {
                if (customerParkingdata.exists()) {
                    fileReader = new FileReader(customerParkingdata);
                    bufferedReader = new BufferedReader(fileReader);
                    customerParkingList = new ArrayList<>();
                    String line;
                    CustomerParking customerParking;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (custid.toLowerCase().equals(data[0].toLowerCase())) {
                            if (date.toLowerCase().equals(data[2].toLowerCase())) {
                                customerParking = new CustomerParking(data[1], data[0], data[2], data[3], data[4]);
                                customerParkingList.add(customerParking);
                            }
                        }
                    }
                }
                return customerParkingList;
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

    public ArrayList<CustomerParking> getAllCustomerParking() throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<CustomerParking> customerParkingList = null;
            try {
                if (customerParkingdata.exists()) {
                    fileReader = new FileReader(customerParkingdata);
                    bufferedReader = new BufferedReader(fileReader);
                    customerParkingList = new ArrayList<>();
                    String line;
                    CustomerParking customerParking;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        customerParking = new CustomerParking(data[1], data[0], data[2], data[3], data[4]);
                        customerParkingList.add(customerParking);
                    }
                }
                return customerParkingList;
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
    
    public ArrayList<CustomerParking> getDalyCustomerParking(String date) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<CustomerParking> customerParkingList = null;
            try {
                if (customerParkingdata.exists()) {
                    fileReader = new FileReader(customerParkingdata);
                    bufferedReader = new BufferedReader(fileReader);
                    customerParkingList = new ArrayList<>();
                    String line;
                    CustomerParking customerParking;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        System.out.println(data[2]);
                        if (date.equals(data[2])) {
                            customerParking = new CustomerParking(data[1], data[0], data[2], data[3], data[4]);
                            customerParkingList.add(customerParking);
                        }
                    }
                }
                return customerParkingList;
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
