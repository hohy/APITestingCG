/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents API of library or framework. Contains classes and methods library
 * provides to client.
 *
 * @author Jan Hýbl
 */
public class API extends APIItem {

    private SortedSet<APIPackage> packages;
    private String version;

    public API(String name) {
        super.name = name;
        packages = new TreeSet<>();
        version = "";
    }

    public API(String name, String version) {
        super.name = name;
        packages = new TreeSet<>();
        this.version = version;
    }

    public void addPackage(APIPackage pkg) {
        packages.add(pkg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(version).append(":\n");
        for (APIPackage p : packages) sb.append(p).append('\n');
        return sb.toString().substring(0, sb.length() - 1);
    }

    public SortedSet<APIPackage> getPackages() {
        return packages;
    }

    @Override
    public void accept(IAPIVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final API other = (API) obj;
        return !(this.packages != other.packages && (this.packages == null || !this.packages.equals(other.packages)));
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Finds class with given name in API or classpath.
     * Current version is not nicely implemented - it searches the API sequentially, so it's slow.
     * But it's best way how it can be done in current APIModel. It would be better, if classes
     * were been stored in map, where they could be accessed directly by name.
     * <p/>
     * If wanted class is not part of an API, method try to load the class with class loader.
     * It loading is successful, loaded class converted to APIClass object using ByteCode scanner.
     *
     * @param className full class name
     * @return class with given name
     */
    public APIClass findClass(String className) throws ClassNotFoundException {

        try {
            return findPrimitive(className);
        } catch (ClassNotFoundException e) {
        }

        for (APIPackage pckg : packages) {
            for (APIClass cls : pckg.getClasses()) {
                if (cls.getFullName().equals(className)) {
                    return cls;
                } else {
                    APIClass result = findNestedClass(className, cls);
                    if (result != null) return result;
                }
            }
        }

        // class is not part of API, try load it with class loader.
        Class loadedClass = Class.forName(className);
        return new APIClass(loadedClass);
    }

    /**
     * Same as findClass(String className), but APIType.getFlatName() is used as a class name for class loader.
     * @param type     type of the parameter
     * @return
     * @throws ClassNotFoundException
     */
    public APIClass findClass(APIType type) throws ClassNotFoundException {

        try {
            return findPrimitive(type.getName());
        } catch (ClassNotFoundException e) {
        }

        for (APIPackage pckg : packages) {
            for (APIClass cls : pckg.getClasses()) {
                if (cls.getFullName().equals(type.getName())) {
                    return cls;
                } else {
                    APIClass result = findNestedClass(type.getName(), cls);
                    if (result != null) return result;
                }
            }
        }

        // class is not part of API, try load it with class loader.
        if(!type.isArray()) return new APIClass(Class.forName(type.getFlatName()));
        else return new APIClass(Class.forName(type.getName()));

    }

    public APIClass findNestedClass(String className, APIClass cls) {
        for (APIClass nestedClass : cls.getNestedClasses()) {
            if (nestedClass.getFullName().equals(className)) {
                return nestedClass;
            } else {
                APIClass c = findNestedClass(className, nestedClass);
                if (c != null) return c;
            }
        }
        return null;
    }

    public APIClass findPrimitive(String name) throws ClassNotFoundException {
        switch (name) {
            case "byte":
                return new APIClass(byte.class);
            case "short":
                return new APIClass(short.class);
            case "int":
                return new APIClass(int.class);
            case "long":
                return new APIClass(long.class);
            case "float":
                return new APIClass(float.class);
            case "double":
                return new APIClass(double.class);
            case "boolean":
                return new APIClass(boolean.class);
            case "char":
                return new APIClass(char.class);
            case "void":
                return new APIClass(Void.class);
            case "null":
                return new APIClass("null");
        }
        throw new ClassNotFoundException("Not a primitive type");
    }

}
