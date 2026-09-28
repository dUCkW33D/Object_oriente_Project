//=============================================================================
// FILE: FileManager.java
//=============================================================================

package passworders;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles reading and writing EncryptedObject subclasses to a file.
 * Uses Java serialization to persist data between runs.
 * 
 * @author Pat Rick
 */
public class FileManager {
    private final String filename = "passwords.bat";
    private ArrayList<EncryptedObject> storedObjects = new ArrayList<>();

    public FileManager() {
        load(); // load existing data on startup
    }

    public void add(EncryptedObject obj) {
        storedObjects.add(obj);
    }

    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(storedObjects);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<EncryptedObject> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            storedObjects = (ArrayList<EncryptedObject>) in.readObject();
        } catch (FileNotFoundException e) {
            storedObjects = new ArrayList<>(); // start empty if no file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return storedObjects;
    }
}