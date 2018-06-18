package exigentech.gameoflife.organism.factory;

import static com.google.common.base.Preconditions.checkArgument;

import exigentech.gameoflife.organism.Organism;
import java.util.function.BiFunction;

public interface OrganismFactory extends BiFunction<Integer, Integer, Organism> {

  /**
   * Produces a new {@linkplain Organism} for the given coordinates.
   * @param x The x coordinate for the Organism (cannot be null, must be positive).
   * @param y The x coordinate for the Organism (cannot be null, must be positive).
   * @return
   */
  @Override
  default Organism apply(Integer x, Integer y) {
    validateCoordinate(x);
    validateCoordinate(y);
    return new Organism(false, x, y);
  }

  private static void validateCoordinate(final Integer coordinate) {
    checkArgument(coordinate != null && coordinate >= 0,
        "Organism coordinates must be positive. Given: " + coordinate);
  }
}
