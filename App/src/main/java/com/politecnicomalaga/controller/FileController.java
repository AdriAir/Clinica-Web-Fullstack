package com.politecnicomalaga.controller;

import com.google.gson.Gson;
import com.politecnicomalaga.model.Clinic;
import java.io.*;
import java.util.Scanner;

public class FileController {

    //TEXT
    public static void writeText(String text, String path) throws IOException {

        FileWriter fileWriter = null;
        PrintWriter printWriter;

        try {
            fileWriter = new FileWriter(path);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.flush();
            fileWriter.close();
            fileWriter = null;
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
    public static String readText(String path) throws IOException {

        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        Scanner scanner;

        try {
            fileReader = new FileReader(path);
            scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                text.append(scanner.nextLine()).append("\n");
            }

            fileReader.close();
            fileReader = null;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return text.toString();
    }

    //JSON
    public static void writeJson(Clinic clinic, String path) throws IOException {
        writeText(new Gson().toJson(clinic), path);
    }
    public static Clinic readJson(String json) {
        return new Gson().fromJson(json, Clinic.class);
    }
}
