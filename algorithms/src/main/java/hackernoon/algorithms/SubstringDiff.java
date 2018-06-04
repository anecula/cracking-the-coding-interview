package hackernoon.algorithms;

import java.util.HashSet;
import java.util.Set;

public final class SubstringDiff {

  private final String lhs;
  private final String rhs;

  public SubstringDiff(String lhs, String rhs) {
    this.lhs = lhs.trim();
    this.rhs = rhs.trim();
  }

  public int fme(int k, String lhs, String rhs) {
    final int maxLength = Integer.max(lhs.length(), rhs.length());
    final Set<Integer> matchingSubstringsLengths = new HashSet<>(maxLength);

    for (int lhsStartIndex = 0; lhsStartIndex < maxLength; lhsStartIndex++) {
      for (int rhsIndex = 0; rhsIndex < maxLength; rhsIndex++) {
        matchingSubstringsLengths.add(
            fme(k, lhs.substring(lhsStartIndex, maxLength), rhs.substring(rhsIndex, maxLength))
        );
      }
    }
    return matchingSubstringsLengths.stream().max(Integer::compareTo).get();
  }

  public int substringDiffer(int k) {
    if (lhs.equals(rhs)) {
      return lhs.length();
    }

    final int maxLength = Integer.max(lhs.trim().length(), rhs.trim().length());
    final Set<Integer> matchingSubstringsLengths = new HashSet<>(maxLength);

    int currentSubstringLength = 0;
    int differingCharacters = 0;

    for (int lhsStartIndex = 0; lhsStartIndex < maxLength; lhsStartIndex++) {
      for (int lhsIndex = 0; lhsIndex < maxLength; lhsIndex++) {
        final int rhsStartIndex = rhs.indexOf(lhs.charAt(lhsIndex), lhsStartIndex);

        while (rhsStartIndex > 0) {
          for (int rhsIndex = rhsStartIndex; rhsIndex < maxLength; rhsIndex++) {
            if (lhs.charAt(lhsIndex) != rhs.charAt(rhsIndex)) {
              differingCharacters++;
            }

            if (differingCharacters > k) {
              matchingSubstringsLengths.add(currentSubstringLength);

              currentSubstringLength = 0;
              differingCharacters = 0;
            } else {
              currentSubstringLength++;
            }
          }
        }
      }
//      for (int)
    }
//      for (int rhsStartIndex = 0; rhsStartIndex < maxLength;) {
//        for (int lhsIndex = lhsStartIndex, rhsIndex = rhsStartIndex; lhsIndex < maxLength;) {
    return 0;
  }

  public int substringDiff(int k) {
    final int maxLength = Integer.max(lhs.trim().length(), rhs.trim().length());

    int currentSubstringLength = 0;
    int differingCharacters = 0;
    int currentMax = 0;

    for (int lhsStartIndex = 0; lhsStartIndex < maxLength; lhsStartIndex++) {

      if (currentMax > maxLength - lhsStartIndex) {
        return currentMax;
      }

      // TODO: this substring is superfluous, if indices can be managed properly.
      final String lhsAnchor = lhs.substring(lhsStartIndex, maxLength);

      for (int rhsStartIndex = rhs.indexOf(lhsAnchor.charAt(0));
               rhsStartIndex >= 0;) {
        final String rhsAnchor = rhs.substring(rhsStartIndex, maxLength);

        for (int lhsIndex = 0, rhsIndex = 0; lhsIndex < lhsAnchor.length() && rhsIndex < rhsAnchor.length(); lhsIndex++, rhsIndex++) {
          if (lhsAnchor.charAt(lhsIndex) != rhsAnchor.charAt(rhsIndex)) {
            differingCharacters++;
          }

          if (differingCharacters > k) {
            break;
          }
          currentSubstringLength++;
        }

        if (differingCharacters < k) {
           currentSubstringLength++;
        }

        if (currentSubstringLength > currentMax) {
          currentMax = currentSubstringLength;
        }
        currentSubstringLength = 0;
        differingCharacters = 0;

        rhsStartIndex = rhs.indexOf(lhsAnchor.charAt(0), rhsStartIndex + 1);
      }
    }

    return currentMax;
  }
}

