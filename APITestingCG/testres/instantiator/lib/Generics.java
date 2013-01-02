package lib;

import java.io.IOException;

public abstract class Generics<T> {
  public abstract T read(String reader) throws IOException;
  public abstract void write(String writer, T value) throws IOException;

  public final String toJson(T value) throws IOException {
    return null;
  }

  public final void write2(String out, T value) throws IOException {
  }

  public final T fromJson(String json) throws IOException {
    return null;
  }

  public final T read2(String in) throws IOException {
      return null;
  }

  public T fromJsonElement(String json) {
    return null;
  }
}