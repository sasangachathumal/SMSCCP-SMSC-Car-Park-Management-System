package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.controller.CustomerController;
import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservFileAccess {

    private File reservdata;

    public ReservFileAccess() {
        try {
            this.reservdata = FilePathCreater.getFilePath("Reserv");
        } catch (IOException ex) {
            Logger.getLogger(ReservFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean setReserv(String reservKey, CustomerController customerController) throws RemoteException, ClassNotFoundException, IOException {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {

            if (!reservdata.exists()) {
                reservdata.createNewFile();
            }

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);

            String newData = format + "#"
                    + reservKey + "#"
                    + customerController.toString() + "\n";

            fileWriter = new FileWriter(reservdata, true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.write(newData);

        } finally {
            if (fileWriter != null && printWriter != null) {
                fileWriter.close();
                printWriter.close();
            }
        }

        return true;
    }

    public boolean removeReserv(String reservKey, CustomerController customerController) throws RemoteException, ClassNotFoundException, IOException {

        BufferedReader bufferedReader = null;
        BufferedWriter bufferWriter = null;
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        try {
            if (reservdata.exists()) {
                fileReader = new FileReader(reservdata);
                bufferedReader = new BufferedReader(fileReader);
                String line = "";
                String document = "";
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = dateFormat.format(date);
                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split("#");
                    if (format.equals(data[0])) {
                        if (reservKey.equals(data[1])) {
                            if (customerController.toString().equals(data[2])) {
                                continue;
                            }
                        }
                    }
                    document += line + "\n";
                }
                fileWriter = new FileWriter(reservdata, false);
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
    }

/*    public ArrayList<Section> getAllSections() throws RemoteException, ClassNotFoundException, IOException {
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
    }*/
    
}
