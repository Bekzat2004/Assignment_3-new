import java.util.ArrayList;
import java.util.List;

public class ConditionFilter implements AutomobileFilter {
    private boolean isNew;

    public void setCondition(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.isNew() == isNew) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
