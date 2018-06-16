import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class WorldTest {

  @Test
  void createRandomized() {
    new ConwaysGame(World.create(10, (i, j) -> new Organism(false, i, j))).iterate(10)
        .forEach(world -> {

        });
    new ConwaysGame(World.create(10, new RandomizedOrganismFactory())).iterate(10)
        .forEach(world -> {
          System.out.println(world);
          System.out.println("=======================");
        });
  }

  @Test
  void resurrectOrganism() {
    final World world = World.create(
        World.MINIMUM_N, new SelectiveOrganismFactory(Map.of(1, 1))
    );

    assertThat(world.getOrganism(1, 1).isAlive(), is(false));
    world.updateOrganism(1, 1, Organism::resurrect);
    assertThat(world.getOrganism(1, 1).isAlive(), is(true));
  }

  @Test
  void underPopulation() {
    final World world = World.create(
        World.MINIMUM_N, new SelectiveOrganismFactory(Map.of(1, 1))
    );
    world.updateOrganism(1, 1, Organism::resurrect);

    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(false));
  }

  private static class SelectiveOrganismFactory implements OrganismFactory {
    private final Map<Integer, Integer> aliveSpaces;

    public SelectiveOrganismFactory(Map<Integer, Integer> aliveSpaces) {
      this.aliveSpaces = aliveSpaces;
    }

    @Override
    public Organism apply(Integer x, Integer y) {
      return new Organism(false, x, y);
    }
  };
}