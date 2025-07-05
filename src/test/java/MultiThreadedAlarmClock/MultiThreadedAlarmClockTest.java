package MultiThreadedAlarmClock;
import org.junit.Test;
import java.time.LocalDateTime;

public class MultiThreadedAlarmClockTest {
    @Test
    public void testAlarmClock() {
        // Create an instance of AlarmClock
        AlarmClock alarmClock = new AlarmClock();

        // Create and push alarms
        for (int i = 0; i < 10; i++) {  // loop runs upto 10 alarms
            final int index = i;
            new Thread(() -> {  //no runnable interface, using lambda expression
                Alarm alarm = new Alarm(LocalDateTime.now().plusSeconds(index + 10), "Alarm " + index);
                alarmClock.pushAlarm(alarm);    // push alarm to the clock
            }).start();
        }
        try {
            while (true) {  // Keep the main thread alive to allow alarms to trigger
                alarmClock.startAlarming(); // Process alarms in the clock
                Thread.sleep(1000);
            }// Wait for 10 seconds to allow alarms to trigger
        } catch (Exception e) {
            //
        }
    }
}
