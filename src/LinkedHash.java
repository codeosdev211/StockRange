package src;

import java.util.*;
import java.io.*;

/** 
  * @author Jayesh Mudliyar
  */

public class LinkedHash {
    private StockList values[]; // stores linked lists of stocks
    private int size; // size of the values array;
    private final int CAPACITY = 100;
    public int fileNumber = 0;

    public LinkedHash() {
        this.values = new StockList[this.CAPACITY];
        this.size = 0;
        // initializing stock objects
        for (int index = 0; index < this.CAPACITY; index++) {  
            this.values[index] = new StockList();
        }
    }

    /* calculates index for a symbol */
    private int hash(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            return -1;
        }
        int asciiSum = 0;
        // calculates sum of all ascii values in a word (symbol in this case) 
        for (int index = 0; index < symbol.length(); index++) {
            asciiSum += (int) ((char) symbol.charAt(index));
        }
        return asciiSum % this.CAPACITY;
    }

    public void insert(Stock stock) {
        if (stock == null) {
            return;
        }
        int index = hash(stock.getSymbol()); // gets index from hash function
        // updates or adds stock in a list
        this.values[index].update(stock, fileNumber);
    }

    public void writeTable() throws Exception {
        FileWriter writer = new FileWriter("Stocks.csv");
        writer.write("TIMESTAMP,SYMBOL,RANGE,RUNNING_TOTAL\n");
        String line = "";
        double range = 0.0;
        for (int row = 0; row < this.CAPACITY; row++) {
           if (this.values[row] == null) {
              continue;
           } 
           for (int stk = 0; stk < this.values[row].size; stk++) {
               Stock temp = this.values[row].items.get(stk);
               line = "";
               range = 0.0;
               for (int each = 0; each < this.fileNumber; each++) {
                   if (temp.getTimeStamp(each) == null) {
                       continue;
                   }
                   range += temp.getRange(each);
                   line = temp.getTimeStamp(each) + "," + 
                       temp.getSymbol() + "," + 
                       temp.getRange(each) + "," +
                       range + "\n";
                   writer.write(line);
               }
           }
        }
        writer.close();
    }

}

class StockList {
    public LinkedList<Stock> items; // stores stocks with same hash values
    public int size; // size of the linked list of stocks
    private int prevFileNum;

    public StockList() {
        this.items = new LinkedList<Stock>();
        this.size = 0;
        this.prevFileNum = 0;
    }

    /* checks if stock is already inserted, if yes then updates range
       else adds the stock to the list;
       returns true is everything went fine else false */
    public boolean update(Stock stock, int fileNumber) {
        if (stock == null) {
            return false;
        }
        int index = 0;
        while (index < this.size) {
            if (this.items.get(index).getSymbol().equals(stock.getSymbol())) {
                double temp = this.items.get(index).getRange(prevFileNum);
                if (prevFileNum == fileNumber) {
                    temp += stock.getRange(0);
                    this.items.get(index).setRange(fileNumber, temp);
                } else {
                    this.items.get(index).setRange(fileNumber, 
                            stock.getRange(0));
                    this.prevFileNum++;
                }
                this.items.get(index).setTimeStamp(fileNumber, 
                        stock.getTimeStamp(0));
                return true;
            }
            index++;
        }
        this.items.add(stock);
        this.size++;
        return true;
    }
}


