@FunctionalInterface
public interface Predicate<T>{
  T test(T t);
  default Integer test2(){
    return 1;
  };
}
