package PlanningApp.Model;

import java.io.*;
import java.util.HashMap;

public class App {
    // this class is the main class of the model part of the application
    public static HashMap<String,User> users ;
    public static User currentuser = null;


    //TODO do not forget to set the currentuser
    public static void setCurrentuser(User user){
        currentuser = user;
    }
    public static User getCurrentuser(){
        return currentuser;
    }
    public App(){
        users = new HashMap<String,User>();
    }
    static public void addUser(User user){
        users.put(user.getname(),user);
    }
    public void setUsers(HashMap<String,User> users){
        this.users = users;
    }
    public void removeUser(User user){
        users.remove(user.getname());
    }


   public static void init(){
       users = new HashMap<String,User>();
   }
    public static void main(String[] args) {
        // this method is the main method of the model part of the application
        // it is used to test the model classes
        App app = new App();
        User user = new User("Amine","9876");
        User user2 = new User("Hamid","2");
        app.addUser(user);
        app.addUser(user2);
        app.SaveToDb("src/main/java/PlanningApp/Files/users.ser");
        app.users = null ;
        app.ReadfromDb("src/main/java/PlanningApp/Files/users.ser");
       System.out.println(app.users.get("Amine").profile.getpassword());

        }

    public static void ReadfromDb(String FilePath)
    {
        try
        {
            FileInputStream fileOut2 = new FileInputStream(FilePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileOut2);
            // read the user object
            users = (HashMap<String,User>) objectIn.readObject();
            System.out.println("users list deserialized successfully.");

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not Found");
        }
        catch (Exception e1){
            System.out.println(e1.getStackTrace());
        }


    }

    public static void SaveToDb(String FilePath){
        try {
            FileOutputStream fileOut = new FileOutputStream(FilePath);
            ObjectOutputStream usersmap = new ObjectOutputStream(fileOut);
            usersmap.writeObject(users);
            System.out.println("users list serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
