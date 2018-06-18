package exigentech.gameoflife.conwaysgame;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import exigentech.gameoflife.World;
import exigentech.gameoflife.organism.Organism;
import exigentech.gameoflife.organism.factory.LivingOrganismFactory;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

final class ConwaysGameTest {

  @Test
  void createRandomized() {
    new ConwaysGame(World.create(10, Organism::new)).iterate(10);
  }

  @Test
  void underPopulation() {
    final World world = World.create(
        World.MINIMUM_N, new LivingOrganismFactory(Map.of(1, 1))
    );
    world.modifyOrganism(1, 1, Organism::resurrect);

    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(false));
  }

  @Test
  void resurrected() {
    final World world = World.create(World.MINIMUM_N, new LivingOrganismFactory(
        Map.of(
            0, 0,
            1, 0,
            2, 0
        )
    ));
    assertThat(world.getOrganism(1, 1).isAlive(), is(false));

    final List<World> iterations = new ConwaysGame(world).iterate(1);
    assertThat(iterations.size(), is(1));
    assertThat(iterations.get(0).getOrganism(1, 1).isAlive(), is(true));
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
}