package gr.crystalogic.commons.entities;

public enum ProfileType {
    AUTO(0), SIMPLE(1), INTERMEDIATE(2), ADVANCED(3);

    private int numVal;

    ProfileType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
