package by.nintendo.datajpa.model;

public enum PetStatus {
    AVAILABLE("AVAILABLE"),
    PENDING("PENDING"),
    SOLD("SOLD");
    private String iteam;

    PetStatus(String iteam) {
        this.iteam = iteam;
    }
}
