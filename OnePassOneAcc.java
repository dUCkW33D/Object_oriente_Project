//=============================================================================
// FILE: OnePassOneAcc.java
//=============================================================================

package passworders;

import java.util.Scanner;

/**
 * Subclass of EncryptedObject for single account-password input.
 * Allows user to enter account and password via console input.
 * 
 * Future subclasses could handle password generation, 2FA, etc.
 * 
 * @author Pat Rick
 */
public class OnePassOneAcc extends EncryptedObject {

    public OnePassOneAcc(String accountName, String password, int shiftNumber) {
        super(accountName, password, shiftNumber);
    }

    // Factory-style constructor using console input
    public static OnePassOneAcc fromUserInput(int bitShift) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Name: ");
        String accountName = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return new OnePassOneAcc(accountName, password, bitShift);
    }
}