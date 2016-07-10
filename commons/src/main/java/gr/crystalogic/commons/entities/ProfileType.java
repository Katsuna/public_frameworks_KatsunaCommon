package gr.crystalogic.commons.entities;

public enum ProfileType {
    DEFAULT(1), V1(2), V2(3);

    private int numVal;

    ProfileType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
