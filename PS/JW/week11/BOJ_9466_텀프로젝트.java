package algo_202412.BOJ;    // BOJ 9466. 텀 프로젝트

import java.io.*;
import java.util.*;

public class boj_9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, n, cnt;
    static int[] s;
    static boolean[] visit, done;

    public static void main(String[] args) throws Exception{
        // 팀원 수 제한 X, 모든 학생이 한 팀일 수도 있음.
        // 모든 학생은 함께 하고싶은 학생을 선택해야한다. (자기자신도 가능)
        // 학생들 : s1, s2, ..., sr
        // 서로 cycle을 이루어야 한 팀이 될 수 있음. (또는 r=1, 스스로를 선택하는 경우)
        // 팀이 없는 학생의 수를 구하자.

        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++){
            n = Integer.parseInt(br.readLine().trim());
            s = new int[n+1];
            done = new boolean[n+1];
            visit = new boolean[n+1];
            cnt = 0;

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 1; i <= n; i++){
                s[i] = Integer.parseInt(st.nextToken());
            }

            // 계산
            for (int i = 1; i <= n; i++){
                dfs(i);
            }
            System.out.println(n - cnt);
        }
    }

    static void dfs(int idx){
        if (done[idx]) return;  // 이미 검사 완료된 것이라면, 더이상 탐색할 필요가 없다. dfs 내에서 하는 이유는 중간에도 탐색 이미 끝낸 친구를 만날 수 있기 때문
        if (visit[idx]){        // 이미 방문했다면 = 사이클 구성
            done[idx] = true;   // 사이클 구성원이니까, 확인 끝난 학생임을 표시
            cnt++;
        }
        visit[idx] = true;  // 방문체크
        dfs(s[idx]);
        done[idx] = true;   // 사이클 구성원이 아니어도 확인 끝냈음 표시
        visit[idx] = false; // 원복
    }
}
