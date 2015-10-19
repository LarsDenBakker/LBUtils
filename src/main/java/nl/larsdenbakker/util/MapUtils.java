package nl.larsdenbakker.util;

import static com.google.common.base.Preconditions.checkNotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class MapUtils {

   /**
    * Create a new instance of the provided map type. The map type must support
    * an empty constructor.
    *
    * @param <K> The key type.
    * @param <V> The value type.
    * @param <M> The map.
    * @param clazz The map type.
    * @return A new instance.
    */
   public static <K, V, M extends Map<K, V>> M of(Class<M> clazz) {
      return _of(clazz, null, null);
   }

   /**
    * Create a new instance of HashMap with the provided key-value pairs.
    * Key-value pairs are provided in the order of key1, val1, key2, val2, key3,
    * val3 etc.
    *
    * @param <K> The key type.
    * @param <V> The value type.
    * @param key... The keys.
    * @param value... The values.
    * @return A new instance with the provided key-value pairs
    */
   public static <K, V> Map<K, V> of(K key1, V val1) {
      return _of(HashMap.class, CollectionUtils.asArray(key1), CollectionUtils.asArray(val1));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2), CollectionUtils.asArray(val1, val2));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3), CollectionUtils.asArray(val1, val2, val3));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4), CollectionUtils.asArray(val1, val2, val3, val4));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5), CollectionUtils.asArray(val1, val2, val3, val4, val5));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8, val9));
   }

   /* See of(K,V) */
   public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9, K key10, V val10) {
      return _of(HashMap.class, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9, key10), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8, val9, val10));
   }

   /**
    * Create a new instance of the provided map type with the provided key-value
    * pairs. Map type must support empty an empty constructor. Key-value pairs
    * are provided in the order of key1, val1, key2, val2, key3, val3 etc.
    *
    * @param <K> The key type.
    * @param <V> The value type.
    * @param mapType The map type.
    * @param key... The keys.
    * @param value... The values.
    * @return A new instance with the provided key-value pairs
    */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1) {
      return _of(mapType, CollectionUtils.asArray(key1), CollectionUtils.asArray(val1));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2) {
      return _of(mapType, CollectionUtils.asArray(key1, key2), CollectionUtils.asArray(val1, val2));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3), CollectionUtils.asArray(val1, val2, val3));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4), CollectionUtils.asArray(val1, val2, val3, val4));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5), CollectionUtils.asArray(val1, val2, val3, val4, val5));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8, val9));
   }

   /* See of(Class<M>,K,V) */
   public static <K, V, M extends Map<K, V>> M of(Class<M> mapType, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9, K key10, V val10) {
      return _of(mapType, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9, key10), CollectionUtils.asArray(val1, val2, val3, val4, val5, val6, val7, val8, val9, val10));
   }

   private static <K, V, M extends Map<K, V>> M _of(Class<M> clazz, K[] keys, V... values) {
      Class<? extends M> constructionClass = (clazz.equals(Map.class)) ? (Class) HashMap.class : clazz;
      if (Map.class.isAssignableFrom(constructionClass)) {
         if (!constructionClass.isInterface()) {
            try {
               Constructor<? extends M> constr = constructionClass.getConstructor();
               M m = constr.newInstance();
               if (keys != null && values != null) {
                  for (int i = 0; i < keys.length; i++) {
                     m.put(keys[i], values[i]); //Internal use garauntees values[i] exists
                  }
               }
               return m;
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
               throw new IllegalArgumentException("Map class " + clazz + " does not support an empty constructor.");
            }
         } else {
            throw new IllegalArgumentException("Must not be an interface.");
         }
      } else {
         throw new IllegalArgumentException("Must be a subclass of Map.");
      }
   }

   /**
    * A shorthand method to put the provided key-value pairs in the provided map
    * and return the same map instance. Key-value pairs are provided in the
    * order of key1, val1, key2, val2, key3, val3 etc.
    *
    * @param <K> The key type.
    * @param <V> The value type.
    * @param map The map.
    * @param key... The keys.
    * @param val... The values.
    * @return The same map instance as @param map.
    */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1) {
      return put(map, CollectionUtils.asArray(key1), val1);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2) {
      return put(map, CollectionUtils.asArray(key1, key2), val1, val2);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3) {
      return put(map, CollectionUtils.asArray(key1, key2, key3), val1, val2, val3);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4), val1, val2, val3, val4);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5), val1, val2, val3, val4, val5);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6), val1, val2, val3, val4, val5, val6);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7), val1, val2, val3, val4, val5, val6, val7);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8), val1, val2, val3, val4, val5, val6, val7, val8);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9), val1, val2, val3, val4, val5, val6, val7, val8, val9);
   }

   /* See put(Map<K,V>, K, V) */
   public static <K, V> Map<K, V> put(Map<K, V> map, K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4, K key5, V val5, K key6, V val6, K key7, V val7, K key8, V val8, K key9, V val9, K key10, V val10) {
      return put(map, CollectionUtils.asArray(key1, key2, key3, key4, key5, key6, key7, key8, key9, key10), val1, val2, val3, val4, val5, val6, val7, val8, val9, val10);
   }

   private static <K, V> Map<K, V> put(Map<K, V> map, K[] keys, V... values) {
      checkNotNull(map);
      if (keys.length != values.length) {
         throw new IllegalArgumentException("Key and value amount are not the same. (" + keys.length + " and " + values.length + ")");
      }
      for (int i = 0; i < keys.length; i++) {
         map.put(keys[i], values[i]);
      }
      return map;
   }

}
