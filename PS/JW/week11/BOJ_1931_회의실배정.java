package algo_202412.BOJ;    // BOJ 1931. 회의실 배정

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1931_회의실배정 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++){
            meetings[i][0] = sc.nextInt();  // 시작시간
            meetings[i][1] = sc.nextInt();  // 끝나는시간
        }

        // 회의를 최대한 많이 하자!
        // = 끝나는 시간 순으로 오름차순 정렬해서, 현재 회의가 끝나고 난 뒤에 열리는 회의 중 가장 빨리 끝나는 것들을 선택해주면서 계산
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 종료시간이 같을 경우 ? 시작 시간이 빠른 순으로 정렬한다.
                // 예를들어, (7,8)과 (8,8)이 주어질 경우에
                // 시작시간이 빠른 순으로 정렬하지 않으면 -> (8,8)이 먼저 선택되어 현재 회의의 종료시간이 8이 되기 때문에, 그 다음 (7,8)은 고려조차 할 수 없다.
                // 그래서 시작 시간이 빠른 순으로 정렬하여 (7,8)을 먼저 보고, 현재 회의의 종료시간이 8로 갱신되어도 (8,8)을 볼 수 있도록 하는 것이다.
                if (o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int cnt = 0;
        int cur_end = 0;
        for (int i = 0; i < N; i++){
            if (cur_end <= meetings[i][0]){
                cur_end = meetings[i][1];
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
