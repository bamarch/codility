package solutions.march.nailingplanks;
//   Given two non-empty arrays A and B consisting of N integers and a non-empty array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
//
//    If it is not possible to nail all the planks, the function should return −1.
//
//    For example, given arrays A, B, C such that:
//    A[0] = 1    B[0] = 4
//    A[1] = 4    B[1] = 5
//    A[2] = 5    B[2] = 9
//    A[3] = 8    B[3] = 10
//
//    C[0] = 4
//    C[1] = 6
//    C[2] = 7
//    C[3] = 10
//    C[4] = 2
//
//    the function should return 4, as explained above.
//
//    Write an efficient algorithm for the following assumptions:
//
//    N and M are integers within the range [1..30,000];
//    each element of arrays A, B, C is an integer within the range [1..2*M];
//    A[K] ≤ B[K].
//----------------------------------------------------------------------------------------------------------------------
// Copy from here until the next dotted line when submitting to codility

import static java.lang.Integer.max;
import static java.lang.Integer.min;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[] A, int[] B, int[] C) {

        // Sort the nails in C, so we can use binary search
        int[] nailValuesSorted = new int[C.length];
        System.arraycopy(C, 0, nailValuesSorted, 0, C.length );
        Arrays.sort(nailValuesSorted);
        //System.out.println(nailValuesSorted);

        // Create array containing the original indices, sorted so the original
        // position of nailValuesSorted[i] can be found as nailIndicesSorted[i]
        Integer[] nailIndicesSorted = new Integer[C.length]; // we box the ints so we can use Arrays.sort
        for (int i = 0; i < C.length; i++) {
            nailIndicesSorted[i]=i;
        }
        Arrays.sort(nailIndicesSorted, Comparator.comparingInt(i  -> C[i]));
        //System.out.println(nailIndicesSorted);

        // This is the result, we return -1 if even with all M nails you cannot nail all N planks
        Integer minOriginalNailIndexForAllPlanks = -1;

        // Iterate over all planks
        for (int K = 0; K < A.length; K++) {
            // Find the first valid entry (which is also smallest valid) in nailValuesSorted where A[K] <= v <= B[K], using binary search algorithm

            // TODO handle when it is not in the list at all e.g list is 1,2,3,4 and search for 5, does that return list length?

            // e.g.

            // Returns index if A[K] is found, otherwise returns (-(insertion point) -1)
            //System.out.println("Searching for A[K]: " + A[K]);
            int binarySearchResult = Arrays.binarySearch(nailValuesSorted, A[K]);

            // We need to parse the results
            int aValidSortedNailIndex;
            // Initialise to something that has be to larger than any of our results
            //System.out.println("binarysearchresult " + binarySearchResult);
            int minSortedNailIndex = C.length;
            if (binarySearchResult < -C.length) {
                // e.g find 5 in {0,1,2,3} -> -4 aka -length
                // the largest nail wont fit the lower plank bound, this plank cannot be nailed, so we should return -1 immediately
                // if any given plank cannot be nailed at all, then there is no need to continue
                minOriginalNailIndexForAllPlanks = -1;
                break;
            } else if (binarySearchResult >= 0) {
                // e.g. find 2 in {0,2,2,3} -> 1 or 2
                // we found a nail that matches the lower bound, now we search linearly left for the same value, to make sure we have the first occurence
                for (int a = binarySearchResult; a >= 0; a--) {
                    if (A[K] == nailValuesSorted[a]) {
                        //System.out.println("assigning, a: " + a);
                        minSortedNailIndex = a;
                    } else {
                        //System.out.println("breaking, a: " + a);
                        break;
                    }
                }
            } else if (binarySearchResult == -1) {
                // e.g find 1 in {2,3,4,5} -> -1
                //  the smallest nail exceeds the lower plank bound, we can take minSortedNailIndex = 0 and proceed as normal if v <= B[K], otherwise return -1 immediately
                minSortedNailIndex = 0;
            } else {
                // e.g. find 1 in {0,3,3,4} -> -2
                // we found a possible insertion point, we can take minSortedNailIndex = -(binarySearchResult + 1) and proceed as normal if v <= B[K], otherwise return -1 immediately
                minSortedNailIndex = -(binarySearchResult+1);
            }

            // Check that our minimum is valid when it did not match a nail value
            //System.out.println("minSortedNailIndex: " + minSortedNailIndex);
            if (B[K] < nailValuesSorted[minSortedNailIndex]) {
                minOriginalNailIndexForAllPlanks = -1;
                break;
            }


            // Initialise to something that has be to larger than any of our results
            Integer minOriginalNailIndex = C.length;

            // Continue through sorted list checking for other valid nails and record their original index if it is smaller
            for (int i = minSortedNailIndex; i < C.length; i++) {

                // Optimization:
                // We can stop this loop whenever minOriginalNailIndex <= minOriginalNailIndexForAllPlanks,
                // because we will take a global maximum across all planks
                // i.e minOriginalNailIndexForAllPlanks = max(minOriginalNailIndexForAllPlanks,minOriginalNailIndex);
                if (minOriginalNailIndex <= minOriginalNailIndexForAllPlanks) {
                    break;
                }
                // Stop searching if the nail value exceeds the maximum for this plank
                // Note we don't need to check plank minimum A[K], because we are starting from smallest valid nail value in nailValuesSorted
                if (B[K] < nailValuesSorted[i]) {
                    break;
                } else {
                    minOriginalNailIndex = min(minOriginalNailIndex, nailIndicesSorted[i]);
                }
            }

            // Update the maximum pos. across all planks, of the minimum positions for specific planks
            minOriginalNailIndexForAllPlanks = max(minOriginalNailIndexForAllPlanks,minOriginalNailIndex);
            //System.out.println("minOriginalNailIndexForAllPlanks: " + minOriginalNailIndexForAllPlanks);
            //System.out.println();
        }

        //System.out.println("result: " + minOriginalNailIndexForAllPlanks);
        //System.out.println();
        //System.out.println();
        int result = minOriginalNailIndexForAllPlanks;
        if (result != -1) {
            result = result + 1; // Convert from index to position
        }
        return result;
    }
}

