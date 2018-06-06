package hackernoon.algorithms;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import org.junit.jupiter.api.Test;

final class XorAndSumTest {

  @Test
  public void lol() {
//    final BitSet shiftingValue = new BitSet();
//    shiftingValue.set(1);

//    System.out.println(BigInteger.valueOf(Arrays.stream(shiftingValue.toLongArray()).sum()));
//    System.out.println(BigInteger.valueOf(Arrays.stream(shiftingValue.toLongArray()).sum() >> 1));
//    assertThat(BigInteger.valueOf(Arrays.stream(shiftingValue.toLongArray()).sum()).longValue() >> 1, is(1L));
//    BitSet derp = BitSet.valueOf(BigInteger.valueOf(Arrays.stream(shiftingValue.toLongArray()).sum() << 1).toByteArray());
//    BitSet herp = BitSet.valueOf(BigInteger.valueOf(Arrays.stream(shiftingValue.toLongArray()).sum()).toByteArray());
//    final BitSet iteration =
//        BitSet.valueOf(
//            BigInteger.valueOf(
//                Arrays.stream(shiftingValue.toLongArray()).sum() << i
//            ).toByteArray()
//        );
    assertThat(
        XorAndSum.SOLUTION.calculateXorAndSum("1000", "1010"), is(489429555)
    );
  }
}