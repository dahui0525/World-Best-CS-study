package algo_202412.BOJ;    // BOJ 2539. 모자이크

import java.io.*;
import java.util.*;

public class BOJ_2539_모자이크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int R, C, P, N;  // R: 행, C: 열, P: 색종이 수, N: 잘못칠한 칸 수
    static int[][] wrong;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());   // ~ 100만
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(br.readLine().trim());   // 종이 수 ~ 100
        N = Integer.parseInt(br.readLine().trim());   // 잘못 칠한 칸 수 ~ 1000

        wrong = new int[N][2];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            wrong[i][0] = Integer.parseInt(st.nextToken()); // 행
            wrong[i][1] = Integer.parseInt(st.nextToken()); // 열
        }
        Arrays.sort(wrong, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int left = 1;
        int right = Math.max(R, C);

        while (left <= right){
            int mid = (left + right)/2;

            if (can(mid)){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean can(int mid){    // **
        int cnt = 0;
        int pre = 0;
        for (int i = 0; i < wrong.length; i++){
            int[] cur = wrong[i];

            if (cur[0] > mid) return false;

            if (pre == 0 || pre + mid <= cur[1]){
                pre = cur[1];
                cnt++;
                if (cnt > P) return false;
            }
        }

        return true;
    }
}