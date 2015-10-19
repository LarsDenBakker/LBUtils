package nl.larsdenbakker.util;

import java.util.Collection;

/**
 * An object that can be 'messaged', this can be a log file, console, user
 * client etc.
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public interface Messageable {

   /**
    * Get the name used to identify this Messageable.
    *
    * @return The name.
    */
   public String getName();

   public void message(Exception exception);

   /**
    * Send one or more messages.
    *
    * @param message The messages.
    */
   public void message(Message... message);

   /**
    * Send a message.
    *
    * @param messageType The type of message.
    * @param message The message.
    */
   public default void message(Message.Type messageType, String message) {
      message(new Message(messageType, message));
   }

   /**
    * Send a message. The provided collection of messages are sent one by one in
    * the order provided by a for-loop.
    *
    * @param messageType The type of message.
    * @param messages The messages.
    */
   public default void message(Message.Type messageType, Collection<String> messages) {
      message(new Message(messageType).add(messages));
   }

   /**
    * Send a message. The provided collection of messages are sent one by one in
    * the order provided by a for-loop.
    *
    * @param messages The messages.
    */
   public default void message(Collection<Message> messages) {
      for (Message message : messages) {
         message(CollectionUtils.asArrayOfType(Message.class, messages));
      }
   }

}
