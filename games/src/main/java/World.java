import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class World {

  private final List<List<Organism>> board;

  public static final int MINIMUM_N = 3;

  public static World create(int n, BiFunction<Integer, Integer, Organism> factory) {
    assert n >= MINIMUM_N : "Requires at least a 3x3 board (given " + n + ")";

    final List<List<Organism>> board = new ArrayList<>(n);

    // TODO: I think this can be improved using IntStream.range, but may be dubiously better.
    for (int i = 0; i < n; i++) {
      board.add(new ArrayList<>(n));
      for (int k = 0; k < n; k++) {
        board.get(i).add(factory.apply(i, k));
      }
    }
    return new World(board);
  }

  public static World create(Collection<Organism> organisms) {
    assert organisms != null : "organisms cannot be null";
    assert
        organisms.size() >= MINIMUM_N * MINIMUM_N :
        "Requires at least 9 organisms (given " + organisms.size() + ")";

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

  public void updateOrganism(int x, int y, Consumer<Organism> applicator) {
    assert x < board.size() && x >= 0;
    Organism organism = board.get(x).get(y);
    board.get(x).remove(organism);

    applicator.accept(organism);
    board.get(x).add(y, organism);
  }

  public Organism getOrganism(int x, int y) {
    assert x < board.size() && x >= 0;
    return board.get(x).get(y);
  }

  public Map<Organism, Integer> getAdjacentLivingOrganismCountByOrganism() {
    return board.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList()).stream()
        .collect(Collectors.toMap(Function.identity(), this::countAdjacentLivingOrganisms));
  }

  private int countAdjacentLivingOrganisms(final Organism organism) {
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
        // TODO: this method could be made more functional: given a function, apply it to all, and
        // accumulate the results in a Collection.
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

interface OrganismFactory extends BiFunction<Integer, Integer, Organism> {

  @Override
  default Organism apply(Integer x, Integer y) {
    return new Organism(false, x, y);
  }
}

final class RandomizedOrganismFactory implements OrganismFactory {

  private static final Random RANDOM = new Random();

  @Override
  public Organism apply(Integer x, Integer y) {
    return new Organism(RANDOM.nextBoolean(), x, y);
  }
}

@FunctionalInterface
interface GameOfLife {

  List<World> iterate(int iterationCount);
}

abstract class GameOfLifeDecorator<T extends GameOfLife> implements GameOfLife {

  private final T game;

  public GameOfLifeDecorator(T game) {
    this.game = game;
  }

  @Override
  public List<World> iterate(int iterationCount) {
    final List<World> iterationResults = game.iterate(iterationCount);
    iterationResults.stream(); // print each step
    return null;
  }
}

final class ConwaysGame implements GameOfLife {

  private final World world;

  ConwaysGame(World world) {
    this.world = world;
  }

  @Override
  public List<World> iterate(int iterationCount) {
    final List<World> iterations = new ArrayList<>(iterationCount);
    for (int i = 0; i < iterationCount; i++) {
      iterations.add(
          World.create(world.getAdjacentLivingOrganismCountByOrganism()
              .entrySet().stream()
              .map(this::applyRule)
              .collect(Collectors.toList()))
      );
    }
    return iterations;
  }

  private Organism applyRule(Entry<Organism, Integer> entry) {
    final Organism organism = entry.getKey();
    final int adjacentLivingOrganisms = entry.getValue();

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
