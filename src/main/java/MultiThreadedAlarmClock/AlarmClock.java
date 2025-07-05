package MultiThreadedAlarmClock;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock {
  /*
   * alarm can be set by multiple clients
   * it will have 5 event loops
   *  clients are multiple threads
   * alarm max limit is 5
   * shared resource - list of alarms
   */
    private final List<Alarm> alarms = new ArrayList<>();
    private final int maxAlarms = 5;

    public void pushAlarm(Alarm alarm) {
      synchronized (this) {
        while(alarms.size() == maxAlarms) {
          try {
            wait(); // Wait until an alarm is removed
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
          }
        }
      }
    }
}