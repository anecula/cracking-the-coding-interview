package exigentech.conwaysgame;

import exigentech.conwaysgame.organism.Organism;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class ConwaysGame implements GameOfLife {
  private final World world;

  ConwaysGame(World world) {
    this.world = world;
  }

  @Override
  public List<World> iterate(int iterationCount) {
    return IntStream.range(0, iterationCount).mapToObj(i ->
        World.create(world.getAdjacentLivingOrganismCountByOrganism()
            .entrySet().stream()
            .map(this::applyRule)
            .collect(Collectors.toList()))
    ).collect(Collectors.toList());
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
