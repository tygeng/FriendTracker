/**
 *
 */
package cs2114.group.friendtracker.testhelper;

import java.util.ArrayList;

import java.util.Random;

import cs2114.group.friendtracker.Event;

import java.util.List;

/**
 *
 * @author Tianyu
 * @version Apr 22, 2012
 */
public class EventList {
    public static String[] names = { "cs2114", "cs2104", "math 3124",
            "math 2224", "engl 3764" };
    public static String[] times = { "0800", "0815", "0830", "0845",
            "1000", "1015", "1030", "1045", "1200", "1215", "1230",
            "1245", "1400", "1415", "1430", "1445", "1600", "1615",
            "1630", "1645" };
    public static Random rand = new Random(1);
    public static List<Event> getEvents() {

        List<Event> events = new ArrayList<Event>();
        for (int i = 0; i < 5; i++) {
            int t = rand.nextInt(15);
            events.add(new Event(i, names[rand.nextInt(5)], 1, times[t],
                    times[t + 1], "20120101", "20121231", "1111111"
                            .toCharArray()));
        }
        return events;
    }
    public static Event getEvent() {
        int t = rand.nextInt(15);
        return new Event(1, names[rand.nextInt(5)], 1, times[t],
                times[t + 1], "20120101", "20121231", "1111111"
                .toCharArray());
    }
}
