package hackernoon.thirtydaysofcode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DayTwo_OperatorsTest {

  @Test
  public void firstExample() {
    assertThat(
        DayTwo_Operators.SOLUTION.buildMessage(12.0, 20, 8),
        is("The total meal cost is 15 dollars.")
    );
  }
}