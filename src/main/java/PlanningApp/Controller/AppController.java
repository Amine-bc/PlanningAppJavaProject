package PlanningApp.Controller;

import PlanningApp.Com.HistoryInterface;
import PlanningApp.Model.Calendar;
import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import PlanningApp.Model.Day;

import java.io.*;

public class AppController implements HistoryInterface {
     static User currentuser ;
     static Calendar currentcalendar ;

      static Day currentday;

      static Task currenttask;
    protected static User getcurrentuser(){
        return currentuser;
    }
    protected static void setcurrentuser(User user){
        currentuser = user;
    }
     public  static String FilePath ;
     public  static void setFilePath(String path){
        FilePath = path;
    }
     static String getFilePath(){
        return FilePath;
    }
     void AppController(){
        // Do I need it ??
    }

    public static void main(String[] args) {
        //check if the file is empty or not
        //if not empty then load login controller
        AppController.setFilePath("src/main/java/PlanningApp/Files/user.ser");
        File file = new File(AppController.FilePath);
        if (file.exists() && file.length() == 0) {
            System.out.println("File is empty.");

            //TODO Ask for registration
            // after registrations add the user to the arraylist in app
            // and do this
            // AppController.setcurrentuser(user);
        } else {
            System.out.println("File is not empty.");
            //TODO Read the users Arraylist from the file
            // load login controller after login happens do this
            // AppController.setcurrentuser(user);
            // App.setCurrentuser(user);
        }


    }


    @Override
    public void bringbackcalendar(String startday, String endday) {
        currentcalendar = User.currentuser.bringbackcalendar(startday,endday,"");
    }
    //TODO: add view here to show the calendar
    // but after executing this method you have to reset the current calendar to currentuser.calendar

}
