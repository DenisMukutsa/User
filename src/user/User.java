package user;

import java.util.ArrayList;

class User {
    private int id;
    private String name;
    private boolean isAdmin;
    static ArrayList<User> list = new ArrayList<>();

    public User() {

    }

    public User(int id, String name, boolean isAdmin) throws UserException {
        this.id = id;
        if(checkId(this.id)) throw new UserException("Идентификатор пользователя не уникален.");

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
    protected Object clone() throws CloneNotSupportedException  {
        String tempName;
        int tempId = 0;
        boolean tempIsAdmin = this.isAdmin;
        User userCloned = null;

        if(this.name != null && !this.name.equals("")) {
            tempName = this.name;
        }
        else {
            throw new CloneNotSupportedException("Не указано имя пользователя.");
        }

        int tempNumber = (int) (Math.random() * 10);
        if (!checkId(tempNumber)) {
            tempId = tempNumber;
        }

        try{
            userCloned = new User(tempId, tempName, tempIsAdmin);
        }
        catch (UserException e) {
            e.getMessage();
        }
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
       return this.name.hashCode() + ((Boolean)this.isAdmin).hashCode();
    }

    public boolean checkId(int id) {
        ArrayList<Integer> idList = new ArrayList<>();
        for(User l : list) {
            idList.add(l.id);
        }
        return idList.contains(id);
    }

    public static void print() {
        System.out.print("Список id существующих пользователей: ");
        for(User l : list) {
        System.out.print(l.id + ", ");
        }
    }
}