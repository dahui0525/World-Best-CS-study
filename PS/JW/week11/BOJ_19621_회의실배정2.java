package algo_202412.BOJ;    // BOJ 19621. 회의실 배정 2

import java.util.*;

public class boj_19621 {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] meetings = new int[Math.max(N, 2)];
        for (int i = 0; i < N; i++){
            sc.nextInt();
            sc.nextInt();
            meetings[i] = sc.nextInt();
        }

        int[] dp = new int[Math.max(N, 2)];
        dp[0] = meetings[0];
        dp[1] = Math.max(meetings[0], meetings[1]);
        for (int i = 2; i < N; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + meetings[i]);
        }
        System.out.println(dp[N-1]);
    }
}