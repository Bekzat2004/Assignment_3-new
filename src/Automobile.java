public class Automobile extends Transport {
    private boolean isAutomatic;
    private String brand;
    private String model;

    public Automobile(boolean isAutomatic, int releaseYear, boolean isNew, String color, double price,
                      double volumeOfEngine, String brand, String model) {
        super(color, price, volumeOfEngine, isNew, releaseYear);
        this.isAutomatic = isAutomatic;
        this.brand = brand;
        this.model = model;
    }


    public boolean isAutomatic() {
        return isAutomatic;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    /*@Override
    public String toString() {
        return "Automobile{" +
                "isAutomatic=" + isAutomatic +
                ", releaseYear=" + releaseYear +
                ", isNew=" + isNew +
                '}';
    }*/
}
