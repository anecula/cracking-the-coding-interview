package exigentech.hackernoon.thirtydaysofcode.daysix;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

final class LoopsTest {

  @Test
  public void evenChars() {
    assertThat(Loops.SOLUTION.parseAsAlternating("Hacker"), is("Hce akr"));
  }
}