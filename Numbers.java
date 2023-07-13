import java.io.*;
import java.util.*;

public class Numbers {
    static StringBuilder sb;
    static ArrayList<String> matchups;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        permute(nums, 0, null);
        PrintWriter pw = new PrintWriter(new FileWriter(new File("data.txt")));
        pw.print(sb.toString());
        pw.flush();
    }

    public static void permute(int[] nums, int start, int[] nums2) {
        if (start == nums.length) {
            for (int i = 0; i < 3; i++) {
                if (nums[i*2] > nums[i*2+1]) {
                    return;
                }
            }
            if (nums2 == null) {
                for (int i = 0; i < 2; i++) {
                    if (nums[i*2] > nums[i*2+2]) {
                        return;
                    }
                }
            }
            if (nums2 == null) {
                print(nums);
                int[] newNums = new int[]{1, 2, 3, 4, 5, 6, 7};
                matchups = new ArrayList<>();

                permute(newNums, 0, nums);

                Collections.sort(matchups);
                sb.append("[");
                for (int i = 0; i < matchups.size(); i++) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append("'");
                    sb.append(matchups.get(i));
                    sb.append("'");
                }
                sb.append("],\n");
            }
            else {
                for (int i = 0; i < 3; i++) {
                    int sum1 = nums[i*2]+nums[i*2+1];
                    int sum2 = nums2[i*2]+nums2[i*2+1];
                    if (Math.abs(sum1-sum2) > 3) {
                        return;
                    }
                }
                StringBuilder sbMatchup = new StringBuilder();
                for (int i = 0; i < nums.length; i++) {
                    sbMatchup.append(nums[i]);
                }
                matchups.add(sbMatchup.toString());
            }
            return;
        }
        permute(nums, start+1, nums2);
        for (int i = start+1; i < nums.length; i++) {
            swap(nums, start, i);
            permute(nums, start+1, nums2);
            swap(nums, start, i);
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void print(int[] nums) {
        sb.append("'");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            // System.out.print(nums[i]);
        }
        // System.out.println();
        sb.append("'");
        sb.append(": ");
    }

    public static void print2(int[] nums) {
        // sb.append("   ");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        sb.append(", ");
    }
}