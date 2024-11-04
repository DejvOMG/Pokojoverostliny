import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate lastWatered;
    private int wateringFrequency;

    public Plant(String name, String notes, LocalDate planted, LocalDate lastWatered, int wateringFrequency) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setLastWatered(lastWatered);
        setWateringFrequency(wateringFrequency);
    }

    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() { return name; }
    public String getNotes() { return notes; }
    public LocalDate getPlanted() { return planted; }
    public LocalDate getLastWatered() { return lastWatered; }
    public int getWateringFrequency() { return wateringFrequency; }

    public void setWateringFrequency(int wateringFrequency) throws PlantException {
        if (wateringFrequency <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo.");
        }
        this.wateringFrequency = wateringFrequency;
    }

    public void setLastWatered(LocalDate lastWatered) throws PlantException {
        if (lastWatered.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nesmí být dřívější než datum zasazení.");
        }
        this.lastWatered = lastWatered;
    }

    public String getWateringInfo() {
        LocalDate nextWatering = lastWatered.plusDays(wateringFrequency);
        return "Rostlina: " + name + ", Poslední zálivka: " + lastWatered + ", Další zálivka: " + nextWatering;
    }

    public void doWateringNow() {
        this.lastWatered = LocalDate.now();
    }
}
