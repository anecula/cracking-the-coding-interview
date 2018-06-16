import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class WorldTest {

  @Test
  void createRandomized() {
    new ConwaysGame(World.create(10, Organism::new)).iterate(10);
  }

  @Test
  void resurrectOrganism() {
    final World world = World.create(
        World.MINIMUM_N, createWithLivingOrganisms(Map.of(1, 1))
    );

    assertThat(world.getOrganism(1, 1).isAlive(), is(false));
    world.updateOrganism(1, 1, Organism::resurrect);
    assertThat(world.getOrganism(1, 1).isAlive(), is(true));
  }

  @Test
  void underPopulation() {
    final World world = World.create(
        World.MINIMUM_N, createWithLivingOrganisms(Map.of(1, 1))
    );
    world.updateOrganism(1, 1, Organism::resurrect);

    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(false));
  }

  @Test
  void resurrected() {
    final World world = World.create(World.MINIMUM_N, createWithLivingOrganisms(
        Map.of(
            0, 0,
            1, 0,
            2, 0
        )
    ));
    System.out.println(world);
    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(false));
  }

  @Test
  void overPopulated() {
    final World world = World.create(World.MINIMUM_N, (x, y) -> new Organism(true, x, y));
    System.out.println(world);

    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    System.out.println(iterations.get(0));
    assertThat(iterations.get(0).getOrganism(0, 1).isAlive(), is(true));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(false));
  }

  private static OrganismFactory createWithLivingOrganisms(Map<Integer, Integer> livingCoords) {
    return new OrganismFactory() {
      @Override
      public Organism apply(Integer x, Integer y) {
        return new Organism(livingCoords.getOrDefault(x, -1) == y, x, y);
      }
    };
  }
}