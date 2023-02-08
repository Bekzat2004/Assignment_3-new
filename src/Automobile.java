public class Automobile extends Transport {
    public Automobile(String color, double price, double volumeOfEngine) {
        super(color, price, volumeOfEngine);
    }

    private String color;
    private boolean isAutomatic;
    private int releaseYear;
    private boolean isNew;

    public Automobile(boolean isAutomatic, int releaseYear, boolean isNew) {
        super();
        this.isAutomatic = isAutomatic;
        this.releaseYear = releaseYear;
        this.isNew = isNew;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }


    @Override
    public String getColor() {
        return color;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "isAutomatic=" + isAutomatic +
                ", releaseYear=" + releaseYear +
                ", isNew=" + isNew +
                '}';
    }
}
