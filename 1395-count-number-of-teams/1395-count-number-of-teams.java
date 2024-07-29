class Solution {

    public int numTeams(int[] rating) {
        int n = rating.length;
        int teams = 0;
        Integer[][] increasingCache = new Integer[n][4];
        Integer[][] decreasingCache = new Integer[n][4];

        for (int startIndex = 0; startIndex < n; startIndex++) {
            teams +=
            countIncreasingTeams(rating, startIndex, 1, increasingCache) +
            countDecreasingTeams(rating, startIndex, 1, decreasingCache);
        }

        return teams;
    }

    private int countIncreasingTeams(
        int[] rating,
        int currentIndex,
        int teamSize,
        Integer[][] increasingCache
    ) {
        int n = rating.length;

        if (currentIndex == n) return 0;

        if (teamSize == 3) return 1;

        if (increasingCache[currentIndex][teamSize] != null) {
            return increasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] > rating[currentIndex]) {
                validTeams +=
                countIncreasingTeams(
                    rating,
                    nextIndex,
                    teamSize + 1,
                    increasingCache
                );
            }
        }

        return increasingCache[currentIndex][teamSize] = validTeams;
    }

    private int countDecreasingTeams(
        int[] rating,
        int currentIndex,
        int teamSize,
        Integer[][] decreasingCache
    ) {
        int n = rating.length;

        if (currentIndex == n) return 0;

        if (teamSize == 3) return 1;

        if (decreasingCache[currentIndex][teamSize] != null) {
            return decreasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] < rating[currentIndex]) {
                validTeams +=
                countDecreasingTeams(
                    rating,
                    nextIndex,
                    teamSize + 1,
                    decreasingCache
                );
            }
        }

        return decreasingCache[currentIndex][teamSize] = validTeams;
    }
}