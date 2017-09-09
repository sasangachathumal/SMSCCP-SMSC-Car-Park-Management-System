package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.Users;
import java.io.*;
import java.rmi.*;
import java.util.concurrent.locks.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserFileAccess {

    private File userdata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public UserFileAccess() {
        try {
            this.userdata = FilePathCreater.getFilePath("Users");
        } catch (IOException ex) {
            Logger.getLogger(UserFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addUsers(Users users) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!userdata.exists()) {
                    userdata.createNewFile();
                }

                String newData = users.getUserId() + "#"
                        + users.getUserName() + "#"
                        + users.getUserPassword() + "\n";

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

    public boolean updateUsers(Users users) throws RemoteException, ClassNotFoundException, IOException {
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
                        if (users.getUserId().equals(data[0])) {
                            data[0] = users.getUserId();
                            data[1] = users.getUserName();
                            data[2] = users.getUserPassword();
                        }
                        document += data[0] + "#" + data[1] + "#" + data[2] + "\n";
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

    public boolean deleteUsers(String id) throws RemoteException, ClassNotFoundException, IOException {
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

    public Users searchUsers(String id) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            Users users = null;
            try {
                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (id.equals(data[1])) {
                            users = new Users(data[0], data[1], data[2]);
                            break;
                        }
                    }
                    return users;
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

    public ArrayList<Users> getAllUsers() throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<Users> userList = null;
            try {
                if (userdata.exists()) {
                    fileReader = new FileReader(userdata);
                    bufferedReader = new BufferedReader(fileReader);
                    userList = new ArrayList<>();
                    String line;
                    Users users;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        users = new Users(data[0], data[1], data[2]);
                        userList.add(users);
                    }
                }
                return userList;
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
