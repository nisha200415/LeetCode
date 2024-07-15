class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int consecutiveOdd=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]%2==1){
                consecutiveOdd++;
            }else{
                consecutiveOdd=0;
            }
            if(consecutiveOdd ==3){
                return true;
            }
        }
        return false;
        
    }
}