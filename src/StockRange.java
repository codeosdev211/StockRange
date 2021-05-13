package src;

import java.util.*;
import java.io.*;

/** 
  * @author Jayesh Mudliyar
  */

public class StockRange {
    private final static String resultFile = "Stock.csv";

    public static void main(String args[]) {
        FileReader fileReader = null; 
        FileWriter fileWriter = null;
        BufferedReader buffReader = null; 
        String eachLine = ""; // string for each line from a file
        int lineCount = 0; // total number of files
        double tempRange = 0.00; // store calculated range
        // holds words from line  split using ","
        String columnNames[], columnValues[]; 
        Stock stock = null; // stock model object
        LinkedHash hashTable = new LinkedHash(); // linked hash table  
        try {
            fileWriter = new FileWriter(resultFile); 
            // reading filenames one by one from arguments
            for (int index = 0; index < args.length; index++) {
                fileReader = new FileReader(args[index]); 
                buffReader = new BufferedReader(fileReader);
                columnNames = buffReader.readLine().split(","); 
                while ((eachLine = buffReader.readLine()) != null) {
                    columnValues = eachLine.split(",");
                    stock = new Stock(args.length);
                    stock.setSymbol(columnValues[0]);
                    tempRange = Double.parseDouble(columnValues[3]) -
                        Double.parseDouble(columnValues[4]);
                    stock.setRange(0, tempRange);
                    stock.setTimeStamp(0, columnValues[10]);
                    hashTable.insert(stock); //adding stock to hash table
                }
                hashTable.fileNumber++;
                fileReader.close();
            }
            hashTable.display();
            fileWriter.close();
        } catch (Exception error) {
            System.out.println("ERR-> " +error.getMessage());
        }
    }

    public static String listToString(String values[], String character) {
        String result = "";
        for (int index = 0; index < values.length; index++) {
            result += values[index] + character;
        }
        return result;
    }

}


