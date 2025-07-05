package MultiThreadedAlarmClock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlarmClock {

  /*
   * alarm can be set by multiple clients
   * it will have 5 event loops
   * clients are multiple threads
   * alarm max limit is 5
   * shared resource - list of alarms
   */
    private final List<Alarm> alarms = new ArrayList<>();
    private final int maxAlarms = 5;

    public void pushAlarm(Alarm alarm) {    //main alarm thread (event loop)
      synchronized (this) {
        while(alarms.size() == maxAlarms) {
          try {
            wait(); // Wait until an alarm is removed
          }catch (InterruptedException e) {
             Thread.currentThread().interrupt(); // Restore interrupted status
             return;
          }
        }
        if((alarm.getDateTime().isAfter(LocalDateTime.now()))) {
            alarms.add(alarm);
            notifyAll();
        }
      }
    }
    public void startAlarming() {
        synchronized (this) {
            while (alarms.isEmpty()) {
                try {
                    wait(); // Wait until an alarm is added
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    return;
                }
            }
            // Process alarms
            Alarm alarm = alarms.removeLast(); // Get the first alarm
            System.out.println("Alarm triggered: " + alarm.getReminder());
            notifyAll(); // Notify waiting threads that an alarm has been processed
        }
    }
}