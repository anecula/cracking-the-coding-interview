package exigentech.hackernoon.thirtydaysofcode.dayfour.daytwo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import exigentech.hackernoon.thirtydaysofcode.daytwo.Operators;
import org.junit.jupiter.api.Test;

class OperatorsTest {

  @Test
  public void firstExample() {
    assertThat(
        Operators.SOLUTION.buildMessage(12.0, 20, 8),
        is("The total meal cost is 15 dollars.")
    );
  }
}