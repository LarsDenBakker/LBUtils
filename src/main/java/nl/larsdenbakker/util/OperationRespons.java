package nl.larsdenbakker.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Lars den Bakker<larsdenbakker@gmail.com>
 */
public class OperationRespons {

   private boolean succeeded;
   private List<String> messages;

   public static OperationRespons of(boolean success) {
      if (success) {
         return succeeded();
      } else {
         return failed();
      }
   }

   public static OperationRespons succeeded(String... messages) {
      return new OperationRespons().setSucceeded(true);
   }

   public static OperationRespons failed(String... messages) {
      return new OperationRespons().setSucceeded(false);
   }

   public boolean hasSucceeded() {
      return succeeded;
   }

   public OperationRespons setSucceeded(boolean succeeded) {
      this.succeeded = succeeded;
      return this;
   }

   public String getMessage() {
      return TextUtils.concatenateWith(messages, " ");
   }

   public List<String> getMessages() {
      if (messages == null) {
         List<String> list = new ArrayList();
         list.add((hasSucceeded()) ? "Operation has been successful." : "Operation has failed.");
         return list;
      } else {
         return messages;
      }
   }

   public OperationRespons addMessages(String... messages) {
      verifyMessagesList();
      for (String message : messages) {
         this.messages.add(message);
      }
      return this;
   }

   public OperationRespons addMessages(Collection<String> messages) {
      verifyMessagesList();
      messages.addAll(messages);
      return this;
   }

   private void verifyMessagesList() {
      if (messages == null) {
         messages = new ArrayList();
      }
   }

}
