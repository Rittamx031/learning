public class Test1 {

  public static void main(String[] args) {
    // Predicate<String> predicate = s -> s.length() == 3;
    Calculater<Integer> addition = (a, b) -> a +b;
    Calculater<Integer> subtraction = (a, b) -> a -b;
    Calculater<Integer> multiple = (a, b) -> a *b;
    Calculater<Integer> division = (a, b) -> a / b;
    System.out.println("5/3 = " + division.operate(5, 3));
    System.out.println("5-3 = " + subtraction.operate(5, 3));
    System.out.println("5*3 = " + multiple.operate(5, 3));
    System.out.println("5+3 = " + addition.operate(5, 3));
  }
}
