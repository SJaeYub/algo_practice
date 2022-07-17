package swea14568;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    private final static int CMD_INIT = 100;
    private final static int CMD_ADD = 200;
    private final static int CMD_REMOVE = 300;
    private final static int CMD_QUERY = 400;

    static class UserSolution {

        class StudentInfo implements Comparable<StudentInfo> {
            int mid;
            int mGrade;
            int mGender;
            int mScore;

            StudentInfo(int id, int gr, int ge, int s) {
                this.mid = id;
                this.mGrade = gr;
                this.mGender = ge;
                this.mScore = s;
            }

            @Override
            public int compareTo(StudentInfo s1) {
                if (s1.mScore > this.mScore) {
                    return 1;
                } else if (s1.mScore == this.mScore) {
                    if (s1.mid > this.mid) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }

            }
        }

        ArrayList<StudentInfo>[][] arr;        //male : 0, female : 1, 1학년 : 1 ....
        HashMap<Integer, StudentInfo> hashMap;        //데이터베이스를 의미(mid, 학생정보)

        public void init() {

            arr = new ArrayList[2][4];
            hashMap = new HashMap<>();

            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= 3; j++) {
                    arr[i][j] = new ArrayList<>();
                }
            }

            return;
        }

        public int add(int mId, int mGrade, char mGender[], int mScore) {
            int gender_temp = -1;

            if (mGender[0] == 'm') {
                gender_temp = 0;
            } else if (mGender[0] == 'f') {
                gender_temp = 1;
            }

            StudentInfo curr = new StudentInfo(mId, mGrade, gender_temp, mScore);
            arr[gender_temp][mGrade].add(curr);

            hashMap.put(mId, curr);

            Collections.sort(arr[gender_temp][mGrade]);
            return arr[gender_temp][mGrade].get(0).mid;
        }

        public int remove(int mId) {

            if (!hashMap.containsKey(mId)) {        //갖고 있지 않다면
                return 0;
            }

            //갖고 있다면
            StudentInfo remove = hashMap.remove(mId);

            int idx = Collections.binarySearch(arr[remove.mGender][remove.mGrade], remove);
            int before_idx = Math.abs(idx) - 1;
            int after_idx = Math.abs(idx) - 2;

            if(before_idx >= 0 && before_idx < arr[remove.mGender][remove.mGrade].size()) {
                if (arr[remove.mGender][remove.mGrade].get(before_idx).mid == remove.mid) {
                    arr[remove.mGender][remove.mGrade].remove(before_idx);
                }
            }
            if (after_idx >= 0 && after_idx < arr[remove.mGender][remove.mGrade].size()) {
                if (arr[remove.mGender][remove.mGrade].get(after_idx).mid == remove.mid) {
                    arr[remove.mGender][remove.mGrade].remove(after_idx);
                }
            }

            int result = -1;

            if (arr[remove.mGender][remove.mGrade].size() == 0) {
                result = 0;
            } else {
                Collections.sort(arr[remove.mGender][remove.mGrade]);
                result = arr[remove.mGender][remove.mGrade].get(arr[remove.mGender][remove.mGrade].size() - 1).mid;
            }

            return result;
        }

        public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {

//            LinkedList<StudentInfo> list = new LinkedList<>();
            StudentInfo target = new StudentInfo(-1, 1, 0, mScore);
            StudentInfo temp = new StudentInfo(-1, 1, 0, mScore);
            ArrayList<StudentInfo> list_tmp = new ArrayList<>();

            for (int i = 0; i < mGradeCnt; i++) {
                for (int j = 0; j < mGenderCnt; j++) {
                    int gender_temp = -1;
                    if (mGender[j][0] == 'm') {
                        gender_temp = 0;
                    } else if (mGender[j][0] == 'f') {
                        gender_temp = 1;
                    }

                    ArrayList<StudentInfo> students = arr[gender_temp][mGrade[i]];

                    int idx = Collections.binarySearch(students, temp);
                    if (idx != -1) {
                        target = students.get(Math.abs(idx) - 2);
                        list_tmp.add(target);
                    } else if (idx == -1) {
                        ;
                    }
                }
            }
            int result = 0;
//            Collections.sort(list);
            Collections.sort(list_tmp);

            if (list_tmp.size() == 0) {
                result = 0;
            } else {
                result = list_tmp.get(list_tmp.size() - 1).mid;
            }

            return result;
        }
    }

    private final static UserSolution usersolution = new UserSolution();


    private static void String2Char(char[] buf, String str) {
        for (int k = 0; k < str.length(); ++k)
            buf[k] = str.charAt(k);
        buf[str.length()] = '\0';
    }

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int id, grade, score;
        int cmd, ans, ret;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    okay = true;
                    break;
                case CMD_ADD:
                    char[] gender = new char[7];
                    id = Integer.parseInt(st.nextToken());
                    grade = Integer.parseInt(st.nextToken());
                    String2Char(gender, st.nextToken());
                    score = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.add(id, grade, gender, score);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_REMOVE:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.remove(id);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_QUERY:
                    int gradeCnt, genderCnt;
                    int[] gradeArr = new int[3];
                    char[][] genderArr = new char[2][7];
                    gradeCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < gradeCnt; ++j) {
                        gradeArr[j] = Integer.parseInt(st.nextToken());
                    }
                    genderCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < genderCnt; ++j) {
                        String2Char(genderArr[j], st.nextToken());
                    }
                    score = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.query(gradeCnt, gradeArr, genderCnt, genderArr, score);
                    if (ret != ans)
                        okay = false;
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("src/swea14568/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}