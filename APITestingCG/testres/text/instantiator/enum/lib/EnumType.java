package lib;

public enum EnumType {        
    A(1),
    B(2),
    C(3);

    private final int value;
    
    EnumType(int value) {
        this.value = value;
    }
    private double value()   { return value; }
    public static final double FIELD = 6.67;

    public double method() {
        return FIELD * value;
    }
}