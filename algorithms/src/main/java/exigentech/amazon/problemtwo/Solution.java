package exigentech.amazon.problemtwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

  public List<String> reorderLines(int logFileSize, List<String> logLines) {
    if (logFileSize != logLines.size()) {
      throw new IllegalArgumentException("Number of log lines must correspond to logFileSize.");
    }

    if (logFileSize == 0) {
      return Collections.emptyList();
    }

    final Map<String, String> linesWithWordsById = new HashMap<>(logFileSize);
    final List<String> linesWithNumbers = new ArrayList<>(logFileSize);

    logLines.forEach(line -> {
      final int idIndex = line.indexOf(" ");
      final String logId = line.substring(0, idIndex);
      final String logEntry = line.substring(idIndex + 1);

      if (logEntry.matches(".*\\d+.*")) {
        linesWithNumbers.add(line);
      } else {
        linesWithWordsById.put(logId, logEntry);
      }
    });

    final List<String> sortedLinesWithWords =
        linesWithWordsById.entrySet().stream()
            .sorted((entry1, entry2) -> {
              final int valueComparison = entry1.getValue().compareTo(entry2.getValue());
              if (valueComparison ==  0) {
                return entry1.getKey().compareTo(entry2.getKey());
              }
              return valueComparison;
            })
            .map(entry -> entry.getKey() + " " + entry.getValue())
            .collect(Collectors.toList());

    sortedLinesWithWords.addAll(linesWithNumbers);

    return sortedLinesWithWords;
  }
}

