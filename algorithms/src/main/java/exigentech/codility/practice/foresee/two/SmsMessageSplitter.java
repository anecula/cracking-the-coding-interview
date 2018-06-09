package exigentech.codility.practice.foresee.two;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Solution {
  public int solution(String S, int K) {
    List<String> words = Stream.of(S.split(" ")).collect(Collectors.toList());

    if (words.stream().anyMatch(word -> word.length() > K)) {
      return -1;
    }

    int currentSmsSize = 0;
    int smsCount = 0;

    for (final String word : words) {
      if (word.trim().isEmpty()) {
        continue;
      }
      if (currentSmsSize + 1 + word.length() < K) {
        currentSmsSize += 1 + word.length();
      } else {
        currentSmsSize = word.length();
        smsCount++;
      }
    }

    return smsCount;
  }
}
