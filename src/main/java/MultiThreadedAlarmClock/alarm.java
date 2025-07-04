package MultiThreadedAlarmClock;

//public class alarm {
//    public void startAlarming(){
//        synchronized (this){
//            while(alarm.isEmpty()){
//                try{
//
//                }
//            }
//        }
//    }
//}
import java.time.LocalDateTime;

public class alarm {
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    private LocalDateTime dateTime;
    private String reminder;


}

