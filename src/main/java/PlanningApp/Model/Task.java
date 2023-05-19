package PlanningApp.Model;

import javafx.scene.paint.Color;

import java.io.*;

public abstract class Task implements TaskUser, TimeCalcs, Serializable,Comparable<Task> {

    public Task(){}; // default constructor
    public Task(String name, String duration, String starttime, int Priority, String day) {
        this.name = name;
        this.duration = duration;
        this.starttime = starttime;
        this.endtime = add(starttime, duration) ;
        this.state = State.notRealized;
        this.Priority = Priority;
        this.day = day;
    }
    public Task(String name, String duration,int Priority) {
        this.name = name;
        this.duration = duration;
        this.state = State.notRealized;
        this.Priority = Priority;
        this.endtime = null;
        this.starttime = null;
        this.day = null;
    }
    private String name ;
    private State state ;
    private String duration ;
    private String starttime;
    private String endtime;
    private String Category ;
    private String Color ;
    private int Priority;
    private String day ;

    public void setCategory(String category) {
        Category = category;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getEndtime() {
        return endtime;
    }
    // setters and getters
    public void setName(String name) {
        this.name = name;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getName() {
        return name;
    }
    public State getState() {
        return state;
    }
    public String getDuration() {
        return duration;
    }
    public String getStarttime() {
        return starttime;
    }
    public boolean equals(Object task) {
        if (this == task) return true;
        if (!(task instanceof Task)) return false;
        // start time and end time
        if (!getStarttime().equals(((Task)task).getStarttime())) return false;
        return getEndtime().equals(((Task)task).getEndtime());
    }
    // compare to
    public int compareTo(Task task) {
        if ( this.getStarttime() == task.getStarttime()){

            if (this.Priority > task.Priority) {
                return 1;
            } else if (this.Priority < task.Priority) {
                return -1;
            } else if (this.Priority == task.Priority) {
                return 1;
            }
            return 0;

        }else if ( this.getStarttime().compareTo(task.getStarttime()) < 0 ){
            return -1 ;
        }else{
            return 1 ;
        }
    }
}
