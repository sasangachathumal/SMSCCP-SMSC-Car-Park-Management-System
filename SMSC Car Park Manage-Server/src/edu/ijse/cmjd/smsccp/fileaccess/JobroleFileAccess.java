package edu.ijse.cmjd.smsccp.fileaccess;

import edu.ijse.cmjd.smsccp.factory.FilePathCreater;
import edu.ijse.cmjd.smsccp.model.JobRole;
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

public class JobroleFileAccess {

    private File JobRoledata;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public JobroleFileAccess() {
        try {
            this.JobRoledata = FilePathCreater.getFilePath("Jobrole");
        } catch (IOException ex) {
            Logger.getLogger(JobroleFileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addJobRole(JobRole jobRole) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;

            try {

                if (!JobRoledata.exists()) {
                    JobRoledata.createNewFile();
                }

                String newData = jobRole.getJobRoleName() + "#"
                        + jobRole.getDate() + "\n";

                fileWriter = new FileWriter(JobRoledata, true);
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

    public boolean updateJobRole(JobRole jobRole) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;

            try {

                if (JobRoledata.exists()) {
                    fileReader = new FileReader(JobRoledata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    String document = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (jobRole.getJobRoleName().equals(data[0])) {
                            data[0] = jobRole.getJobRoleName();
                            data[1] = jobRole.getDate();
                        }
                        document += data[0] + "#" + data[1] + "\n";
                    }
                    fileWriter = new FileWriter(JobRoledata, false);
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

    public boolean deleteJobRole(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.writeLock().lock();

            BufferedReader bufferedReader = null;
            BufferedWriter bufferWriter = null;
            FileWriter fileWriter = null;
            FileReader fileReader = null;
            try {
                if (JobRoledata.exists()) {
                    fileReader = new FileReader(JobRoledata);
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
                    fileWriter = new FileWriter(JobRoledata, false);
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

    public JobRole searchJobRole(String name) throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            JobRole jobRole = null;
            try {
                if (JobRoledata.exists()) {
                    fileReader = new FileReader(JobRoledata);
                    bufferedReader = new BufferedReader(fileReader);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        if (name.equals(data[0])) {
                            jobRole = new JobRole(data[0], data[1]);
                            break;
                        }
                    }
                    return jobRole;
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

    public ArrayList<JobRole> getAllJobRoles() throws RemoteException, ClassNotFoundException, IOException {
        try {
            lock.readLock().lock();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            ArrayList<JobRole> JobRoleList = null;
            try {
                if (JobRoledata.exists()) {
                    fileReader = new FileReader(JobRoledata);
                    bufferedReader = new BufferedReader(fileReader);
                    JobRoleList = new ArrayList<>();
                    String line;
                    JobRole jobRole = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("#");
                        jobRole = new JobRole(data[0], data[1]);
                        JobRoleList.add(jobRole);
                    }
                }
                return JobRoleList;
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
