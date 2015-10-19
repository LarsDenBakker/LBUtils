package nl.larsdenbakker.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class Message {

   public enum Type {

      RAW,
      INFO,
      WARNING,
      ERROR,
      DEBUG;

      public String getPrefix() {
         if (this.equals(RAW)) {
            return "";
         } else {
            return '[' + this.toString() + ']';
         }
      }
   }

   private List<String> text;
   private final Type messageLevel;

   public Message(Type messageLevel, String... text) {
      this.messageLevel = messageLevel;
      add(text);
   }

   public List<String> getRawText() {
      return (text != null) ? text : new ArrayList();
   }

   public List<String> getFormattedText() {
      List<String> formattedText = new ArrayList();
      for (String str : getRawText()) {
         formattedText.add(messageLevel.getPrefix() + str);
      }
      return formattedText;
   }

   public Type getMessageLevel() {
      return messageLevel;
   }

   public final Message add(String... text) {
      verifyTextList(text.length);
      add(Arrays.<String>asList(text));
      return this;
   }

   public final Message add(Collection<String> text) {
      verifyTextList(text.size());
      this.text.addAll(text);
      return this;
   }

   private void verifyTextList() {
      verifyTextList(-1);
   }

   private void verifyTextList(int suggestedSize) {
      if (text == null) {
         text = (suggestedSize != -1) ? new ArrayList(suggestedSize) : new ArrayList();
      }
   }

}
