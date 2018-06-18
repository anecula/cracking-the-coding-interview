package exigentech.gameoflife.organism.factory;

import com.google.common.collect.ImmutableMap;
import exigentech.gameoflife.organism.Organism;
import java.util.Map;

public final class DeadOrganismFactory implements OrganismFactory {

  private final Map<Integer, Integer> livingCoordinates;

  public DeadOrganismFactory(Map<Integer, Integer> livingCoordinates) {
    this.livingCoordinates = ImmutableMap.copyOf(livingCoordinates);
  }

  // TODO: only difference between living/dead is the conditional; consider abstracting that out
  // (abstract out what differs, a la Gang of Four).
  @Override
  public Organism apply(Integer x, Integer y) {
    return new Organism(livingCoordinates.getOrDefault(x, -1) != y, x, y);
  }
}
