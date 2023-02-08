import java.util.ArrayList;
import java.util.List;

public class PriceRangeFilter implements AutomobileFilter {

    private int from;
    private int to;

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getPrice() >= from && automobile.getPrice() <= to) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
