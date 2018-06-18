package exigentech.conwaysgame.organism.factory;

import com.google.common.collect.ImmutableMap;
import exigentech.conwaysgame.organism.Organism;
import java.util.Map;

public class LivingOrganismFactory implements OrganismFactory {

  private final Map<Integer, Integer> livingCoordinates;

  public LivingOrganismFactory(Map<Integer, Integer> livingCoordinates) {
    this.livingCoordinates = ImmutableMap.copyOf(livingCoordinates);
  }

  @Override
  public Organism apply(Integer x, Integer y) {
    return new Organism(livingCoordinates.getOrDefault(x, -1) == y, x, y);
  }
}
