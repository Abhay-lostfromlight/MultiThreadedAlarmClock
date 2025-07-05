package MultiThreadedAlarmClock;

import org.junit.Test;

import java.time.LocalDateTime;

public class MultiThreadedAlarmClockTest {
    @Test
    public void testAlarmClock() {
        // Create an instance of AlarmClock
        AlarmClock alarmClock = new AlarmClock();

        // Create and push alarms
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(() -> {
                Alarm alarm = new Alarm(LocalDateTime.now().plusSeconds(index + 10), "Alarm " + index);
                alarmClock.pushAlarm(alarm);
            }).start();
        }
        try {
            while (true) {
                alarmClock.startAlarming();
                Thread.sleep(1000);
            }// Wait for 10 seconds to allow alarms to trigger
        } catch (Exception e) {
            //
        }
    }
}
