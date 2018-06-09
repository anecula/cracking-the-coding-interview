package exigentech.codility.practice.foresee;

import java.util.Random;

enum RandomValueGenerator {
  SOLUTION;

  private static Random RANDOM = new Random();

  /**
   * @return Randomized integer such that {@code lowerBound < n < upperBound}
   */
  int betweenBounds(int lowerBound, int upperBound) {
    return RANDOM.nextInt(upperBound - lowerBound) + lowerBound;
  }
}

final class Solution {
  private static int UPPER_BOUND = Double.valueOf(Math.pow(10, 9)).intValue();
  private static int MULTIPLE_OF = 10;

  /**
   * @return Randomized multiple of 10 (x) such that {@code n < x < 10^9}.
   */
  int solution(int n) {
    if (n == UPPER_BOUND - 1) {
      // Since the return value must be greater than n and a multiple of 10,
      // UPPER_BOUND is the only valid value.
      return UPPER_BOUND;
    }
    if (n >= UPPER_BOUND) {
      throw new IllegalArgumentException("Input n must be strictly < " + UPPER_BOUND);
    }

    final int boundedRandom = RandomValueGenerator.SOLUTION.betweenBounds(n + 1, UPPER_BOUND);
    final int mod = boundedRandom % MULTIPLE_OF;
    if (mod == 0) {
      return boundedRandom;
    }
    if (boundedRandom - mod == n) {
      return boundedRandom + (MULTIPLE_OF - mod);
    }
    return boundedRandom - mod;
  }
}
