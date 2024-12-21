package algo_202412.BOJ;    // BOJ 19623. 회의실 배정 4

import java.io.*;
import java.util.*;

public class BOJ_19623_회의실배정4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Meeting[] meetings;
    
    static class Meeting{
        int start, end, people;
        public Meeting(int s, int e, int p){
            this.start = s;
            this.end = e;
            this.people = p;
        }
    }

    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(br.readLine());
        meetings = new Meeting[Math.max(N, 2)];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end, people);
        }

        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });

        int[] dp = new int[N];
        dp[0] = meetings[0].people;
        for (int i = 1; i < N; i++){
            int prev = getPrevMeeting(i);

            if (prev > -1)  // 찾았을 경우
                dp[i] = Math.max(meetings[i].people + dp[prev], dp[i-1]);
            else            // 찾지 못했을 경우
                dp[i] = Math.max(meetings[i].people, dp[i-1]);
        }

        System.out.println(dp[N-1]);
    }

    static int getPrevMeeting(int cur){
        int left = 0;
        int right = cur-1;

        while (left <= right){
            int mid = (left+right) / 2;

            if (meetings[mid].end <= meetings[cur].start){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        return right;    // 상한을 찾는 거니까
    }
}
