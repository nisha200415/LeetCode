class Solution {

    public int smallestDistancePair(int[] nums, int k) {
        int arrayLength = nums.length;
        int maxElement = Integer.MIN_VALUE;
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
        }
        int[] distanceBucket = new int[maxElement + 1];
        for (int i = 0; i < arrayLength; ++i) {
            for (int j = i + 1; j < arrayLength; ++j) {
                int distance = Math.abs(nums[i] - nums[j]);
                ++distanceBucket[distance];
            }
        }
        for (int dist = 0; dist <= maxElement; ++dist) {
            k -= distanceBucket[dist];
            if (k <= 0) {
                return dist;
            }
        }
        return -1;
    }
}
