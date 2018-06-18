package exigentech.gameoflife;

import java.util.List;

/**
 * Represents a Game of Life, which is a game that has a unique {@linkplain World} which is
 * impacted by iterations on itself according to some rule.
 */
public interface GameOfLife {

  List<World> iterate(int iterationCount);
}
