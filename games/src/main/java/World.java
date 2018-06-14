import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class World {

  private final List<List<Organism>> board;

  int countAdjacentLivingOrganisms(Organism organism) {
    final int x = organism.xIndex();
    final int y = organism.yIndex();

    int adjacentLivingOrganisms = 0;

    for (int i = x - 1; i <= x + 1; i++) {
      if (i < 0 || i > board.size()) {
        continue;
      }
      for (int k = y - 1; k <= y + 1; k++) {
        if (k < 0 || k > board.size()) {
          continue;
        }
        if (board.get(i).get(k).isAlive()) {
          adjacentLivingOrganisms++;
        }
      }
    }

    return adjacentLivingOrganisms;
  }

  @Override
  public String toString() {
    return " ";
  }

  Map<Organism, Integer> getWorldState() {
    return board.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList()).stream()
        .collect(Collectors.toMap(Function.identity(), this::countAdjacentLivingOrganisms));
  }

  public static World create(Collection<Organism> organisms) {
    assert organisms != null && !organisms.isEmpty() : "organisms == null || organisms.isEmpty()";

    Map<Integer, List<Organism>> organismsByAxis = organisms.stream()
        .collect(Collectors.groupingBy(Organism::xIndex));

    // ?-
    return new World(
        organisms.stream()
            .collect(Collectors.groupingBy(Organism::xIndex))
            .keySet().stream().sorted()
            .map(xAxis -> organismsByAxis.get(xAxis))
            .collect(Collectors.toList())
    );
//
//    for (int i = 0; i < n; i++) {
//      board.add(new ArrayList<>(n));
//      for (int k = 0; k < n; k++) {
//        board.get(i).add(new Organism(random.nextBoolean(), i, k));
//      }
//    }
//    return new World(board);
  }

  public static World createRandomized(int n) {
    assert n > 0 : "n <= 0";

    final Random random = new Random(n);
    final List<List<Organism>> board = new ArrayList<>(n);

    for (int i = 0; i < n; i++) {
      board.add(new ArrayList<>(n));
      for (int k = 0; k < n; k++) {
        board.get(i).add(new Organism(random.nextBoolean(), i, k));
      }
    }
    return new World(board);
  }

  private World(List<List<Organism>> board) {
    // validate not empty/null
    this.board = board;
  }
}


interface WorldIteration {

  World iterate(int iterationCount);
}

final class ConwaysGame implements WorldIteration {

  private final World world;

  ConwaysGame(World world) {
    this.world = world;
  }

  @Override
  public World iterate(int iterationCount) {
    return null; // TODO

//    world.getWorldState().forEach();
  }

  private void applyRule(final Organism organism, final int adjacentLivingOrganisms) {
    boolean isAlive = organism.isAlive();

    if (adjacentLivingOrganisms < 2) {
      organism.kill();
    }
  }
}

final class Organism {

  private boolean isAlive;
  private final int xIndex;
  private final int yIndex;

  Organism(boolean isAlive, int xIndex, int yIndex) {
    this.isAlive = isAlive;
    this.xIndex = xIndex;
    this.yIndex = yIndex;
  }

  int xIndex() {
    return xIndex;
  }

  int yIndex() {
    return yIndex;
  }

  boolean isAlive() {
    return this.isAlive;
  }

  void kill() {
    this.isAlive = false;
  }

  void resurrect() {
    this.isAlive = true;
  }
}
