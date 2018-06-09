package exigentech.amazon.problemone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

final class SolutionTest {

  @Test
  public void firstExample() {
    assertThat(new Solution().retrieveMostFrequentlyUsedWords(
            "romeo romeo wherefore art romeo", List.of("art though")),
        is(List.of("romeo"))
    );
  }
}