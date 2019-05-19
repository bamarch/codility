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

import static java.lang.Math.max;

import java.util.Arrays;

class Solution {
    public int[] solution(int N, int[] A) {
        // Return array, int[] is initialised to zero by default
        int[] R = new int[N];
        int maxValue = 0;

        // Loop over A, elements refer to a counter X or to N+1
        for (int K = 0; K < A.length; K++) {
            int X = A[K];

            // A counter is specified, increment its value
            if (1 <= X && X <= N ) {
                R[X-1] = R[X-1] + 1; // account for arrays being indexed from zero
                maxValue = max(maxValue, R[X-1]); // record this so we don't require a loop later on
            }
            // N+1 is specified, set all counters in the results array of length N to current max value
            if (X == N + 1) {
                for (int i = 0; i < N; i++) {
                    R[i] = maxValue;
                }
            }
            //System.out.println(Arrays.toString(R));
        }

        return R;
    }
}

//----------------------------------------------------------------------------------------------------------------------
// Dont copy this part into codility test window (contains entry point and test cases)

public class MaxCounters {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // from question
        int[] answer1 = sol.solution(
            5,
            new int[]{3, 4, 4, 6, 1, 4, 4}
        );

        //System.out.println("Was expecting:");
        //System.out.println(Arrays.toString(new int[] {0, 0, 1, 0, 0}));
        //System.out.println(Arrays.toString(new int[] {0, 0, 1, 1, 0}));
        //System.out.println(Arrays.toString(new int[] {0, 0, 1, 2, 0}));
        //System.out.println(Arrays.toString(new int[] {2, 2, 2, 2, 2}));
        //System.out.println(Arrays.toString(new int[] {3, 2, 2, 2, 2}));
        //System.out.println(Arrays.toString(new int[] {3, 2, 2, 3, 2}));
        //System.out.println(Arrays.toString(new int[] {3, 2, 2, 4, 2}));

        assert Arrays.equals(answer1, new int[]{3,2,2,4,2});

    }
}