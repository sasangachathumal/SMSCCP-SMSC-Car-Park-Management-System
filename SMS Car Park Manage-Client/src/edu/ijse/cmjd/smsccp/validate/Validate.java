/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.smsccp.validate;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Chamith
 */
public class Validate {

    private static String text;

    public static void nicNoValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() > 9) {
            String newText = text.substring(0, 9);
            textField.setText(newText);

        }
    }
    
//    public static void PlaceIDValidate(JTextField textField) {
//        String text1 = textField.getText();
//        
////        text = textField.getText().replaceAll("[\\D]", "");
////        textField.setText(text);
////        if (text.length() > 9) {
////            String newText = text.substring(0, 9);
////            textField.setText(newText);
////
////        }
//    }

    public static void passwordValidation(JPasswordField passwordField) {
        char[] password = passwordField.getPassword();
        text = password.toString();
        if (text.length() > 9) {
            String newText = text.substring(0, 9);
            passwordField.setText(newText);

        }
    }

    public static void numberFormat(JTextField textField) {
        text = textField.getText();
        String newText = String.format("%,(20.2f", text);
        textField.setText(newText);
    }

    public static void tpNoValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 10) {
            String serviceProvider = text.substring(0, 3);
            String number = text.substring(3, 10);
            textField.setText(serviceProvider + "-" + number);
        }
    }

    public static void yearValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 4) {
            String newText = text.substring(0, 4);
            textField.setText(newText);
        }
    }

    public static void ageValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 3) {
            String newText = text.substring(0, 3);
            textField.setText(newText);
        }
    }

    public static void feeValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 4) {
            String newText = text.substring(0, 4);
            textField.setText(newText);
        }
    }
    public static void cashValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 5) {
            String newText = text.substring(0, 4);
            textField.setText(newText);
        }
    }

    public static void dateValidation(JTextField field) {
        text = field.getText().replaceAll("[\\D]", "");
        field.setText(text);
        if (text.length() >= 8) {
            String year = text.substring(0, 4);
            String month = text.substring(4, 6);
            String a2 = text.substring(7);
            String date = text.substring(6, 8);
            field.setText(text);
            field.setText(year + "-" + month + "-" + date);
        }

    }
}
