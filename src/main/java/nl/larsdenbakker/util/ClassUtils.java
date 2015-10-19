package nl.larsdenbakker.util;

import static com.google.common.base.Preconditions.checkNotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class ClassUtils {

   /**
    * Returns a new instance of the specified type with the specified
    * constructor parameters. This is identical to calling
    * getConstructorWithSuperTypes(Class<T>, Object...).newInstance(Object...);
    *
    * @param <T> The returned type.
    * @param toConstruct The class of the desired type.
    * @param constructorParameters The constructor parameters of the class.
    *
    * @return A new instance of the specified type.
    * @throws NoSuchMethodException
    * @throws InstantiationException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    */
   public static <T> T getNewInstance(Class<T> toConstruct, Object... constructorParameters) throws NoSuchMethodException, InstantiationException,
                                                                                                    IllegalAccessException, IllegalArgumentException,
                                                                                                    InvocationTargetException {
      Constructor<T> constructor = getConstructorWithSuperTypes(toConstruct, constructorParameters);
      return constructor.newInstance(constructorParameters);
   }

   /**
    * Returns a constructor, if found, of the specified type with the specified
    * constructor parameters.
    *
    * @param <T> The type that is to be constructed.
    * @param toConstruct The class of the desired type.
    * @param constructorParameters The constructor parameters of the class.
    *
    * @return The requested constructor, if found.
    * @throws NoSuchMethodException Thrown when the constructor could not be
    * found.
    */
   public static <T> Constructor<T> getConstructorWithSuperTypes(Class<T> toConstruct, Object[] constructorParameters) throws NoSuchMethodException {
      Class<?>[] constructorParameterTypes = new Class<?>[constructorParameters.length];
      for (int i = 0; i < constructorParameters.length; i++) {
         constructorParameterTypes[i] = constructorParameters[i].getClass();
      }
      return getConstructorWithSuperTypes(toConstruct, constructorParameterTypes);
   }

   /**
    * Returns a constructor, if found, of the specified type with the specified
    * constructor parameter types.
    *
    * @param <T> The type that is to be constructed.
    * @param toConstruct The class of the desired type.
    * @param constructorParameterTypes The constructor parameter types.
    *
    * @return The requested constructor, if found.
    * @throws NoSuchMethodException Thrown when the constructor could not be
    * found.
    */
   public static <T> Constructor<T> getConstructorWithSuperTypes(Class<T> toConstruct, Class<?>... constructorParameterTypes) throws NoSuchMethodException {
      checkNotNull(toConstruct);
      checkNotNull(constructorParameterTypes);
      if (constructorParameterTypes.length >= 1) {
         Constructor<T> constructor;
         try {
            constructor = toConstruct.getConstructor(constructorParameterTypes);
         } catch (NoSuchMethodException e) {
            constructor = getConstructorWithSuperTypes(toConstruct, 0, constructorParameterTypes);
         }
         if (constructor != null) {
            return constructor;
         } else {
            throw new NoSuchMethodException("Did not find any constructor with parameter types: " + TextUtils.getDescription(constructorParameterTypes));
         }
      } else {
         return toConstruct.getConstructor();
      }
   }

   private static <T> Constructor<T> getConstructorWithSuperTypes(Class<T> toConstruct, int index, Class<?>... constructorParameterTypes) {
      Constructor<T> constructor;
      Class<?> superType = constructorParameterTypes[index];
      while (superType != null) {
         constructor = getConstructor(toConstruct, index, superType, constructorParameterTypes);
         if (constructor != null) {
            return constructor;
         }
         //Try the interfaces of super class
         for (Class<?> interfaceType : superType.getInterfaces()) {
            constructor = getConstructor(toConstruct, index, interfaceType, constructorParameterTypes);
            if (constructor != null) {
               return constructor;
            }
         }
         superType = superType.getSuperclass();
      }

      return null;
   }

   private static <T> Constructor<T> getConstructor(Class<T> toConstruct, int index, Class<?> superType, Class<?>... constructorParameterTypes) {
      Class<?>[] tempConstructorParameterTypes = Arrays.copyOf(constructorParameterTypes, constructorParameterTypes.length);
      tempConstructorParameterTypes[index] = superType;
      index++;
      for (int i = index; i < constructorParameterTypes.length; i++) {
         return ClassUtils.getConstructorWithSuperTypes(toConstruct, i, tempConstructorParameterTypes);
      }
      try {
         return toConstruct.getConstructor(tempConstructorParameterTypes);
      } catch (NoSuchMethodException | SecurityException ex) {
         return null;
      }
   }

}
