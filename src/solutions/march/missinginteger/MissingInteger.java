package solutions.march.missinginteger;
//----------------------------------------------------------------------------------------------------------------------
// Copy from here until the next dotted line when submitting to codility

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
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
        List<Integer> positiveNumbers = Arrays.stream(A).filter(x -> x>0).boxed().collect(Collectors.toList());
        HashSet<Integer> postitiveSet = new HashSet<>(positiveNumbers);

        int smallestAbsentInt = IntStream.rangeClosed(1,100000).filter(x -> !postitiveSet.contains(x)).findFirst().orElse(100001);

        System.out.println(smallestAbsentInt);
        return smallestAbsentInt;
    }
}

//----------------------------------------------------------------------------------------------------------------------
// Dont copy this part into codility test window (contains entry point and test cases)
public class MissingInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // for this test data do...

        // from question
        sol.solution(new int[]{1, 3, 6, 4, 1, 2}); // 5
        sol.solution(new int[]{1, 2, 3}); // 4
        sol.solution(new int[]{-1, -3}); //1

        // at the limits
        sol.solution(IntStream.rangeClosed(1,100000).toArray());
        sol.solution(IntStream.rangeClosed(-100000,-1).toArray());

        // other examples
        //sol.solution(Stream.of("a", "b", "c").collect(Collectors.toList()));
    }
}