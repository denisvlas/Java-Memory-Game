import java.util.ArrayList;

class User {
    private String name;
    private int highestScore;

    public User(String name, int highestScore) {
        this.name = name;
        this.highestScore = highestScore;
    }

    String getName() {
        return name;
    }

    int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int score) {
        highestScore = score;
    }
}

public class UserList extends ArrayList<User> {
    public UserList() {
        super();
    }

    public void addUser(User user) {
        this.add(user);
    }

    public void removeUser(User user) {
        this.remove(user);
    }

    public User getUser(String name) {
        for (User user : this) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void displayUsers() {
        System.out.println("Users:");
        for (User user : this) {
            System.out.println(user.getName() + " " + user.getHighestScore());
        }

    }

    public void updateUser(User user) {
        for (User u : this) {
            if (u.getName().equals(user.getName())) {
                u.setHighestScore(Math.max(u.getHighestScore(), user.getHighestScore()));
                return;
            }
        }
        this.add(user);
    }
}
