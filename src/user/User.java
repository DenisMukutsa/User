package user;

import java.util.ArrayList;
import java.util.Objects;

class User {
    private int id;
    private String name;
    private boolean isAdmin;
    static ArrayList<User> list = new ArrayList<>();

    public User() {}

    public User(int id, String name, boolean isAdmin) throws UserException {
        this.id = id;
        for(User l : list) {
            if (id == l.id) {
                throw new UserException("Идентификатор пользователя не уникален.");
            }
        }

        if(name != null && !name.equals("")) {
            this.name = name;
        }
        else {
            throw new UserException("Не указано имя пользователя.");
        }
        this.isAdmin = isAdmin;
        list.add(this);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        User userCloned = new User();
        if(this.name != null && !this.name.equals("")) {
            userCloned.name = this.name;
        }
        else {
            throw new CloneNotSupportedException("Не указано имя пользователя.");
        }
        userCloned.isAdmin = this.isAdmin;
        userCloned.id = (int) (Math.random() * 10);
        for(User l : list) {
            if (userCloned.id == l.id) {
                userCloned.id =  (int) (Math.random() * 10);
            }
        }
        list.add(userCloned);
        return userCloned;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || !(obj instanceof User)) {
            return false;
        }
        User object = (User) obj;
        if(this.name.equals(object.name) && this.isAdmin == object.isAdmin) {
            return true;
        }
        else {
            return false;
        }
    }

   @Override
    public int hashCode() {
       return Objects.hash(name, isAdmin);
    }

    public static void print() {
        System.out.print("Список id существующих пользователей: ");
        for(User l : list) {
        System.out.print(l.id + ", ");
        }
    }
}