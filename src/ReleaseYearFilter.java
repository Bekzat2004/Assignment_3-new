import java.util.ArrayList;
import java.util.List;

public class ReleaseYearFilter implements AutomobileFilter {
    private int fromYear;
    private int toYear;

    public ReleaseYearFilter() {
    }

    public ReleaseYearFilter(int from, int to) {
        this.fromYear = from;
        this.toYear = to;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }


    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getReleaseYear() >= fromYear && automobile.getReleaseYear() <= toYear) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
