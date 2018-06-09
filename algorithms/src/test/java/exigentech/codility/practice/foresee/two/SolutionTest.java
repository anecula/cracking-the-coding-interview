package exigentech.codility.practice.foresee.two;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  public void lol1() {
    assertThat(new Solution().solution("An ab is", 2), is(3));
  }

  @Test
  public void lol2() {
    assertThat(new Solution().solution("A a is", 2), is(3));
  }

  @Test
  public void lol3() {
    assertThat(new Solution().solution("SMS messages are really short", 12), is(3));
  }

  @Test
  public void lol4() {
    assertThat(new Solution().solution("SMS messages are really short", 6), is(-1));
  }


  @Test
  public void lol5() {
    assertThat(new Solution().solution("     ", 1), is(0));
  }

  @Test
  public void lol6() {
    assertThat(new Solution().solution("  t   ", 1), is(1));
  }
}