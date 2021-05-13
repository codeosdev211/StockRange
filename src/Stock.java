package src;

/**
  * @author Jayesh Mudliyar
  */
public class Stock {
    private String symbol;
    private double ranges[];
    private String timeStamps[];

    public Stock (int fileCount) {
        this.ranges = new double[fileCount];
        this.timeStamps = new String[fileCount];
    }

    public void setSymbol(String value) {
        this.symbol = value;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setRange(int fileNumber, double value) {
        this.ranges[fileNumber] = value;
    }

    public double getRange(int fileNumber) {
        return this.ranges[fileNumber];
    }

    public void setTimeStamp(int fileNumber, String value) {
        this.timeStamps[fileNumber] = value;
    }

    public String getTimeStamp(int fileNumber) {
        return this.timeStamps[fileNumber];
    }
}

