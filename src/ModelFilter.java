import java.util.ArrayList;
import java.util.List;

public class ModelFilter implements AutomobileFilter {

    private String model;

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getModel().equals(model)) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
