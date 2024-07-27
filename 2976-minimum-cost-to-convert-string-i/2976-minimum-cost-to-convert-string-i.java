class Solution {

    public long minimumCost( String source, String target, char[] original, char[] changed,
        int[] cost) {
        List<int[]>[] adjacencyList = new List[26];
        for (int i = 0; i < 26; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        int conversionCount = original.length;
        for (int i = 0; i < conversionCount; i++) {
            adjacencyList[original[i] - 'a'].add(
                    new int[] { changed[i] - 'a', cost[i] }
                );
        }
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minConversionCosts[i] = dijkstra(i, adjacencyList);
        }
        long totalCost = 0;
        int stringLength = source.length();
        for (int i = 0; i < stringLength; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long charConversionCost =
                    minConversionCosts[source.charAt(i) - 'a'][target.charAt(
                            i
                        ) -
                        'a'];
                if (charConversionCost == -1) {
                    return -1;
                }
                totalCost += charConversionCost;
            }
        }
        return totalCost;
    }
    private long[] dijkstra(int startChar, List<int[]>[] adjacencyList) {
        PriorityQueue<Pair<Long, Integer>> priorityQueue = new PriorityQueue<>(
            (e1, e2) -> Long.compare(e1.getKey(), e2.getKey())
        );
        priorityQueue.add(new Pair<>(0L, startChar));
        long[] minCosts = new long[26];
        Arrays.fill(minCosts, -1L);

        while (!priorityQueue.isEmpty()) {
            Pair<Long, Integer> currentPair = priorityQueue.poll();
            long currentCost = currentPair.getKey();
            int currentChar = currentPair.getValue();

            if (
                minCosts[currentChar] != -1L &&
                minCosts[currentChar] < currentCost
            ) continue;
            for (int[] conversion : adjacencyList[currentChar]) {
                int targetChar = conversion[0];
                long conversionCost = conversion[1];
                long newTotalCost = currentCost + conversionCost;
                if (
                    minCosts[targetChar] == -1L ||
                    newTotalCost < minCosts[targetChar]
                ) {
                    minCosts[targetChar] = newTotalCost;
                    priorityQueue.add(new Pair<>(newTotalCost, targetChar));
                }
            }
        }
        return minCosts;
    }
}