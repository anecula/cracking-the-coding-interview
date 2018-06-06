package hackernoon.algorithms;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;
import javafx.util.converter.BigIntegerStringConverter;

interface Solver {

  int SUMMATION_MAX = 314159;

  long calculateXorAndSum(String a, String b);

}

enum XorAndSum implements Solver {
  SOLUTION;

//  @Override
//  public long calculateXorAndSum(String constant, String shifting) {
//
//    final BitSet constantValue = stringToBitSet(constant);
//    final long shiftValue = binaryStringToInt(shifting);
//
//    long summation = 0;
//
//    for (int i = 0; i < SUMMATION_MAX; i++) {
//      final BitSet shiftedValue = shiftValueToBitSet(shiftValue, i);
//      shiftedValue.xor(constantValue);
//      summation += bitSetToLong(shiftedValue);
//    }
//    return (int) summation;
//  }

  @Override
  public long calculateXorAndSum(String constant, String shifting) {
    final BigIntegerStringConverter converter = new BigIntegerStringConverter();
    final BigInteger constantValue = converter.fromString(constant);
    final BigInteger shiftingValue = converter.fromString(shifting);

    BigInteger summation = BigInteger.ZERO;

    for (int i = 0; i <= SUMMATION_MAX; i++) {
      summation = summation.add(constantValue.xor(shiftingValue.shiftLeft(i)));
//      final BitSet iteration = BitSet.valueOf(
//          BigInteger.valueOf(Arrays.stream(shiftingValue).sum() << i).toByteArray()
//      );
//      System.out.println(iteration.cardinality());
//      iteration.xor(constantValue);
//      System.out.println(iteration.cardinality());
//      summation += Arrays.stream(iteration.toLongArray()).sum();
    }

    return (int) (summation.longValue() - Math.pow(10, 9) + 7);
  }

  static BigInteger stringToBiggyTwoPac(String input) {
    return new BigIntegerStringConverter().fromString(input);
  }

  static BitSet stringToBitSet(String input) {
    final String[] characters = input.split("");

    return IntStream.range(0, characters.length)
        .filter(i -> "1".equals(characters[i])
        ).collect(BitSet::new, BitSet::set, BitSet::or);
  }

  static long binaryStringToInt(String input) {
    return Arrays.stream(
        stringToBitSet(input).toLongArray()
    ).sum();
  }

  static long bitSetToLong(BitSet input) {
    return Arrays.stream(input.toLongArray()).sum();
  }

  static BitSet shiftValueToBitSet(long shiftingValue, int shiftAmount) {
    shiftingValue <<= shiftAmount;
    return BitSet.valueOf(
        BigInteger.valueOf(shiftingValue).toByteArray()
    );
  }
}
