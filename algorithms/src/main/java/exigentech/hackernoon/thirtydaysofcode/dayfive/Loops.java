package exigentech.hackernoon.thirtydaysofcode.dayfive;

import java.util.Scanner;
import java.util.stream.IntStream;

enum Loops {

  SOLUTION;

  String formatMultiples(int n) {
    final StringBuffer buffer = new StringBuffer();
    IntStream.range(1, 11).forEachOrdered(i ->
            buffer.append(n)
                .append(" x ")
                .append(i)
                .append(" = ")
                .append(n * i)
                .append(System.lineSeparator())
        );
    return buffer.toString();
  }
}

final class Solution {
  public static void main(String[] args) {
    try (final Scanner scanner = new Scanner(System.in)) {
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      System.out.println(Loops.SOLUTION.formatMultiples(scanner.nextInt()));
    }
  }
}

