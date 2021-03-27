package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {




    public static void main(String[] args) {
        // write your code here
        String separator = File.separator;
        File inputFile = new File(separator + "Users" + separator + "elmirabajgulova" + separator + "Desktop" + separator + "dataexport_20210320T064822.csv");
        File outputFile = new File("parseWeatherInformation.txt");

        ArrayList<String> information = getText(inputFile);

        if (information != null) {
            Data data = new Data(parse(information));

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
                data.getDayArrayList().forEach(x -> {
                    try {
                        bufferedWriter.write(String.valueOf(x));
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<String> parse (ArrayList<String> information) {
        //убираем  не нужную информацию
        while (!information.get(0).contains("T0000")) {
            information.remove(0);
        }
        return information;
    }

    private static ArrayList<String> getText (File file){
        ArrayList<String> strings = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String s = bufferedReader.readLine();
            strings = new ArrayList<>();
            while (s != null) {
                strings.add(s);
                s = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("No Text");
        }
        return strings;


    }
}


