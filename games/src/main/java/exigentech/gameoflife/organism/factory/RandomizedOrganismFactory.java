package exigentech.gameoflife.organism.factory;

import exigentech.gameoflife.organism.Organism;
import java.util.Random;

/**
 * Generates an organism which is alive or dead based on a random seed.
 */
public final class RandomizedOrganismFactory implements OrganismFactory {

  private static final Random RANDOM = new Random();

  @Override
  public Organism apply(Integer x, Integer y) {
    return new Organism(RANDOM.nextBoolean(), x, y);
  }
}
