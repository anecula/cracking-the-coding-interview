package exigentech.gameoflife;

import exigentech.gameoflife.organism.Organism;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents an N*N
 */
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

  public void modifyOrganism(int x, int y, Consumer<Organism> applicator) {
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
    int adjacentLivingOrganisms = 0;

    for (int x = organism.xIndex() - 1; x < organism.xIndex() + 1; x++) {
      if (x < 0 || x >= board.size()) {
        continue;
      }
      for (int y = organism.yIndex() - 1; y < organism.yIndex() + 1; y++) {
        if (y < 0 || y >= board.size()) {
          continue;
        }
        // TODO: this method could be made more functional: given a function, apply it to all, and
        // accumulate the results in a Collection.
        if (board.get(x).get(y).isAlive()) {
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

