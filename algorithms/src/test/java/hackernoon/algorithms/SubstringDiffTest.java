package hackernoon.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public final class SubstringDiffTest {

  @Test
  public void firstExample() {
    assertThat(new SubstringDiff("abcd", "bbca").substringDiff(1), is(3));
  }

  @Test
  public void secondExample() {
    assertThat(new SubstringDiff("tabriz", "torino").substringDiff(2), is(4));
  }

  @Test
  public void thirdExample() {
    assertThat(new SubstringDiff("abacba", "abcaba").substringDiff(0), is(3));
  }

  @Test
  public void fourthExample() {
    assertThat(new SubstringDiff("helloworld", "yellomarin").substringDiff(3), is(8));
  }
}