package exigentech.codility.practice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

final class SolutionTest {

  @Test
  public void oneSetBit() {
    assertThat(new RandomValueGenerator().solution(3), is(0));
  }

  @Test
  public void trailingZeroes() {
    assertThat(new RandomValueGenerator().solution(328), is(2));
  }

  @Test
  public void twoGaps() {
      assertThat(new RandomValueGenerator().solution(529), is(4));
  }

  @Test
  public void noGaps() {
    assertThat(new RandomValueGenerator().solution(15), is(0));
  }

  @Test
  public void medium() {
    assertThat(new RandomValueGenerator().solution(20), is(1));
  }

  @Test
  public void large() {
    assertThat(new RandomValueGenerator().solution(6291457), is(20));
  }

  @Test
  public void really() {
    String a = new String("a");
    String b = "a";
    String c = a;
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(b.equals(c));
  }
}