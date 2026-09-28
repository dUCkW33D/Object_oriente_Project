//=============================================================================
// FILE: PassworderS.java - MAIN CLASS
//=============================================================================

package passworders;

import java.util.ArrayList;
import java.util.Scanner;

public class PassworderS {

    private static FileManager fileManager = new FileManager();
    private static int bitShift = 0; // Master decryption key

    public static void main(String[] args) {
        int choice = 1;
        Scanner scanner = new Scanner(System.in);

        while (choice != 0) {
            System.out.println("\n=== Password Manager S ===");
            System.out.println("Current Master Key (Bitshift): " + bitShift);
            System.out.println("1. Enter password and account name");
            System.out.println("2. Retrieve passwords");
            System.out.println("3. Change Master Key (Bitshift)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Add New Account ---");
                        OnePassOneAcc acc = OnePassOneAcc.fromUserInput(bitShift);
                        fileManager.add(acc);
                        fileManager.save();
                        System.out.println("Account saved successfully!");
                    }
                    case 2 -> {
                        System.out.println("\n--- Retrieve Passwords ---");
                        ArrayList<EncryptedObject> list = fileManager.load();
                        ObjectOut.displayDecrypted(list, bitShift);
                        System.out.println("\nNote: If passwords look wrong, check your Master Key!");
                    }
                    case 3 -> {
                        System.out.println("\n--- Change Master Key ---");
                        System.out.print("Enter new Master Key (Bitshift): ");
                        try {
                            int newShift = Integer.parseInt(scanner.nextLine());
                            bitShift = newShift;
                            System.out.println("Master Key changed to: " + bitShift);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input, Master Key remains: " + bitShift);
                        }
                    }
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        scanner.close();
    }
}