package nl.larsdenbakker.util;

import java.lang.ref.Reference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A List implementation that automatically deals with boxing and unboxing References.
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public abstract class ReferenceList<L extends Reference<V>, V> extends AbstractList<V> {

   protected final ArrayList<L> items;

   /**
    * Create a new empty ReferenceList
    */
   public ReferenceList() {
      items = new ArrayList();
   }

   /**
    * Creating a new ReferenceList with specified elements.
    *
    * @param c The elements to add.
    */
   public ReferenceList(Collection c) {
      items = new ArrayList();
      addAll(0, c);
   }

   @Override
   public Iterator<V> iterator() {
      return new ReferenceListIterator();
   }

   @Override
   public int size() {
      removeReleased();
      return items.size();
   }

   @Override
   public V get(int index) {
      return items.get(index).get();
   }

   private void removeReleased() {
      for (Iterator<L> it = items.iterator(); it.hasNext();) {
         L ref = it.next();
         if (ref == null || ref.get() == null) {
            it.remove();
         }
      }
   }

   @Override
   public String toString() {
      return items.toString();
   }

   @Override
   public int hashCode() {
      return items.hashCode();
   }

   @Override
   public boolean equals(Object o) {
      return items.equals(o);
   }

   private class ReferenceListIterator implements Iterator<V> {

      private int n;
      private int i;

      public ReferenceListIterator() {
         n = size();
         i = 0;
      }

      @Override
      public boolean hasNext() {
         return i < n;
      }

      @Override
      public V next() {
         return get(i++);
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }

   }

}
