package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Ticket {
    private String name;
    private String currency;
    private String ends;
    private String info;
    private boolean soldOut;
    private int price;
    private String starts;

    public Ticket() {
    }

    public Ticket(String name, String currency, String ends, String info, boolean soldOut, int price, String starts) {
        this.setName(name);
        this.setCurrency(currency);
        this.setEnds(ends);
        this.setInfo(info);
        this.setSoldOut(soldOut);
        this.setPrice(price);
        this.setStarts(starts);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }
}
