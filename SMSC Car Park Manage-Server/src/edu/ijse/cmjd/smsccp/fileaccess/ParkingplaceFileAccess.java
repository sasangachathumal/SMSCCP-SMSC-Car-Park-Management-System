package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.Place;
import java.io.*;
import java.rmi.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingplaceFileAccess {

    private File placedata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ParkingplaceFileAccess() {
        try {
            this.placedata = FilePathCreater.getFilePath("ParkingPlace");
        } catch (IOException ex) {
            Logger.getLogger(ParkingplaceFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addPlace(Place place) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!placedata.exists()) {
                    placedata.createNewFile();
                }

                String newData = place.getPlaceID() + "#"
                        + place.getPlaceVehicalType() + "#"
                        + place.getPlaceSpecial() + "\n";

                fileWriter = new FileWriter(placedata, true);
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

    public boolean updatePlace(Place place) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (placedata.exists()) {
                    fileReader = new FileReader(placedata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (place.getPlaceID().equals(data[0])) {
                            data[0] = place.getPlaceID();
                            data[1] = place.getPlaceVehicalType();
                            data[2] = place.getPlaceSpecial();
                        }
                        document += data[0] + "#" + data[1] + "#" + data[2] + "\n";
                    }
                    fileWriter = new FileWriter(placedata, false);
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

    public boolean deletePlace(String id) throws RemoteException, ClassNotFoundException, IOException {

        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (placedata.exists()) {
                    fileReader = new FileReader(placedata);
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
                    fileWriter = new FileWriter(placedata, false);
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

    public Place searchPlace(String id) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            Place place = null;
            try {
                if (placedata.exists()) {
                    fileReader = new FileReader(placedata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (id.equals(data[0])) {
                            place = new Place(data[0], data[1], data[2]);
                            break;
                        }
                    }
                    return place;
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

    public ArrayList<Place> getAllPlace() throws RemoteException, ClassNotFoundException, IOException {

        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<Place> PlaceList = null;
            try {
                if (placedata.exists()) {
                    fileReader = new FileReader(placedata);
                    bufferedReader = new BufferedReader(fileReader);
                    PlaceList = new ArrayList<>();
                    String line;
                    Place place;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        place = new Place(data[0], data[1], data[2]);
                        PlaceList.add(place);
                    }
                }
                return PlaceList;
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
