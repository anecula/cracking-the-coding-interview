import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class World {

  private final List<List<Organism>> board;

  public static World createRandomized(int n) {
    assert n > 0 : "n <= 0";

    final Random random = new Random(n);
    final List<List<Organism>> board = new ArrayList<>(n);

    // TODO: I think this can be improved using IntStream.range, but may be dubiously better.
    for (int i = 0; i < n; i++) {
      board.add(new ArrayList<>(n));
      for (int k = 0; k < n; k++) {
        board.get(i).add(new Organism(random.nextBoolean(), i, k));
      }
    }
    return new World(board);
  }

  public static World create(Collection<Organism> organisms) {
    assert organisms != null && !organisms.isEmpty() : "organisms == null || organisms.isEmpty()";

    final Map<Integer, List<Organism>> organismsByAxis =
        organisms.stream()
            .collect(Collectors.groupingBy(Organism::xIndex));

    return new World(
        organismsByAxis
            .keySet().stream().sorted()
            .map(xAxis -> organismsByAxis.get(xAxis))
            .collect(Collectors.toList())
    );
  }

  public Map<Organism, Integer> getAdjacentLivingOrganisms() {
    return board.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList()).stream()
        .collect(Collectors.toMap(Function.identity(), this::countAdjacentLivingOrganisms));
  }

  private int countAdjacentLivingOrganisms(Organism organism) {
    final int x = organism.xIndex();
    final int y = organism.yIndex();

    int adjacentLivingOrganisms = 0;

    for (int i = x - 1; i <= x + 1; i++) {
      if (i < 0 || i >= board.size()) {
        continue;
      }
      for (int k = y - 1; k <= y + 1; k++) {
        if (k < 0 || k >= board.size()) {
          continue;
        }
        // TODO: this function could be made more functional: given a function, apply it to all
        // neighbors of each element, and return a Collection contains the results.
        if (board.get(i).get(k).isAlive()) {
          adjacentLivingOrganisms++;
        }
      }
    }
    return adjacentLivingOrganisms;
  }

  @Override
  public String toString() {
    return board.stream().map(List::toString).collect(Collectors.joining(System.lineSeparator()));
  }

  private World(List<List<Organism>> board) {
    // Using vanilla Java Lists, there are many opportunities for null here. A good solution is to
    // use the ImmutableCollection API from Guava (Google's Utility Library), which has guarantees
    // against null values.
    assert board != null && !board.isEmpty() : "organisms == null || organisms.isEmpty()";
    this.board = board;
  }
}


@FunctionalInterface
interface GameOfLife {

  Stream<World> iterate(int iterationCount);
}

final class ConwaysGame implements GameOfLife {

  private final World world;

  ConwaysGame(World world) {
    this.world = world;
  }

  @Override
  public Stream<World> iterate(int iterationCount) {
//    return World.create(
//        world.getAdjacentLivingOrganisms().entrySet().stream()
//        .map(entry ->
//          applyRule(entry.getKey(), entry.getValue())
//        ).collect(Collectors.toList()));

    // TODO
    return null;
  }

  private Organism applyRule(final Organism organism, final int adjacentLivingOrganisms) {
    if (organism.isAlive() && adjacentLivingOrganisms < 2 || adjacentLivingOrganisms > 3) {
      organism.kill();
    } else if (!organism.isAlive() && adjacentLivingOrganisms == 3) {
      organism.resurrect();
    }
    return organism;
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

  @Override
  public String toString() {
    return isAlive ? "0" : "1";
  }
}
