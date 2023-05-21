package PlanningApp.Controller;

import PlanningApp.Model.SimpleTask;
import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import PlanningApp.View.FirstPageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import PlanningApp.Model.Calendar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private GridPane daysOfWeekGrid;

    @FXML
    private HBox CalendarContainer;
    @FXML
    private Button previousMonthButton;

    @FXML
    private Button nextMonthButton;

    private Calendar calendarModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO user Calendar is a static proprety it must called :
        calendarModel = new Calendar();
        updateView();
    }



    public void handlePreviousMonthButton() {
        calendarModel.goToPreviousMonth();
        updateView();
    }

    public void handleNextMonthButton() {
        calendarModel.goToNextMonth();
        updateView();
    }

    private void updateView() {
        YearMonth yearMonth = YearMonth.from(calendarModel.getCurrentDate());
        monthYearLabel.setText(yearMonth.getMonth().toString() + " " + yearMonth.getYear());

        LocalDate currentDate = LocalDate.now();

        calendarGrid.getChildren().clear();
        daysOfWeekGrid.getChildren().clear();

        int daysInMonth = yearMonth.lengthOfMonth();
        int startDayOfMonth = yearMonth.atDay(1).getDayOfWeek().getValue();

        // Display days of the week in the daysOfWeekGrid with space between each day
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayOfWeekLabel = new Label(daysOfWeek[i]);
            dayOfWeekLabel.setStyle("-fx-font-weight: bold;");
            dayOfWeekLabel.setTextAlignment(TextAlignment.CENTER);
            dayOfWeekLabel.setTextFill(Color.web("#0598FF"));
            // Add a space between each day of the week
            HBox dayOfWeekContainer = new HBox(dayOfWeekLabel);

            HBox.setMargin(dayOfWeekLabel, new Insets(0, 60, 0, 0));

            daysOfWeekGrid.add(dayOfWeekContainer, i, 0);
        }

        int rowIndex = 1;
        int columnIndex = startDayOfMonth % 7; // Adjust columnIndex calculation

        for (int day = 1; day <= daysInMonth; day++) {
            Pane dayPane = new Pane();
            dayPane.setMinSize(80, 80);

            Circle circle = new Circle(15);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.web("#0598FF"));

            Label dayLabel = new Label(String.valueOf(day));
            dayLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
            dayLabel.setTextFill(Color.BLACK);
            dayLabel.setTextAlignment(TextAlignment.CENTER);
            dayLabel.setLayoutX(22);
            dayLabel.setLayoutY(19);

            if (currentDate.equals(yearMonth.atDay(day))) { // Check if it's the current day
                circle.setFill(Color.web("#0598ff"));
                dayLabel.setTextFill(Color.web("#0598FF"));
            }

            final int clickedDay = day; // Create a final copy of the day variable

            dayPane.getChildren().addAll(circle, dayLabel);
            dayPane.setOnMouseClicked(event -> showAlert(clickedDay)); // Use the final copy in the lambda expression

            circle.setCursor(Cursor.HAND);
            calendarGrid.add(dayPane, columnIndex, rowIndex);

            columnIndex++;
            if (columnIndex == 7) {
                columnIndex = 0;
                rowIndex++;
            }
            CalendarContainer.setStyle("-fx-border-color: #0598FF; -fx-border-width: 6px; -fx-border-radius: 15px;");
            VBox.setMargin(CalendarContainer,new Insets(0,20,20,20));
        }
    }





    private void showAlert(int day) {
        LocalDate currentDate = LocalDate.of(calendarModel.getCurrentDate().getYear(), calendarModel.getCurrentDate().getMonth(), day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE", Locale.ENGLISH);
        String formattedDate = currentDate.format(formatter);

        System.out.println(formattedDate);
        AppController.currentday=AppController.currentuser.getCalendar().getDays().get(formattedDate);
        AppController.currentday.setDayname(formattedDate);
        System.out.println("name"+AppController.currentday.getDayname());

        //System.out.println("Day clicked is " + formattedDate);

        //String date ="2021-02-11Thursday";
        //String date2 ="2021-02-12Friday";
        //String date3 ="2021-02-13Saturday";

        //User user = new User("Amine","password");
        //user.createCalendar(2021,2022,2,10,10,30);
        //Task task = new SimpleTask("task1","01:00",date,"03:00",0);
//      //System.out.println("name"+task.getName()+"duration"+task.getDuration()+"priority"+task.getPriority()+"starttime"+task.getStarttime()+"endtime"+task.getEndtime());
        //user.addtimeslot(date,"02:00","04:30");
        //user.addtimeslot(date,"10:00","12:00");
        //user.addtimeslot(date,"14:00","16:00");
        //user.addtimeslot(date2,"02:00","05:00");
        //user.addtimeslot(date2,"10:00","11:00");
        //user.addtimeslot(date2,"14:00","16:00");
        //user.addtimeslot(date3,"02:00","05:00");
        //user.addtimeslot(date3,"10:00","11:00");
        //user.addtimeslot(date3,"14:00","16:00");
//      //user.planifyman(task,date2);
//      //user.planifyman(task,date);
        //System.out.println("Beforeplanifying");
        //System.out.println("Day1"+date+"\n");
        //user.getCalendar().getDays().get(date).printDay();
//      //System.out.println("Day2"+date2+"\n");

        //user.getCalendar().getDays().get(date2).printDay();
        //user.planifyman(task,date);
        //System.out.println("Afterplanifying");
        //user.getCalendar().getDays().get(date).printDay();



        // set it to static :
        //AppController.currentuser=user;
        //AppController.currentday=user.getCalendar().getDays().get(date);
        //AppController.currentday.setDayname(formattedDate);
        try {
            int a=1;
            if(a==0){
                //here we make a test
                // TODO make a test if the day is in the period:
                // ida day li khayrou luser may'existich fi la periode li kheyerha l user :
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Day Clicked");
                alert.setHeaderText(null);
                alert.setContentText(formattedDate+" : Day Clicked out of period");
                alert.showAndWait();
            }else{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/DayPage.fxml"));

                Parent root = loader.load();


                // Access the DayController and set the Day object
                DayController dayController = loader.getController();

                dayController.Showday( formattedDate);


                // Set the new FXML file as the scene
                Scene scene = new Scene(root);

                // Get the primary stage
                Stage primaryStage = (Stage) monthYearLabel.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setMinWidth(600);
                primaryStage.setMinHeight(400);
                primaryStage.setMaxWidth(600);
                primaryStage.setMaxHeight(400);
                primaryStage.show();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }




    }



}

