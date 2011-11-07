package testScannerLib.pkga;

/**
 *
 * @author Jan Hýbl
 */
public enum BasicEnum {
    VALA(1), VALB(2), VALC(3);
    
    private final int value;
    
    BasicEnum(int value) {
        this.value = value;
    }   
    
    public void doSomething() {
        
    }    
    
    public int getIntValue() {
        return 1;
    }        
}
