package hackernoon.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

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
}