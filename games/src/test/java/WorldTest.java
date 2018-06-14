import org.junit.jupiter.api.Test;

class WorldTest {

  @Test
  void createRandomized() {
    new ConwaysGame(World.createRandomized(10)).iterate(10).forEach(world -> {
      System.out.println(world);
      System.out.println("=======================");
    });
  }
}