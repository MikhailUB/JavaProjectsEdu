package ru.Mikhail.utils;

import java.io.*;

public class MySerializer {
    public static <T extends Serializable> void serialize(String fileName, T object) {
        try (
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
        ) {
            out.writeObject(object);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл " + e.getMessage());
        }
    }

    public static <T extends Serializable> T deserialize(String fileName) {
        try (
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
        ) {
            return (T)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось прочитать файл " + e.getMessage());
        }
        return null;
    }
}
