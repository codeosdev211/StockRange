package src;

/**
  * @author Jayesh Mudliyar
  */
public class Stock {
    private String symbol;
    private double range;
    private String timeStamp;

    public void setSymbol(String value) {
        this.symbol = value;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setRange(double value) {
        this.range = value;
    }

    public double getRange() {
        return this.range;
    }

    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }
}

