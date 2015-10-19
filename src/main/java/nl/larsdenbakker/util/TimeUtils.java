package nl.larsdenbakker.util;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class TimeUtils {

   public static final DateTimeFormatter DMY_FORMAT = DateTimeFormatter.ofPattern("d-MM-yyyy");
   public static final DateTimeFormatter MDY_FORMAT = DateTimeFormatter.ofPattern("MM-d-yyyy");
   public static DateTimeFormatter DEFAULT_FORMAT = DMY_FORMAT;

   public static final long SECOND_MS = 1000;
   public static final long MINUTE_MS = SECOND_MS * 60;
   public static final long HOUR_MS = MINUTE_MS * 60;
   public static final long DAY_MS = HOUR_MS * 24;
   public static final long WEEK_MS = DAY_MS * 7;

   /**
    * @param time The time span in milliseconds.
    *
    * @return A human readable description of the time span.
    */
   public static String toTimeSpanDescription(long time) {
      int days = (int) (time / DAY_MS);
      time -= days * DAY_MS;
      int hours = (int) (time / HOUR_MS);
      time -= hours * HOUR_MS;
      int minutes = (int) (time / MINUTE_MS);
      time -= minutes * MINUTE_MS;
      int seconds = (int) (time / SECOND_MS);
      time -= seconds * SECOND_MS;
      int ms = (int) time;
      if (days != 0) {
         return days + " days and " + hours + " hours";
      } else if (hours != 0) {
         return hours + " hours and " + minutes + " minutes";
      } else if (minutes != 0) {
         return minutes + " minutes and " + seconds + " seconds";
      } else if (seconds != 0) {
         return seconds + " seconds";
      } else {
         return ms + " ms.";
      }
   }

}
