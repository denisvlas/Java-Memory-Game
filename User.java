import java.util.ArrayList;

class User {
    String name;
    int highestScore;

    public User(String name, int highestScore) {
        this.name = name;
        this.highestScore = highestScore;
    }
}

class UserList extends ArrayList<User> {
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
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        for (User u : this) {
            if (u.name.equals(user.name)) {
                u.highestScore = user.highestScore;
            }
        }
    }
}
