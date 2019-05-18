package maxcounters;
//You are given N counters, initially set to 0, and you have two possible operations on them:
//
//    increase(X) − counter X is increased by 1,
//    max counter − all counters are set to the maximum value of any counter.
//
//    A non-empty array A of M integers is given. This array represents consecutive operations:
//
//    if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
//    if A[K] = N + 1 then operation K is max counter.
//
//    For example, given integer N = 5 and array A such that:
//    A[0] = 3
//    A[1] = 4
//    A[2] = 4
//    A[3] = 6
//    A[4] = 1
//    A[5] = 4
//    A[6] = 4
//
//    the values of the counters after each consecutive operation will be:
//    (0, 0, 1, 0, 0)
//    (0, 0, 1, 1, 0)
//    (0, 0, 1, 2, 0)
//    (2, 2, 2, 2, 2)
//    (3, 2, 2, 2, 2)
//    (3, 2, 2, 3, 2)
//    (3, 2, 2, 4, 2)
//
//    The goal is to calculate the value of every counter after all operations.
//
//    Write a function:
//
//class Solution { public int[] solution(int N, int[] A); }
//
//that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing the values of the counters.
//
//    Result array should be returned as an array of integers.
//
//    For example, given:
//    A[0] = 3
//    A[1] = 4
//    A[2] = 4
//    A[3] = 6
//    A[4] = 1
//    A[5] = 4
//    A[6] = 4
//
//    the function should return [3, 2, 2, 4, 2], as explained above.
//
//    Write an efficient algorithm for the following assumptions:
//
//    N and M are integers within the range [1..100,000];
//    each element of array A is an integer within the range [1..N + 1].
//----------------------------------------------------------------------------------------------------------------------
// Copy from here until the next dotted line when submitting to codility

class Solution {
    public int solution(int[] A) {

//        Integer[] boxedA = new Integer[A.length];
//        for (int i = 0; i < A.length; i++) {
//            boxedA[i] = A[i];
//        }
//        HashSet hashSet = new HashSet<Integer>(Arrays.asList(boxedA));
//
//        int smallestAbsentInt = 100001;
//        for (int i = 1; i <= 100000 ; i++) {
//            if (!hashSet.contains(i)) {
//                smallestAbsentInt = i;
//                break;
//            }
//        }
//
//        //System.out.println(smallestAbsentInt);
//        return smallestAbsentInt;
        return -1;
    }
}

//----------------------------------------------------------------------------------------------------------------------
// Dont copy this part into codility test window (contains entry point and test cases)

public class MaxCounters {
    public static void main(String[] args) {
        Solution sol = new Solution();

//        // from question
//        assert sol.solution(new int[]{1, 3, 6, 4, 1, 2}) == 5;
//        assert sol.solution(new int[]{1, 2, 3}) == 4;
//        assert sol.solution(new int[]{-1, -3}) == 1;
//
//        // at the limits
//        assert sol.solution(IntStream.rangeClosed(1,100000).toArray()) == 100001;
//        assert sol.solution(IntStream.rangeClosed(-100000,-1).toArray()) == 1;

        // other examples
        //sol.solution(Stream.of("a", "b", "c").collect(Collectors.toList()));
    }
}