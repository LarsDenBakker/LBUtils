package nl.larsdenbakker.util;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public enum Direction {

   NORTH,
   NORTH_EAST,
   EAST,
   SOUTH_EAST,
   SOUTH,
   SOUTH_WEST,
   WEST,
   NORTH_WEST;

   /**
    * Get a Direction (N/NE/E/SE/S/SW/W/NW) based on a yaw value.
    *
    * @param direction The yaw value.
    * @return The Direction.
    */
   public static Direction fromYaw(double direction) {
      if (direction < 0) {
         direction += 360;
      }
      if (direction >= 202.5 && direction < 247.5) {
         return NORTH_EAST;
      }
      if (direction >= 247.5 && direction < 292.5) {
         return EAST;
      }
      if (direction >= 292.5 && direction < 337.5) {
         return SOUTH_EAST;
      }
      if (direction >= 337.5 && direction < 360
          || direction >= 0 && direction < 22.5) {
         return SOUTH;
      }
      if (direction >= 22.5 && direction < 67.5) {
         return SOUTH_WEST;
      }
      if (direction >= 67.5 && direction < 112.5) {
         return WEST;
      }
      if (direction >= 112.5 && direction < 157.5) {
         return NORTH_WEST;
      }
      return NORTH;// if (direction >= 157.5 && < 202.5)
   }

   /**
    * Get a cardinal direction (N/E/S/W) based on a yaw value.
    *
    * @param direction The yaw value.
    * @return The cardinal direction.
    */
   public static Direction fromYawCardinal(double direction) {
      if (direction < 0) {
         direction += 360;
      }
      if (direction >= 225 && direction < 315) {
         return EAST;
      }
      if (direction >= 315 && direction < 0
          || direction >= 0 && direction < 22.5) {
         return SOUTH;
      }
      if (direction >= 45 && direction < 135) {
         return WEST;
      }
      return NORTH;//if (direction >= 135 && < 225)
   }

   /**
    * Get a diagonal direction (NE/SE/SW/NW) based on a yaw value.
    *
    * @param direction The yaw value.
    * @return The cardinal direction.
    */
   public static Direction fromYawDiagonal(double direction) {
      if (direction < 0) {
         direction += 360;
      }
      if (direction >= 270 && direction <= 360) {
         return SOUTH_EAST;
      }
      if (direction >= 0 && direction < 90) {
         return SOUTH_WEST;
      }
      if (direction >= 90 && direction < 180) {
         return NORTH_WEST;
      }
      return NORTH_EAST;//if (direction >= 180 && < 270)
   }

}
