package user;

public class Main {
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
            User.print();
            System.out.println();
            User user4 = new User(140, "Васильев", false);

        }
        catch(UserException e) {
            System.out.println(e.getMessage());
        }
        catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}
