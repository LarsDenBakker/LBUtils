package nl.larsdenbakker.util;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class CollectionUtils {

   /**
    * Get a random element from a Collection.
    *
    * @param <T>  The collection type.
    * @param coll A non-empty Collection.
    *
    * @return A random element from the Collection.
    */
   public static <T> T getRandom(Collection<T> coll) {
      Preconditions.checkArgument(!coll.isEmpty(), "Collection cannot be empty.");
      int size = coll.size();
      T t = null;
      int i = 0;
      int end = NumberUtils.randInt(0, size);
      for (T e : coll) {
         t = e;
         i++;
         if (i == end) {
            break;
         }
      }
      return t;
   }

   /**
    * Turns a Collection into an array with elements in the same order as they
    * appear during a regular for-loop.
    *
    * @param <T>  The Collection and array's element type.
    * @param type The Collection and array's element type class.
    * @param coll The Collection.
    *
    * @return An array containing all elements of the Collection.
    */
   public static <T> T[] asArrayOfType(Class<T> type, Collection<T> coll) {
      T[] array = (T[]) Array.newInstance(type, coll.size());
      int i = 0;
      for (T t : coll) {
         array[i] = t;
         i++;
      }
      return array;
   }

   /**
    * A shorthand method to turn vararg parameters into an array.
    *
    * @param <T>   The array element type.
    * @param items Vararg parameters of type T.
    *
    * @return An array of type T with @items varargs as elements.
    */
   public static <T> T[] asArray(T... items) {
      return items;
   }

   /**
    *
    * @param <T>   The array element type.
    * @param left  Array to be merged.
    * @param right Varargs to be merged with array.
    *
    * @return A new array with first all the elements of @left, then all the vararg elements of @right.
    */
   public static <T> T[] merge(T[] left, T... right) {
      int newLength = left.length + right.length;
      T[] newArray = Arrays.copyOf(left, newLength);
      for (int i = left.length; i < newLength; i++) {
         newArray[i] = right[i - left.length];
      }
      return newArray;
   }

   /**
    * Construct a new instance of the specified collection, with optionally specified elements.
    * The Collection type should support an empty constructor.
    *
    * @param <E>      The element type of the Collection.
    * @param <C>      The collection type.
    * @param clazz    The class of the collection to be constructed. It must support an empty constructor.
    * @param elements Optionally vararg elements to add to the new constructed Collection.
    *
    * @return The constructed Collection with optionally the specified @elements as elements.
    */
   public static <E, C extends Collection<E>> C instanceOf(Class<C> clazz, E... elements) {
      if (Collection.class.isAssignableFrom(clazz)) {
         Class<? extends Collection> constructionClass;
         if (clazz.isInterface()) {
            if (clazz.equals(List.class)) {
               constructionClass = ArrayList.class;
            } else if (clazz.equals(Set.class)) {
               constructionClass = HashSet.class;
            } else {
               throw new IllegalArgumentException("Unknown Collection interface: " + clazz);
            }
         } else {
            constructionClass = clazz;
         }
         try {
            Constructor<?> constr = constructionClass.getConstructor();
            C c = (C) constr.newInstance(); //Safe cast garaunteed
            for (E e : elements) {
               c.add(e);
            }
            return c;
         } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalArgumentException("Could not create instance of Collection class: " + clazz, ex);
         }
      } else {
         throw new IllegalArgumentException("Must be a subclass of Collection.");
      }
   }

   /**
    * Shorthand method to turn vararg elements into an ArrayList.
    *
    * @param <E>      The element type.
    * @param elements The elements.
    *
    * @return A new ArrayList with specified @elements as elements.
    */
   public static <E> ArrayList<E> listOf(E... elements) {
      return instanceOf(ArrayList.class, elements);
   }

   /**
    * Get the first value, if any, mapped to the specified type or one of it's super classes.
    *
    * @param <V>  The returned value.
    * @param map  The map.
    * @param type The mapped type.
    *
    * @return The first value found, if any.
    */
   public static <V> V getMappedValueFromSuperType(Map<Class<?>, V> map, Class type) {
      while (type != null) {
         V val = map.get(type);
         if (val != null) {
            return val;
         } else {
            type = type.getSuperclass();
         }
      }
      return null;
   }

   /**
    * Get all mapped values to the specified type or one of it's super classes.
    *
    * @param <V>  The returned value.
    * @param map  The map.
    * @param type The mapped type.
    *
    * @return A list of the mapped values that were found.
    */
   public static <V> List<V> getMappedValuesFromSuperTypes(Map<Class<?>, V> map, Class type) {
      List<V> list = new ArrayList();
      while (type != null) {
         V val = map.get(type);
         if (val != null) {
            list.add(val);
         } else {
            type = type.getSuperclass();
         }
      }
      return list;
   }

}
