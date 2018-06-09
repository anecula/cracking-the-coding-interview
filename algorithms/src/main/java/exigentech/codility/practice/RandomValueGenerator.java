package exigentech.codility.practice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class RandomValueGenerator {

  public int solution(int n) {
    final String binaryRepresentation = Integer.toBinaryString(n);

    final List<Integer> setBitIndices = IntStream.range(0, binaryRepresentation.length())
        .filter(i -> binaryRepresentation.charAt(i) == '1')
        .mapToObj(Integer::valueOf)
        .collect(Collectors.toList());

    if (setBitIndices.size() <= 1) {
      return 0;
    }

    return IntStream.range(0, setBitIndices.size() - 1)
        .map(i -> setBitIndices.get(i + 1) - setBitIndices.get(i) - 1).max().orElse(0);
  }
}
