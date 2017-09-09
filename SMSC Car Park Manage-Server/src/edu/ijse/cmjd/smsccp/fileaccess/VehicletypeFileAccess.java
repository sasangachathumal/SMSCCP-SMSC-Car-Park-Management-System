package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.VehicleType;
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

public class VehicletypeFileAccess {
    

    private File vehicletypedata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public VehicletypeFileAccess() {
        try {
            this.vehicletypedata = FilePathCreater.getFilePath("VehicleType");
        } catch (IOException ex) {
            Logger.getLogger(VehicletypeFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addVehicaleType(VehicleType vehicleType) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!vehicletypedata.exists()) {
                    vehicletypedata.createNewFile();
                }

                String newData = vehicleType.getVehicleType() + "#"
                        + vehicleType.getDate() + "\n";

                fileWriter = new FileWriter(vehicletypedata, true);
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

    public boolean updateVehicaleType(VehicleType vehicleType) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (vehicletypedata.exists()) {
                    fileReader = new FileReader(vehicletypedata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (vehicleType.getVehicleType().equals(data[0])) {
                            data[0] = vehicleType.getVehicleType();
                            data[1] = vehicleType.getDate();
                        }
                        document += data[0] + "#" + data[1] + "\n";
                    }
                    fileWriter = new FileWriter(vehicletypedata, false);
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

    public boolean deleteVehicaleType(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (vehicletypedata.exists()) {
                    fileReader = new FileReader(vehicletypedata);
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
                    fileWriter = new FileWriter(vehicletypedata, false);
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

    public VehicleType searchVehicaleType(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            VehicleType vehicleType = null;
            try {
                if (vehicletypedata.exists()) {
                    fileReader = new FileReader(vehicletypedata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (name.equals(data[0])) {
                            vehicleType = new VehicleType(data[0], data[1]);
                            break;
                        }
                    }
                    return vehicleType;
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

    public ArrayList<VehicleType> getAllVehicaleTypes() throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<VehicleType> vehicleTypeList = null;
            try {
                if (vehicletypedata.exists()) {
                    fileReader = new FileReader(vehicletypedata);
                    bufferedReader = new BufferedReader(fileReader);
                    vehicleTypeList = new ArrayList<>();
                    String line;
                    VehicleType vehicleType = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        vehicleType = new VehicleType(data[0], data[1]);
                        vehicleTypeList.add(vehicleType);
                    }
                }
                return vehicleTypeList;
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
