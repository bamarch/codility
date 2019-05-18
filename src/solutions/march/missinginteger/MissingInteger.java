package solutions.march.missinginteger;
//----------------------------------------------------------------------------------------------------------------------
// Copy from here until the next dotted line when submitting to codility

import java.util.Arrays;
import java.util.HashSet;

import java.util.stream.IntStream;

//    Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//    that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
//
//    For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
//
//    Given A = [1, 2, 3], the function should return 4.
//
//    Given A = [−1, −3], the function should return 1.
//
//    Write an efficient algorithm for the following assumptions:
//
//    N is an integer within the range [1..100,000];
//    each element of array A is an integer within the range [−1,000,000..1,000,000].

class Solution {
    public int solution(int[] A) {

        Integer[] boxedA = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            boxedA[i] = A[i];
        }
        HashSet hashSet = new HashSet<Integer>(Arrays.asList(boxedA));

        int smallestAbsentInt = 100001;
        for (int i = 1; i <= 100000 ; i++) {
            if (!hashSet.contains(i)) {
                smallestAbsentInt = i;
                break;
            }
        }

        //System.out.println(smallestAbsentInt);
        return smallestAbsentInt;
    }
}

//----------------------------------------------------------------------------------------------------------------------
// Dont copy this part into codility test window (contains entry point and test cases)
public class MissingInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // from question
        assert sol.solution(new int[]{1, 3, 6, 4, 1, 2}) == 5;
        assert sol.solution(new int[]{1, 2, 3}) == 4;
        assert sol.solution(new int[]{-1, -3}) == 1;

        // at the limits
        assert sol.solution(IntStream.rangeClosed(1,100000).toArray()) == 100001;
        assert sol.solution(IntStream.rangeClosed(-100000,-1).toArray()) == 1;

        // other examples
        //sol.solution(Stream.of("a", "b", "c").collect(Collectors.toList()));
    }
}