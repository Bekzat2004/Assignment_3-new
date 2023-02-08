import java.util.ArrayList;
import java.util.List;

public class BrandFilter implements AutomobileFilter {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getBrand().equals(brand)) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
