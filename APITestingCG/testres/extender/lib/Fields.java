package lib;

import lib.ClassA;

import java.io.File;

public class Fields {

    private int prvtFld = 1;

    protected ClassA protectedFld = null;

    protected static ClassA protectedStaticFld = null;

    protected final ClassA protectedFinalFld = null;

    protected static final ClassA protectedStaticFinalFld = null;

    protected int protectedPrimitiveFld = 2;

    protected static int protectedStaticPrimitiveFld = 2;

    protected final int protectedFinalPrimitiveFld = 2;

    protected static final int protectedStaticFinalPromitiveFld = 2;

    public File publicFld = null;

    public static ClassA publicStaticFld = null;

    public int publicPrimitiveFld = 2;

    public static int publicStaticPrimitiveFld = 2;

    // ruzne typy

    public byte b = 1;

    public short s = 2;

    public int i = 3;

    public long l = 4;

    public float f = 5.1f;

    public double d = 6.1d;

    public char c = '7';

    public String str = "888";

    public boolean bb = false;

}