package pl.javastart;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository {

    private List<User> usersList;

    public UsersRepository() {
        this.usersList = new ArrayList();
        usersList.add(new User("Adam", "Nowak", 23));
        usersList.add(new User("Jan", "Kowalski", 32));
        usersList.add(new User("Maciek", "Olak", 19));
    }

    public List<User> getAll() {
        return usersList;
    }

    public void add(User user) {
        usersList.add(user);
    }
}
