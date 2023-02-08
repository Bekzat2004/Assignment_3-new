import java.util.ArrayList;
import java.util.List;

public class ColorFilter implements AutomobileFilter {

    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getColor().equals(color)) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
