package skPlanetTest1;

import java.util.*;

public class Hobbies {

    private final HashMap<String, String[]> hobbies = new HashMap<String, String[]>();

    public void add(String hobbyist, String... hobbies) {
        this.hobbies.put(hobbyist, hobbies);
    }

    public List<String> findAllHobbyists(String hobby) {
        ArrayList<String> result_list = new ArrayList<>();

        for (String name : hobbies.keySet()) {
            String[] setOfHobby = hobbies.get(name);

            for (String cmp : setOfHobby) {
                if (cmp.equals(hobby)) {
                    result_list.add(name);
                }
            }
        }

        return result_list;
    }

    public static void main(String[] args) {
        Hobbies hobbies = new Hobbies();
        hobbies.add("Steve", "Fashion", "Piano", "Reading");
        hobbies.add("Patty", "Drama", "Magic", "Pets");
        hobbies.add("Chad", "Puzzles", "Pets", "Yoga");

        System.out.println(Arrays.toString(hobbies.findAllHobbyists("Yoga").toArray()));
    }
}