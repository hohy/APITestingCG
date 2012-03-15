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
        packages = new TreeSet<APIPackage>();
        version = "";
    }

    public API(String name, String version) {
        super.name = name;
        packages = new TreeSet<APIPackage>();
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
     * Finds class with given name in API.
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
        int dotIndex = className.lastIndexOf('.');

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

    public APIClass findNestedClass(String className, APIClass cls) {
        for (APIClass nestedClass : cls.getNestedClasses()) {
            if (nestedClass.getFullName().equals(className)) {
                return nestedClass;
            } else {
                findNestedClass(className, nestedClass);
            }
        }
        return null;
    }
}
