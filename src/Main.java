import java.util.HashMap;

class Solution {
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // Important: handles sum == k case

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }


    public static void main(){
        int ans = subarraySum(new int[]{4,6,5,5,5,10}, 10);
        System.out.println(ans);
    }
}
