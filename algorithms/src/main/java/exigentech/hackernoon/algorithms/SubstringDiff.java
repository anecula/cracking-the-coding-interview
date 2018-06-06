package hackernoon.algorithms;

final class SubstringDiff {

  private final String lhs;
  private final String rhs;

  SubstringDiff(String lhs, String rhs) {
    this.lhs = lhs.trim();
    this.rhs = rhs.trim();
  }

  /**
   * Big-O is tough for this optimization. I believe the worst-case scenario is where k=1, and every
   * other character from {@code lhs} and {@code rhs} match, so that the algorithm has to keep going
   * to see if it can reach a higher max, but it repeatedly restarts. With k=1, the amount of
   * restarts will be maximized.
   * <p />
   * Furthermore, for each iteration on n, the size of rhs shrinks by 2, since we ignore the first n
   * positions that have already been considered.
   * <p />
   * I believe that is something like this:
   * <ol>
   * <li>{@code O(n^({n/2, n-2/2, n-4/2, ...1}))}</li>
   * <li>{@code O(n^(n^log(n))+1)}</li>
   * <li>{@code O(n^(n^log(n)))}</li>
   * </ol>
   * Although I think that's leaving out k, which has a place in the optimization, as well.
   *
   * @param k
   * @return
   */
   int substringDiff(int k) {
    final int maxLength = Integer.max(lhs.trim().length(), rhs.trim().length());

    int currentSubstringLength = 0;
    int differingCharacters = 0;
    int currentMax = 0;

    for (int lhsStartIndex = 0; lhsStartIndex < maxLength; lhsStartIndex++) {
      if (currentMax > maxLength - lhsStartIndex) {
        return currentMax;
      }

      // The "anchor" strings are created to make zero-based indexing intuitive through the rest of
      // the admittedly complex algorithm. For the loop that does the actual comparisons, it will
      // always index based on the substrings beginning with lhsStart and rhsStart, where rhsStart
      // is always an index matching lhsStart[0].

      final String lhsAnchor = lhs.substring(lhsStartIndex, maxLength);

      for (int rhsStartIndex = rhs.indexOf(lhsAnchor.charAt(0));
               rhsStartIndex >= 0;) {

        final String rhsAnchor = rhs.substring(rhsStartIndex, maxLength);

        for (int lhsIndex = 0, rhsIndex = 0;
                 lhsIndex < lhsAnchor.length() && rhsIndex < rhsAnchor.length();
                 lhsIndex++, rhsIndex++) {

          if (lhsAnchor.charAt(lhsIndex) != rhsAnchor.charAt(rhsIndex)) {
            differingCharacters++;
          }

          if (differingCharacters > k) {
            break;
          }
          if (++currentSubstringLength == maxLength) {
            return maxLength;
          }
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

