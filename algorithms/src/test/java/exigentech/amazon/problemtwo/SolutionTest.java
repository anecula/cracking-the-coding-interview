package exigentech.amazon.problemtwo;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  public void firstExample() {
    final List<String> logFile =
        List.of(
            "mi2 jog mid pet",
            "wz3 34 4 398",
            "a1 alps cow bar",
            "x4 45 21 7"
        );
    System.out.println(new Solution().reorderLines(4, logFile));
  }

}