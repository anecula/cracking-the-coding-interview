package exigentech.amazon;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution {

  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude) {
    if (literatureText.isEmpty() || literatureText == null) {
      return Collections.emptyList();
    }

    final List<String> words =
        Arrays.stream(literatureText.split("\\s"))
            .map(word -> word.trim())
            .filter(word -> !wordsToExclude.contains(word))
            .collect(Collectors.toList());

    final Map<String, Integer> wordsByFrequency = new HashMap<>();

    for (final String word : words) {
      if (wordsByFrequency.containsKey(word)) {
        wordsByFrequency.put(word, wordsByFrequency.get(word) + 1);
      } else {
        wordsByFrequency.put(word, 1);
      }
    }

    final Optional<Integer> highestFrequency = wordsByFrequency.values().stream()
        .max(Integer::compareTo);
    if (!highestFrequency.isPresent()) {
      return Collections.emptyList();
    }

    return wordsByFrequency.entrySet().stream()
        .filter(entry -> entry.getValue() == highestFrequency.get()).map(entry -> entry.getKey())
        .collect(Collectors.toList());
  }
  // METHOD SIGNATURE ENDS
}

