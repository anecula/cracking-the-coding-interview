package hackernoon.algorithms;

import java.util.HashSet;
import java.util.Set;

public final class SubstringDiff {

  private final String s1;
  private final String s2;

  public SubstringDiff(String s1, String s2) {
    this.s1 = s1;
    this.s2 = s2;
  }

  public int substringDiff(int k) {
    if (s1.equals(s2)) {
      return s1.length();
    }

    final int maxLength = Integer.max(s1.length(), s2.length());
    final Set<Integer> matchingSubstrings = new HashSet<>(maxLength);

    int currentSubstringLength = 0;
    int differingCharacters = 0;

    for (int substringStart = 0; substringStart < maxLength; substringStart++) {
      int substringIndex = substringStart;

      while (substringIndex < maxLength) {
        if (s1.charAt(substringStart) != s2.charAt(substringStart)) {
          differingCharacters++;
        }
        if (differingCharacters > k) {
          break;
        }
        currentSubstringLength++;
        substringIndex++;
      }

      if (currentSubstringLength > 0) {
        matchingSubstrings.add(currentSubstringLength);
      }
      currentSubstringLength = 0;
      differingCharacters = 0;
    }

    return matchingSubstrings.stream().max(Integer::compareTo).get();
  }


//  public static void main(String[] args) throws IOException {
//    try (BufferedWriter bufferedWriter = new BufferedWriter(
//        new FileWriter(System.getenv("OUTPUT_PATH")));
//        Scanner scanner = new Scanner(System.in)) {
//
//      int arg = scanner.nextInt();
//      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//      for (int tItr = 0; tItr < arg; tItr++) {
//        String[] kS1S2 = scanner.nextLine().split(" ");
//
//        int k = Integer.parseInt(kS1S2[0]);
//
//        String s1 = kS1S2[1];
//
//        String s2 = kS1S2[2];
//
//        int result = substringDiff(k, s1, s2);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//      }
//    }
//  }
}

