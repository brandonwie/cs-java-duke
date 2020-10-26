import java.util.Random;

public class DiceRolling {
  public void simpleSimulate(int rolls) {
    Random rand = new Random();
    int twos = 0;
    int twelves = 0;

    for (int k = 0; k < rolls; k++) {
      int d1 = rand.nextInt(6) + 1;
      int d2 = rand.nextInt(6) + 1;
      if (d1 + d2 == 2) {
        twos += 1;
      } else if (d1 + d2 == 12) {
        twelves += 1;
      }
    }
    System.out.println("twos: " + twos + "\t" + "twelves: " + twelves);
  }

  public void simulate(int rolls) {
    Random rand = new Random();
    // add 1 more to apply result to the same value of index
    int[] counts = new int[12 + 1];

    for (int k = 0; k < rolls; k++) {
      int d1 = rand.nextInt(6) + 1;
      int d2 = rand.nextInt(6) + 1;
      counts[d1 + d2] += 1;
    }

    for (int k = 2; k <= 12; k++) {
      System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k] / rolls);
    }
  }
}
