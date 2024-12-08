@FunctionalInterface
public interface Calculater<T> {
  T operate(T a, T b);
}
