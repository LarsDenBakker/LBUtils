package nl.larsdenbakker.util;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public abstract class AbstractMessageable implements Messageable {

   private final String name;
   private final List<String> messageLog = new ArrayList();

   private final EnumMap<Message.Type, Boolean> toMessageForType = new EnumMap(Message.Type.class);

   public AbstractMessageable(String name) {
      this.name = name;
      setDefaults();
   }

   protected void setDefaults() {
      toMessageForType.put(Message.Type.RAW, true);
      toMessageForType.put(Message.Type.INFO, true);
      toMessageForType.put(Message.Type.WARNING, true);
      toMessageForType.put(Message.Type.ERROR, true);
      toMessageForType.put(Message.Type.DEBUG, false);
   }

   public void setToMessageForAllTypes() {
      for (Message.Type messageType : Message.Type.values()) {
         setToMessageForType(messageType, true);
      }
   }

   public void setToMessageForType(Message.Type messageType, boolean toMessage) {
      toMessageForType.put(messageType, toMessage);
   }

   public boolean getToMessageForType(Message.Type messageType) {
      return toMessageForType.get(messageType);
   }

   @Override
   public String getName() {
      return name;
   }

   public List<String> getMessageLog() {
      return messageLog;
   }

   @Override
   public void message(Message... messages) {
      for (Message message : messages) {
         for (String str : message.getFormattedText()) {
            messageLog.add(str);
         }
      }
      onMessage(messages);
   }

   protected void onMessage(Message... messages) {

   }

}
