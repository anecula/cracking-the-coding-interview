package exigentech.gameoflife.organism;

import java.util.Objects;

public final class Organism {

  private boolean isAlive;
  private final int xIndex;
  private final int yIndex;

  public Organism(int xIndex, int yIndex) {
    this.isAlive = false;
    this.xIndex = xIndex;
    this.yIndex = yIndex;
  }

  public Organism(boolean isAlive, int xIndex, int yIndex) {
    this.isAlive = isAlive;
    this.xIndex = xIndex;
    this.yIndex = yIndex;
  }

  public int xIndex() {
    return xIndex;
  }

  public int yIndex() {
    return yIndex;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void kill() {
    isAlive = false;
  }

  public void resurrect() {
    if (!isAlive) {
      isAlive = true;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organism organism = (Organism) o;
    return isAlive == organism.isAlive &&
        xIndex == organism.xIndex &&
        yIndex == organism.yIndex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(xIndex, yIndex);
  }

  @Override
  public String toString() {
    return isAlive ? "1" : "0";
  }
}
