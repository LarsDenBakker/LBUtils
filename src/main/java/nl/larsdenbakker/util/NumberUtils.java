package nl.larsdenbakker.util;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class NumberUtils {

   /**
    * Get a random integer within the provided range.
    *
    * @param min The minimum value.
    * @param max The maximum value.
    * @return A random integer within the provided range.
    */
   public static int randInt(int min, int max) {
      return min + (int) (Math.random() * ((max - min) + 1));
   }

   /**
    * Get a random double within the provided range.
    *
    * @param min The minimum value.
    * @param max The maximum value.
    * @return A random double within the provided range.
    */
   public static double randDouble(double min, double max) {
      return min + (Math.random() * ((max - min) + 1));
   }

}
