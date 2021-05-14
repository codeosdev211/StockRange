package src;

import java.util.*;
import java.io.*;

/** 
  * @author Jayesh Mudliyar
  */

public class StockRange {

    public static void main(String args[]) {
        FileReader fileReader = null; 
        BufferedReader buffReader = null; 
        String eachLine = ""; // string for each line from a file
        int lineCount = 0; // total number of files
        double tempRange = 0.00; // store calculated range
        // holds words from line  split using ","
        String columnNames[], columnValues[]; 
        Stock stock = null; // stock model object
        LinkedHash hashTable = new LinkedHash(); // linked hash table  
        try {
            // reading filenames one by one from arguments
            for (int index = 0; index < args.length; index++) {
                fileReader = new FileReader(args[index]); 
                buffReader = new BufferedReader(fileReader);
                columnNames = buffReader.readLine().split(","); 
                // loop through each line of a file
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
            hashTable.writeTable();
        } catch (Exception error) {
            System.out.println("ERR-> " +error.getMessage());
        }
    }
}


