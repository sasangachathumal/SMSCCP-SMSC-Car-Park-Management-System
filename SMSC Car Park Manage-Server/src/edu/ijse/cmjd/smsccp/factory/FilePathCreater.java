package edu.ijse.cmjd.smsccp.factory;

import java.io.File;
import java.io.IOException;

public class FilePathCreater {

    public static File getFilePath(String fileName) throws IOException {
        switch (fileName) {
            case "Customer":
                File fileCustomer = new File("./src/edu/ijse/cmjd/smsccp/datafiles/Customers.txt");
                if (!fileCustomer.exists()) {
                    fileCustomer.createNewFile();
                }
                return fileCustomer;
            case "FixCustomer":
                File fileFixCustomer = new File("./src/edu/ijse/cmjd/smsccp/datafiles/FixCustomers.txt");
                if (!fileFixCustomer.exists()) {
                    fileFixCustomer.createNewFile();
                }
                return fileFixCustomer;
            case "CustomerParking":
                File fileCustomerParking = new File("./src/edu/ijse/cmjd/smsccp/datafiles/CustomerParking.txt");
                if (!fileCustomerParking.exists()) {
                    fileCustomerParking.createNewFile();
                }
                return fileCustomerParking;
            case "Jobrole":
                File fileJobrole = new File("./src/edu/ijse/cmjd/smsccp/datafiles/JobRole.txt");
                if (!fileJobrole.exists()) {
                    fileJobrole.createNewFile();
                }
                return fileJobrole;
            case "ParkingPlace":
                File fileParkingPlace = new File("./src/edu/ijse/cmjd/smsccp/datafiles/ParkingPlace.txt");
                if (!fileParkingPlace.exists()) {
                    fileParkingPlace.createNewFile();
                }
                return fileParkingPlace;
            case "Reserv":
                File fileReserv = new File("./src/edu/ijse/cmjd/smsccp/datafiles/Reserv.txt");
                if (!fileReserv.exists()) {
                    fileReserv.createNewFile();
                }
                return fileReserv;
            case "Section":
                File fileSection = new File("./src/edu/ijse/cmjd/smsccp/datafiles/Section.txt");
                if (!fileSection.exists()) {
                    fileSection.createNewFile();
                }
                return fileSection;
            case "Users":
                File fileUsers = new File("./src/edu/ijse/cmjd/smsccp/datafiles/SystemUsers.txt");
                if (!fileUsers.exists()) {
                    fileUsers.createNewFile();
                }
                return fileUsers;
            case "VehicleType":
                File fileVehicleType = new File("./src/edu/ijse/cmjd/smsccp/datafiles/vehicletype.txt");
                if (!fileVehicleType.exists()) {
                    fileVehicleType.createNewFile();
                }
                return fileVehicleType;
            default:
                return null;
        }
    }

}
