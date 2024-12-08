package steamapi;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
  public static void main(String[] args) {
    List<Integer> population = new ArrayList<>();
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);
    population.add(19999);

    int result = population.stream().mapToInt(Integer::intValue).sum();
    System.out.println(result);
  }
}
