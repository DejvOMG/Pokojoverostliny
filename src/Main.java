import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            
            String[] fileNames = {"resources/kvetiny.txt", "resources/kvetiny-spatne-datum.txt", "resources/kvetiny-spatne-frekvence.txt"};

            for (String fileName : fileNames) {
                PlantManager manager = new PlantManager();

                System.out.println("\nNačítání ze souboru: " + fileName);
                try {
                    manager.loadFromFile(fileName);
                    System.out.println("Informace o zálivce pro všechny květiny:");
                    for (Plant plant : manager.getPlantList()) {
                        System.out.println(plant.getWateringInfo());
                    }
                } catch (PlantException e) {
                    System.err.println("Chyba při načítání souboru " + fileName + ": " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Chyba při práci se souborem " + fileName + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
