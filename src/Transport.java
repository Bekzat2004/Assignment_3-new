public abstract class Transport {
    private String color;
    private double price;
    private double volumeOfEngine;
    private boolean isNew;
    private int releaseYear;

    public Transport(String color, double price, double volumeOfEngine, boolean isNew, int releaseYear) {
        this.color = color;
        this.price = price;
        this.volumeOfEngine = volumeOfEngine;
        this.isNew = isNew;
        this.releaseYear = releaseYear;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public double getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
