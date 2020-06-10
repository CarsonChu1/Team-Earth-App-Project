package com.example.earthteamproject;


import java.io.*;
import java.util.*;
/**-WARNING- This program contains essentially no error handling code. With mediocre power, comes mediocre responsibility**/


public class ProduceTool {

    public static void main(String[] args) throws IOException {
        String rawFile = "";
        String[] parsedProduceString = new String[5];
        Produce[] produceOBJArray = new Produce[10];
        FileOutputStream outStream = null;
        ObjectOutputStream objStream = null;
        Scanner inScanner = new Scanner(System.in);
        System.out.println("Enter the absolute path for input text file:");
        File inFile = new File(inScanner.nextLine());
        System.out.println("File opened successfully");
        Scanner fileScanner = new Scanner(inFile);

        while (fileScanner.hasNext()) {
            rawFile = rawFile + fileScanner.nextLine();
        }


        String[] unparsedProduce = rawFile.split("\\|");
        for (int i = 0; i < unparsedProduce.length; i++) {
            parsedProduceString = unparsedProduce[i].split("}");
            String name = parsedProduceString[0];
            String descShort = parsedProduceString[1];
            String descLong = parsedProduceString[2];
            String imgURL = parsedProduceString[3];
            Double date = Double.parseDouble(parsedProduceString[4]);
            produceOBJArray[i] = new Produce(name, descLong, descShort, imgURL, date);
        }

        System.out.println("Objects successfully parsed from file");


        for (int i = 0; i < unparsedProduce.length; i++)
            try {
                outStream = new FileOutputStream(produceOBJArray[i].getName() + ".bin");
                objStream = new ObjectOutputStream(outStream);
                objStream.writeObject(produceOBJArray[i]);
                objStream.close();
            } catch (Exception ex) {
                System.out.println("Saving Failed!");
                ex.printStackTrace();
            }
        System.out.println("Successfully Saved!");


    }
}


