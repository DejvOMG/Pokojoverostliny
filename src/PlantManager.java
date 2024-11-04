import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private List<Plant> plants = new ArrayList<>();

    public List<Plant> getPlantList() {
        return new ArrayList<>(plants); 
    }

    public void loadFromFile(String filename) throws PlantException, IOException {
        plants.clear();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            String line;
            int lineNumber = 0;  
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split("\t");
                if (parts.length != 5) {
                    System.out.println("Chybný formát na řádku " + lineNumber + ": očekává se 5 hodnot oddělených tabulátory.");
                    continue;  
                }
                try {
                    String name = parts[0];
                    String notes = parts[1];
                    LocalDate planted = LocalDate.parse(parts[2].trim());
                    LocalDate lastWatered = LocalDate.parse(parts[3].trim());
                    int wateringFrequency = Integer.parseInt(parts[4].trim());
                    plants.add(new Plant(name, notes, planted, lastWatered, wateringFrequency));
                } catch (Exception e) {
                    System.out.println("Chyba při zpracování řádku " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při otevření souboru " + filename + ": " + e.getMessage());
            throw e;
        }
    }

   
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }
}
