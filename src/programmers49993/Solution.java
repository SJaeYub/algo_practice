package programmers49993;

public class Solution {

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));
    }

    static int solution(String skills, String[] skill_trees) {
        int answer = 0;

        for (String skill : skill_trees) {
            String skl_tmp = skill;

            for (int i = 0; i < skill.length(); i++) {
                String tmp = skill.substring(i, i + 1);
                if (!skills.contains(tmp)) {
                    skl_tmp = skl_tmp.replace(tmp, "");
                }
            }

            if (skills.indexOf(skl_tmp) == 0) {
                answer++;
            }
        }

        return answer;
    }
}
