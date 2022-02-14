package skPlanetTest3;

import java.util.*;

public class Friend {
    private Collection<Friend> friends;
    private String email;

    public Friend(String email) {
        this.email = email;
        this.friends = new ArrayList<Friend>();
    }

    public String getEmail() {
        return email;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void addFriendship(Friend friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    public boolean canBeConnected(Friend friend) {
        boolean result = false;

        result = bfs(this, friend);

        return result;
    }

    public static void main(String[] args) {
        Friend a = new Friend("A");
        Friend b = new Friend("B");
        Friend c = new Friend("C");

        a.addFriendship(b);
        b.addFriendship(c);

        System.out.println(a.canBeConnected(c));
    }

    static boolean bfs(Friend friend, Friend target) {
        boolean result = false;
        Queue<Friend> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();

        q.add(friend);
        while (!q.isEmpty()) {
            Friend curr = q.poll();
            set.add(friend.getEmail());

            if (curr.email == target.email) {
                result = true;
                break;
            }

            for (Friend currFriend : curr.getFriends()) {
                if (!set.contains(currFriend.getEmail())) {
                    q.add(currFriend);
                }
            }
        }

        return result;
    }
}