package nl.larsdenbakker.util;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public interface MessageListener {
   
   public void onMessage(Messageable messageable, Message... messages);
   
}
