package exigentech.conwaysgame;

import java.util.List;

public interface GameOfLife {

  List<World> iterate(int iterationCount);
}
