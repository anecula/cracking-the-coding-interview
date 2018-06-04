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
    if (lhs.equals(rhs)) {
      return lhs.length();
    }

    final int maxLength = Integer.max(lhs.trim().length(), rhs.trim().length());
    final Set<Integer> matchingSubstringsLengths = new HashSet<>(maxLength);

    int currentSubstringLength = 0;
    int differingCharacters = 0;

    for (int lhsStartIndex = 0; lhsStartIndex < maxLength; lhsStartIndex++) {
      for (int rhsStartIndex = 0; rhsStartIndex < maxLength;) {
        for (int lhsIndex = lhsStartIndex, rhsIndex = rhsStartIndex; lhsIndex < maxLength;) {
          if (lhs.charAt(lhsIndex++) != rhs.charAt(rhsIndex++)) {
            differingCharacters++;
          }
          if (differingCharacters > k) {
            matchingSubstringsLengths.add(currentSubstringLength);
            lhsIndex = lhsStartIndex;
            rhsIndex = ++rhsStartIndex;

            currentSubstringLength = 0;
            differingCharacters = 0;
          } else {
            currentSubstringLength++;
          }
//          lhsIndex++;
//          rhsIndex++;
        }
        }
      }
//      for (int lhsIndex = lhsStartIndex, rhsIndex = 0; rhsIndex < maxLength;) {
//        final char lhsChar = lhs.charAt(lhsIndex);
//        final char rhsChar = rhs.charAt(rhsIndex);
//        if (lhs.charAt(lhsIndex) != rhs.charAt(rhsIndex)) {
//          differingCharacters++;
//        }
//        if (differingCharacters > k) {
//          matchingSubstringsLengths.add(currentSubstringLength);
//          lhsIndex = lhsStartIndex;
//
//          currentSubstringLength = 0;
//          differingCharacters = 0;
//        } else {
//          lhsIndex++;
//          currentSubstringLength++;
//        }
//        rhsIndex++;
//      }
//    }

    return matchingSubstringsLengths.stream().max(Integer::compareTo).get();
  }
}

