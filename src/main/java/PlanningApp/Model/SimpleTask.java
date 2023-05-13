package PlanningApp.Model;

public class SimpleTask extends Task{

    public SimpleTask(String name,String duration, String starttime,String endtime,String state){
        // this constructor is used once someone clicks on new task
        // first we ask if he wants to auto plan it or manually
        // second we ask for its type decomp or simple
        // Then create an instance then
            this.name = name;
            this.starttime =starttime;
            this.endtime = endtime ;
            this.duration =duration ;
            this.state = State.valueOf(state.toUpperCase());

    }


    @Override
    public void planifyman(String time,String duration) {
        this.state = State.SCHEDULED ;
    }
    @Override
    public void planifyauto(String startperiod, String endperiod, String day) {

        if (  Integer.toString((Integer.parseInt(startperiod) + Integer.parseInt(duration))).compareTo(endperiod) == 0 ){

        this.starttime =startperiod;
        this.endtime =endperiod ;
        this.state = State.SCHEDULED ;
    }else{
            System.out.println("Error in planification down in simple task model");
        }
    }
    @Override
    public void postpone(String time) {
        //change the start time and end time
        if (Integer.parseInt(time) > Integer.parseInt(starttime)){
            this.starttime =time;
            this.endtime = Integer.toString(Integer.parseInt(time) + Integer.parseInt(duration)) ;}
        else{
            System.out.println("Error in postponing down in simple task model");
        }
    }

    @Override
    public void replan(String time) {
        //change the start time and end time
        this.starttime =time;
        this.endtime = Integer.toString(Integer.parseInt(time) + Integer.parseInt(duration)) ;

    }
    @Override
    public void evaluate() {

    }

    // setter and getters for the attributes
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public State getState() {
        return state;
    }
    public void setState(String state) {
        this.state = State.valueOf(state.toUpperCase());
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getEndtime() {
        return endtime;
    }
}