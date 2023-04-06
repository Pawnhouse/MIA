package org.example.model;


public class CrimeCriminal implements Model {
    private int crimeId;
    private int criminalId;

    public CrimeCriminal() {
    }

    public CrimeCriminal(int crimeId, int criminalId) {
        this.crimeId = crimeId;
        this.criminalId = criminalId;
    }

    @Override
    public void show() {
        System.out.printf("crime_id: %d\tcriminal_id: %d\n", crimeId, criminalId);
    }

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }

    public int getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(int criminalId) {
        this.criminalId = criminalId;
    }
}
