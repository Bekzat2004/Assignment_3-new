public abstract class Transport {
    String color;
    String region;
    double price;
    double volumeOfEngine;
    double weight;
    boolean isNew;

    public Transport(String color, double price, double volumeOfEngine) {
        this.color = color;
        this.price = price;
        this.volumeOfEngine = volumeOfEngine;
    }

    public Transport() {

    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public double getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(double volumeOfEngine) {
        this.volumeOfEngine = volumeOfEngine;
    }



    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        this.isNew = aNew;
    }
}
