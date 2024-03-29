package PlanningApp.Model;


import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TimeSlot implements TimeCalcs, Serializable, Comparable<TimeSlot> {

    private String start;
    private String state ;
    private String end;
    private String duration;
    public TimeSlot(String start, String end){
        this.start = start;
        this.end = end;
        this.duration = subtract(this.end,this.start);
    }

    public String checkstate(){
        return this.state;
    };



    public String getstart(){
        return this.start ;
    }

    public String getend(){
        return this.end ;
    }

    public void setend(String end){
        this.end = end ;
    }

    public void setstart(String start){
        this.start = start ;
    }

    public String getduration(){
        return this.duration ;
    }

    public void setduration(String getduration) {
        this.duration = getduration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (!getstart().equals(timeSlot.getstart())) return false;
        return getend().equals(timeSlot.getend());
    }
    @Override
    public int compareTo(TimeSlot timeSlot) {
        if (Objects.equals(this.getstart(), timeSlot.getstart())){

            if (this.getduration().compareTo(timeSlot.getduration()) > 0) {
                return -1;
            } else if (this.getduration().compareTo(timeSlot.getduration()) < 0) {
                return 1;
            } else if (this.getduration().compareTo(timeSlot.getduration()) == 0) {
                return 1;
            }
            return 0;

        }else if ( this.getstart().compareTo(timeSlot.getstart()) < 0 ){
            return -1 ;
        }else{
            return 1 ;
        }
    }


}
