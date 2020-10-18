public class App {
  public static void main(String[] args) {
    Part1 part1 = new Part1();
    part1.printAllGenes("ATGCCCTAGAAATGCCCTAA");
    Part2 part2 = new Part2();
    part2.testHowMany();
    // Quiz #1
    Part3 part3 = new Part3();
    part3.printAllGenes("AATGCTAACTAGCTGACTAAT");
    // Debug #1
    Debug1 debug1 = new Debug1();
    debug1.test();
    // Debug #2
    Debug2 debug2 = new Debug2();
    debug2.test();
  }
}
