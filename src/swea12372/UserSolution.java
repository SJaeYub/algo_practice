package swea12372;


public class UserSolution {

    static class UserR {
        int uid;
        int income;

        UserR(int u, int i) {
            this.uid = u;
            this.income = i;
        }
    }

    UserR[] arr;
    int size = 0;

    public void init() {
        arr = new UserR[100000 + 1];
        size = 0;
    }

    public void addUser(int uID, int income) {
        arr[size] = new UserR(uID, income);
//            System.out.println("uid : " + uID + " income : " + income);
        size++;
    }

    int getTop10(int[] result) {
        int cnt = 0;
        quick_sort(arr, 0, size - 1);

        for (UserR user : arr) {
            if (arr[cnt] != null && cnt < 10) {
                //                   System.out.println("uid : " + user.uid + " income : " + user.income);
                result[cnt] = user.uid;
                cnt++;
            } else {
                break;
            }
        }

        return cnt;
    }

    public void quick_sort(UserR[] temp, int left, int right) {
        if (left < right) {
            int p = left, i = left + 1, j = right;
            while (i <= j) {
                while (temp[i] != null && temp[i].income >= temp[p].income) {
                    if (temp[i].income == temp[p].income && temp[i].uid < temp[p].uid) {
                      i++;
                        continue;
                    }
                    i++;
              }

                while (temp[j].income < temp[p].income) {
                    j--;
                }

                if (i < j) {
                    UserR user = temp[i];
                    temp[i] = temp[j];
                    temp[j] = user;
                }
            }

            UserR user = temp[p];
            temp[p] = temp[j];
            temp[j] = user;

            quick_sort(temp, left, j - 1);
            quick_sort(temp, j + 1, right);
        }

    }

}