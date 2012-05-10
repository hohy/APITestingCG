package lib;

import java.util.List;

public class Fields4 {

    public PublicNestedClass nested1;
    public PublicNestedClass<String> nested2;
    
    public ProtectedNestedClass nested3;
    public ProtectedNestedClass<String> nested4;
    
    public PrivateNestedClass nested5;
    public PrivateNestedClass nested6;
    
    public List<PublicNestedClass> nested7;
    public List<ProtectedNestedClass> nested8;
    public List<PrivateNestedClass> nested9;
    
    public class PublicNestedClass <G> {
    
    }
    
    protected class ProtectedNestedClass <H> {
    
    }
    
    private class PrivateNestedClass <I> {
    
    }
    
}