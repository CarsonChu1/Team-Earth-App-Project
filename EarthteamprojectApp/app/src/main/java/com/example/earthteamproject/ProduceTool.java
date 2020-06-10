package com.example.earthteamproject;


import java.io.*;
import java.util.*;
/**-WARNING- This program contains essentially no error handling code. With mediocre power, comes mediocre responsibility**/


public class ProduceTool {

    public static void main(String[] args) throws IOException {


        String rawFile = "";
        String[] parsedProduceString;
        FileOutputStream outStream = null;
        ObjectOutputStream objStream = null;
        Scanner inScanner = new Scanner(System.in);
        System.out.println("Enter the absolute path for input text file:");
        File inFile = new File(inScanner.nextLine());
        System.out.println("Enter the amount of items to be parsed:");
        Produce[] produceOBJArray = new Produce[Integer.parseInt(inScanner.nextLine())];
        System.out.println("File opened successfully");
        Scanner fileScanner = new Scanner(inFile);

        //Loading text from file
        while (fileScanner.hasNext()) {
            rawFile = rawFile + fileScanner.nextLine();
        }


        //This will split up multiple produce objects within one text file and add their info to an array
        String[] rawProduce = rawFile.split("\\|");

        //This creates a produce object for each item in rawProduce[]
        for (int i = 0; i < rawProduce.length; i++) {

            //splitting up the info fields by the "}" delimiting char
            parsedProduceString = rawProduce[i].split("}");

            //this could probably be one statement but my brain is smooth
            String name = parsedProduceString[0];
            String descShort = parsedProduceString[1];
            String descLong = parsedProduceString[2];
            String imgURL = parsedProduceString[3];
            Double date = Double.parseDouble(parsedProduceString[4]);

            //populating array with new produce items
            produceOBJArray[i] = new Produce(name, descLong, descShort, imgURL, date);
        }

        System.out.println("Objects successfully parsed from file");


        //saving to file
        for (int i = 0; i < rawProduce.length; i++)
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


