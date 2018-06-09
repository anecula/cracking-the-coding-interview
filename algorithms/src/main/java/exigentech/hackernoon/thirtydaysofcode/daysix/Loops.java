package exigentech.hackernoon.thirtydaysofcode.daysix;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum Loops {
  SOLUTION;

  String parseAsAlternating(String input) {
    if (input == null) {
      return "";
    }

    return new StringBuffer(input.length() * 2)
        .append(getAlternatingCharacters(input))
        .append(' ')
        .append(getAlternatingCharacters(input.substring(1)))
        .toString();
  }

  private String getAlternatingCharacters(final String input) {
    return IntStream.range(0, input.length())
        .filter(n -> n % 2 == 0)
        .mapToObj(n -> String.valueOf(input.charAt(n)))
        .collect(Collectors.joining(""));
  }
}

final class Solution {

  public static void main(String[] args) {
    try (final Scanner scanner = new Scanner(System.in)) {
      scanner.nextInt(); // Discard; it's simply not needed
      while (scanner.hasNext()) {
        System.out.println(Loops.SOLUTION.parseAsAlternating(scanner.next()));
      }
    }
  }
}
