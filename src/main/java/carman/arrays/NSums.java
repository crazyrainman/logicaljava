package carman.arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class NSums {
    public void findBrutalForce(int[] nums, int aim) {
        for (int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == aim) {
                    System.out.println(String.valueOf(nums[i]) + String.valueOf(nums[j]) + "equals " + String.valueOf(aim));
                }
            }
        }
    }
    public void findWihHash(int[] nums, int aim) {
        if (nums.length < 2) {
            System.out.println("Too short array.");
            return;
        }
        HashMap<Integer, Integer> subtracts = new HashMap<>();
        subtracts.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (subtracts.containsKey(aim - nums[i])) {
                System.out.println(nums[i]  + " + "  + nums[subtracts.get(aim - nums[i])] + " equals " + aim);
            }
            subtracts.put(nums[i], i);
        }
    }
    public void find3Sum(int[] nums, int target) {
        HashMap<Integer, Integer> knowns = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            knowns.put(nums[i - 1], i - 1);
            for(int j = 2; j < nums.length; j++) {
                int remainder = target - nums[i] - nums[j];
                if (knowns.containsKey(remainder)) {
                    System.out.println(nums[knowns.get(remainder)] + " + " + nums[i] + " + " + nums[j] + "==" + target);
                }
            }
        }
    }
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(0, 10);
        array.add(0, 11);
        array.add(0, 13);
        for (Integer elem : array) {
            System.out.println(elem);
        }
        int[] nums = new int[] {1, 1, 2, 8, 11, 9, -1};
        new NSums().find3Sum(nums, 2);
    }
}