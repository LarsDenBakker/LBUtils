package nl.larsdenbakker.util;

import com.google.common.base.CharMatcher;
import static com.google.common.base.CharMatcher.inRange;
import static com.google.common.base.Preconditions.checkNotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class TextUtils {

   /**
    * @param argument The argument.
    *
    * @return @param argument with all characters lowercased, and the first
    * character uppercased.
    */
   public static String capitalizeFirst(String argument) {
      checkNotNull(argument);
      if (!argument.isEmpty()) {
         return Character.toUpperCase(argument.charAt(0)) + argument.substring(1);
      } else {
         return argument;
      }
   }

   /**
    *
    * @param arguments Array of Strings whose first character will all be
    *                  capitalized.
    */
   public static void capitalizeAllWords(String[] arguments) {
      for (int i = 0; i < arguments.length; i++) {
         arguments[i] = capitalizeFirst(arguments[i]);
      }
   }

   /**
    *
    * @param argument String who words, separated by spaces, will all have their
    *                 first character capitalized.
    *
    * @return The capitalized String.
    */
   public static String capitalizeAllWords(String argument) {
      String[] splitArgument = splitOnSpaces(argument);
      capitalizeAllWords(splitArgument);
      return TextUtils.concatenateWith(splitArgument, " ");
   }

   /**
    *
    * @param input The string to check.
    *
    * @return Whether the provided string contains only ASCII characters.
    */
   public static boolean isAllASCII(String input) {
      boolean ret = CharMatcher.ASCII.matchesAllOf(input);
      return ret;
   }

   /**
    *
    * @param input The string to check.
    *
    * @return Whether the provided string contains only alphabetical characters.
    */
   public static boolean isAllAlphabetical(String input) {
      return CharMatcher.inRange('a', 'z').or(inRange('A', 'Z')).matchesAllOf(input);

   }

   /**
    *
    * @param coll       The elements to concatenate.
    * @param seperator  The string to seperate elements with.
    *
    * @return A string containing all provided elements, separated with the
    * @param separator.
    */
   public static String concatenateWith(Collection coll, String seperator) {
      StringBuilder sb = new StringBuilder();
      for (Object obj : coll) {
         sb.append(getDescription(obj)).append(seperator);
      }
      sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
   }

   /**
    *
    * @param array      The array to concatenate.
    * @param seperator  The string to seperate elements with.
    *
    * @return A string containing all provided elements, separated with the
    * @param separator.
    */
   public static String concatenateWith(Object[] array, String seperator) {
      StringBuilder sb = new StringBuilder();
      for (Object obj : array) {
         sb.append(getDescription(obj)).append(seperator);
      }
      sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
   }

   /**
    * Identical to concatenateWith(Collection, ", ").
    */
   public static String concatenate(Collection coll) {
      return concatenateWith(coll, ", ");
   }

   /**
    * Splits a String into an array of Strings where words are separated by one
    * or more spaces, unless they are contained within double quotes.
    *
    * @param str The String to split.
    *
    * @return The String split into an array of Strings.
    */
   public static String[] splitOnSpacesExceptInQuotes(String str) {
      String[] split = str.replaceAll("\\s+", " ").trim().split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      for (int i = 0; i < split.length; i++) {
         split[i] = split[i].replaceAll("\"", "");
      }
      return split;
   }

   /**
    * Splits a String into an array of Strings where words are separated by one
    * or more spaces.
    *
    * @param str The String to split.
    *
    * @return The String split into an array of Strings.
    */
   public static String[] splitOnSpaces(String str) {
      return str.replaceAll("\\s+", " ").trim().split(" ");
   }

   /**
    * Splits a String into an array of Strings where words are separated by one
    * or more spaces or commas.
    *
    * @param str The String to split.
    *
    * @return The String split into an array of Strings.
    */
   public static String[] splitOnSpacesAndCommas(String str) {
      return splitOnSpaces(str.replaceAll(",", " "));
   }

   /**
    * Splits a String into an array of Strings where words are separated by
    * periods.
    *
    * @param str The String to split.
    *
    * @return The String split into an array of Strings.
    */
   public static String[] splitOnPeriods(String str) {
      return str.split("\\.");
   }

   /**
    * Get a String with random letters of specified length.
    *
    * @param length The length of the String.
    *
    * @return The String.
    */
   public static String getRandomString(int length) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < length; i++) {
         sb.append((char) NumberUtils.randInt(33, 126));
      }
      return sb.toString();
   }

   /**
    * Describe this object's type. Collections, Maps and Array elements are
    * recursed and concatenated. Classes that implement Describable are queried
    * for their description, otherwise obj.getClass().getSimpleName() is
    * returned.
    *
    * @param obj         The object.
    * @param printNullAs What a null value is to be described as.
    *
    * @return The type description.
    */
   public static String getTypeDescription(Object obj, String printNullAs) {
      if (obj == null) {
         return printNullAs;
      } else if (obj instanceof Collection) {
         return TextUtils.getTypeDescription((Collection) obj, printNullAs);
      } else if (obj instanceof Map) {
         return getTypeDescription((Map) obj, printNullAs);
      } else if (obj.getClass().isArray()) {
         return getTypeDescription(((Object[]) obj));
      } else if (obj instanceof Describable) {
         return ((Describable) obj).getTypeDescription();
      } else {
         return obj.getClass().getSimpleName();
      }
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Object obj) {
      return TextUtils.getTypeDescription(obj, "null");
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Collection coll) {
      return TextUtils.getTypeDescription(coll, "null");
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Collection coll, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      int i = 0;
      int max = coll.size() - 1;
      sb.append('[');
      for (Object obj : coll) {
         sb.append(TextUtils.getTypeDescription(obj, printNullAs));
         if (i != max) {
            sb.append(", ");
         }
         i++;
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Object[] array) {
      return TextUtils.getTypeDescription(array, "null");
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Object[] array, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('[');
      for (int i = 0; i < array.length; i++) {
         sb.append(TextUtils.getTypeDescription(array[i], printNullAs));
         if (i != array.length - 1) {
            sb.append(", ");
         }
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Map<Object, Object> map) {
      return getTypeDescription(map, "null");
   }

   /**
    * See getTypeDescription(Object, String).
    */
   public static String getTypeDescription(Map<Object, Object> map, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('{');
      int i = 0;
      int max = map.size() - 1;
      for (Entry<Object, Object> entry : map.entrySet()) {
         sb.append(TextUtils.getTypeDescription(entry.getKey(), printNullAs));
         sb.append(": ");
         sb.append(TextUtils.getTypeDescription(entry.getValue(), printNullAs));
         if (i != max) {
            sb.append(", ");
         }
      }
      sb.append('}');
      return sb.toString();
   }

   /**
    * Describe this object. Collections, Maps and Array elements are recursed
    * and concatenated. Classes that implement Describable are queried for their
    * description, otherwise obj.toString() is returned.
    *
    * @param obj         The object.
    * @param printNullAs What a null value is to be described as.
    *
    * @return The description.
    */
   public static String getDescription(Object obj, String printNullAs) {
      if (obj == null) {
         return printNullAs;
      } else if (obj instanceof Collection) {
         return getDescription((Collection) obj, printNullAs);
      } else if (obj instanceof Map) {
         return getDescription((Map) obj, printNullAs);
      } else if (obj.getClass().isArray()) {
         return getDescription(((Object[]) obj));
      } else if (obj instanceof Describable) {
         return ((Describable) obj).getDescription();
      } else if (obj instanceof Class) {
         return ((Class) obj).getSimpleName();
      } else if (obj instanceof LocalDate) {
         return ((LocalDate) obj).format(TimeUtils.DEFAULT_FORMAT);
      } else {
         return obj.toString();
      }
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Object obj) {
      return getDescription(obj, "null");
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Collection coll) {
      return getDescription(coll, "null");
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Collection coll, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      int i = 0;
      int max = coll.size() - 1;
      sb.append('[');
      for (Object obj : coll) {
         sb.append(TextUtils.getDescription(obj, printNullAs));
         if (i != max) {
            sb.append(", ");
         }
         i++;
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Object[] array) {
      return getDescription(array, "null");
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Object[] array, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('[');
      for (int i = 0; i < array.length; i++) {
         sb.append(TextUtils.getDescription(array[i], printNullAs));
         if (i != array.length - 1) {
            sb.append(", ");
         }
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Map<Object, Object> map) {
      return getDescription(map, "null");
   }

   /**
    * See getDescription(Object, String).
    */
   public static String getDescription(Map<Object, Object> map, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('{');
      int i = 0;
      int max = map.size() - 1;
      for (Entry<Object, Object> entry : map.entrySet()) {
         sb.append(getDescription(entry.getKey(), printNullAs));
         sb.append(": ");
         sb.append(getDescription(entry.getValue(), printNullAs));
         if (i != max) {
            sb.append(", ");
         }
      }
      sb.append('}');
      return sb.toString();
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Object obj) {
      return TextUtils.getTypeAndValueDescription(obj, "null");
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Object obj, String printNullAs) {
      if (obj instanceof Collection) {
         return TextUtils.getTypeAndValueDescription((Collection) obj, printNullAs);
      } else if (obj instanceof Map) {
         return getTypeAndValueDescription((Map) obj, printNullAs);
      } else if (obj == null) {
         return printNullAs;
      } else {
         return "(" + TextUtils.getTypeDescription(obj) + ") " + getDescription(obj);
      }
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Collection coll) {
      return TextUtils.getTypeAndValueDescription(coll, "null");
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Collection coll, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      int i = 0;
      int max = coll.size() - 1;
      sb.append('[');
      for (Object obj : coll) {
         sb.append(TextUtils.getTypeAndValueDescription(obj, printNullAs));
         if (i != max) {
            sb.append(", ");
         }
         i++;
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Object[] array) {
      return TextUtils.getTypeAndValueDescription(array, "null");
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Object[] array, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('[');
      for (int i = 0; i < array.length; i++) {
         sb.append(TextUtils.getTypeAndValueDescription(array[i], printNullAs));
         if (i != array.length - 1) {
            sb.append(", ");
         }
      }
      sb.append(']');
      return sb.toString();
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Map<Object, Object> map) {
      return getTypeAndValueDescription(map, "null");
   }

   /**
    * Get a description of both of object's type and value. See
    * getTypeDescription(Object, String) and getDescription(Object, String) for
    * more.
    *
    * @return The description.
    */
   public static String getTypeAndValueDescription(Map<Object, Object> map, String printNullAs) {
      StringBuilder sb = new StringBuilder();
      sb.append('{');
      int i = 0;
      int max = map.size() - 1;
      for (Entry<Object, Object> entry : map.entrySet()) {
         sb.append(TextUtils.getTypeAndValueDescription(entry.getKey(), printNullAs));
         sb.append(": ");
         sb.append(TextUtils.getTypeAndValueDescription(entry.getValue(), printNullAs));
         if (i != max) {
            sb.append(", ");
         }
      }
      sb.append('}');
      return sb.toString();
   }

}
