package user; 

import java.util.ArrayList;

public class User {
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
        return userCloned;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
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
       int admin = (this.isAdmin == true) ? 1 : -1;
       final int prime = 43;
       int hash = 1;
       int nameLength = this.name.length();
       int charFirst = this.name.charAt(0);
       int charSecond = this.name.charAt(1);
       int charLast = this.name.charAt(this.name.length()-1);
       return hash = prime * 3 + nameLength + charFirst + charSecond + charLast + admin;
    }

    public static void main(String[] args) {
        try{
            User user1 = new User(123, "Петров", true);
            User user2 = new User(3, "Иванов", false);
            User user3 = new User(140, "Сергеев", false);
            User user1Cloned = (User)user1.clone();
            System.out.println("Пользователь 1 равен клонированному пользователю: " + user1.equals(user1Cloned));
            System.out.println("Пользователь 1 равен пользователю 2: " + user1.equals(user2));
            System.out.println("Хеш-код Пользователя 1: " + user1.hashCode());
            System.out.println("Хеш-код клонированного пользователя: " + user1Cloned.hashCode());
            System.out.println("Хеш-код Пользователя 2: " + user2.hashCode());
            System.out.println("Хеш-код Пользователя 3: " + user3.hashCode());
            User user4 = new User(123, "Васильев", false);
        }
        catch(UserException e) {
            System.out.println("Неверно указаны параметры пользователя.");
        }
        catch (CloneNotSupportedException e) {
            System.out.println("Клонирование не поддерживается.");
        }
    }
}

class UserException extends Exception {
    public UserException(String msg) {
        System.out.println(msg);
    }
}
