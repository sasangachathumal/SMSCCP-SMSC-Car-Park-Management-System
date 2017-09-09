package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.Customer;
import java.io.*;
import java.rmi.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFileAccess {
    
    private File customerdata;
    
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public CustomerFileAccess() {
        try {
            this.customerdata = FilePathCreater.getFilePath("Customer");
        } catch (IOException ex) {
            Logger.getLogger(CustomerFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addCustomer(Customer customer)throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            
            try {
                
                if (!customerdata.exists()) {
                    customerdata.createNewFile();
                }
                
                String newData = customer.getCustomerNIC()+"#"+
                        customer.getCustomerName()+"#"+
                        customer.getCustomerTelephone()+"#"+
                        customer.getCustomerVehicalNo()+"\n";
                
                fileWriter = new FileWriter(customerdata,true);
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
    
    public boolean deleteCustomer(String nic)throws RemoteException, ClassNotFoundException, IOException {
        
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (customerdata.exists()) {
                    fileReader = new FileReader(customerdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = "";
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (nic.equals(data[0])) {
                            continue;
                        }
                        document += line + "\n";
                    }
                    fileWriter = new FileWriter(customerdata, false);
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
    
    public Customer searchCustomer(String nic)throws RemoteException, ClassNotFoundException, IOException {
        
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            Customer customer = null;
            try {
                if (customerdata.exists()) {
                    fileReader = new FileReader(customerdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (nic.equals(data[0])) {
                            customer = new Customer(data[1], data[0], data[2], data[3]);
                            break;
                        }
                    }
                    return customer;
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
    
    public ArrayList<Customer> getAllCustomer()throws RemoteException, ClassNotFoundException, IOException {
        
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<Customer> CustomerList = null;
            try {
                if (customerdata.exists()) {
                    fileReader = new FileReader(customerdata);
                    bufferedReader = new BufferedReader(fileReader);
                    CustomerList = new ArrayList<>();
                    String line;
                    Customer customer;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        customer = new Customer(data[1], data[0], data[2], data[3]);
                        CustomerList.add(customer);
                    }
                }
                return CustomerList;
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
