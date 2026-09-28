package passworders;

import java.io.Serializable;

/**
 * Base class for all encrypted account types.
 * Handles basic data and encryption/decryption logic.
 * 
 * NOTE: shiftNumber is NOT saved - you must remember it to decrypt!
 * 
 * @author Pat Rick
 */
public class EncryptedObject implements Serializable {
    protected String accountName;
    protected String password;
 

    public EncryptedObject(String accountName, String password, int shiftNumber) {
        this.accountName = accountName;
        this.password = encrypt(password, shiftNumber);
    }

    // Simple Caesar-style shift encryption
    protected String encrypt(String input, int shift) {
        if (input == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append((char) (c + shift));
        }
        return sb.toString();
    }

    // Decrypt method - requires the correct shift number
    protected String decrypt(String encrypted, int shift) {
        if (encrypted == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            sb.append((char) (c - shift));
        }
        return sb.toString();
    }

    public String getAccountName() {
        return accountName;
    }

    public String getEncryptedPassword() {
        return password;
    }

    // Now requires shift parameter to decrypt
    public String getDecryptedPassword(int shift) {
        return decrypt(password, shift);
    }

    @Override
    public String toString() {
        return "Account: " + accountName + " | Password (Encrypted): " + password;
    }
}