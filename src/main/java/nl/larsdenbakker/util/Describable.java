package nl.larsdenbakker.util;

/**
 * A utility interface to aid in communicating better understandable
 * descriptions of objects to end users and log files.
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public interface Describable {

   /**
    * Describe this object's type. By default this is getClass().getSimpleName,
    * implementations can override this to provide a more coherent description.
    *
    * @return The description.
    */
   public default String getTypeDescription() {
      return getClass().getSimpleName();
   }

   /**
    * Describe this object in a way users can easily understand. This can be a
    * uniquely identifiable key.
    *
    * @return The description.
    */
   public String getDescription();

   /**
    * Get a description of both of object's type and value. See getTypeDescription() and getDescription() for more.
    *
    * @return The description.
    */
   public default String getTypeAndValueDescription() {
      return TextUtils.getTypeAndValueDescription(this);
   }
}
