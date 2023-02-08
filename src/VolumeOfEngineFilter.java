import java.util.ArrayList;
import java.util.List;

public class VolumeOfEngineFilter implements AutomobileFilter {

    private double volumeOfEngine;

    public void setVolumeOfEngine(double volumeOfEngine) {
        this.volumeOfEngine = volumeOfEngine;
    }

    @Override
    public List<Automobile> filter(List<Automobile> automobiles) {
        List<Automobile> filteredAutomobiles = new ArrayList<>();
        for (Automobile automobile : automobiles) {
            if (automobile.getVolumeOfEngine() == volumeOfEngine) {
                filteredAutomobiles.add(automobile);
            }
        }
        return filteredAutomobiles;
    }
}
