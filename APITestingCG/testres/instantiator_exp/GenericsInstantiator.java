
package test.lib;

import java.io.IOException;
import lib.Generics;

public class GenericsInstantiator {


    public <T> T fromJsonElement(Generics<T> instance, String json) {
        return instance.fromJsonElement(json);
    }

    public <T> T fromJson(Generics<T> instance, String json) {
        try {
            return instance.fromJson(json);
        } catch (IOException e) {
        }
        return null;
    }

    public <T> T read2(Generics<T> instance, String in) {
        try {
            return instance.read2(in);
        } catch (IOException e) {
        }
        return null;
    }

    public <T> T read(Generics<T> instance, String reader) {
        try {
            return instance.read(reader);
        } catch (IOException e) {
        }
        return null;
    }

    public <T> String toJson(Generics<T> instance, T value) {
        try {
            return instance.toJson(value);
        } catch (IOException e) {
        }
        return null;
    }

    public <T> void write2(Generics<T> instance, String out, T value) {
        try {
            instance.write2(out, value);
        } catch (IOException e) {
        }
    }

    public <T> void write(Generics<T> instance, String writer, T value) {
        try {
            instance.write(writer, value);
        } catch (IOException e) {
        }
    }

}