//----------------------------------------------------------------------------------------------------------------------
// Dont copy this part into codility test window (contains entry point and test cases)
public class NailingPlanks {
    public static void main(String[] args) {
        Solution sol = new Solution();

        //    N and M are integers within the range [1..30,000];
        //    each element of arrays A, B, C is an integer within the range [1..2*M];
        //    A[K] ≤ B[K].

        assert sol.solution(
            new int[]{1, 4, 5, 8},
            new int[]{4, 5, 9, 10},
            new int[]{4, 6, 7, 4, 2}
        ) == -1; // cannot be done, even with all M=5 nails you can only nail 3 planks, not all N=4 planks

        // from question
        assert sol.solution(
            new int[]{1, 4, 5, 8},
            new int[]{4, 5, 9, 10},
            new int[]{4, 6, 7, 10, 2}
        ) == 4; // requires the first 4 nails from C to nail all 4 planks

        // more
        assert sol.solution(
            new int[]{1, 4, 5, 8, 8},
            new int[]{4, 5, 9, 10, 11},
            new int[]{30000, 4, 5, 9, 10, 4, 5, 7}
        ) == 4; // requires the first 4 nails from C to nail all 5 planks

        // at the limits
        assert sol.solution(
            IntStream.rangeClosed(1,30000).toArray(),
            IntStream.rangeClosed(1,30000).toArray(),
            IntStream.rangeClosed(1,30000).toArray()
        ) == 30000; // requires all 30000 nails in C to nail 30000 planks
        assert sol.solution(
            IntStream.rangeClosed(1,30000).toArray(),
            IntStream.rangeClosed(1,30000).map(x -> x * 2).toArray(),
            IntStream.rangeClosed(1,30000).toArray()
        ) == 30000; // requires all 30000 nails in C to nail 30000 planks
        assert sol.solution(
            IntStream.rangeClosed(1,30000).toArray(),
            IntStream.rangeClosed(1,30000).map(x -> x * 2).toArray(),
            IntStream.rangeClosed(1,30000).map(x -> 1).toArray()
        ) == -1; // cannot be done, you can only nail one plank, not all 30000 planks
    }
}
