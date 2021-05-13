package src;

import java.util.*;

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

    public void display() {
        for (int i = 0; i < this.CAPACITY; i++) {
            if (this.values[i] == null) {
                System.out.printf("at %d - NULL\n", i);
                continue;
            }
            System.out.printf("at %d - ", i);
            for (int j = 0; j < this.values[i].size; j++) {
                Stock temp = this.values[i].items.get(j);
                System.out.printf(" { %s } ", temp.getSymbol());
            }
            System.out.println();
        }
    }
}

class StockList {
    public LinkedList<Stock> items; // stores stocks with same hash values
    public int size; // size of the linked list of stocks

    public StockList() {
        this.items = new LinkedList<Stock>();
        this.size = 0;
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
                break;
            }
            index++;
        }
        if (index < this.items.size()) { // if stock is already in list, update
            double temp = this.items.get(index).getRange(fileNumber) 
                + stock.getRange(fileNumber);
            this.items.get(index).setRange(fileNumber, temp);
        } else { //  stock is new to list, add stock to list
            this.items.add(stock);
            this.size++;
        }
        return true;
    }
}


