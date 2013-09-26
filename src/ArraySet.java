import java.util.Iterator;
import java.util.NoSuchElementException;

/*********************************************************************************
 * Written by: Simon Cicek                                                       *
 * Last changed: 2012-04-26                                                      *
 *                                                                               *
 * The class implementing a set, using an array as the underlying datastructure. *
 *********************************************************************************/

public class ArraySet<E> implements Set<E>
{
    // The default capacity of the set
    public static final int DEFAULT_CAPACITY = 100;

    // The elements of the set
    private E[] elements;

    // The index of the last element in the set
    private int lastIndex = -1;

    public ArraySet()
    {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArraySet(int initialCapacity)
    {
        elements = (E[]) new Object[initialCapacity];
    }

    public ArraySet(Set<E> set)
    {
        elements = (E[]) new Object[set.size()];
        int index = 0;
        for (E element : set)
            elements[index++] = element;

        lastIndex = set.size () - 1;
    }
    
    public boolean isEmpty()
    {
        return lastIndex == -1;
    }
    
    public int size()
    {
        return lastIndex + 1;
    }

    // Enlargens the array in which the elements of the set are stored
    protected void enlarge()
    {
        // Create a temporary array
        E[] n = (E[]) new Object[elements.length * 2];
        
        // Copy the elements from the array and add them to the new array
        System.arraycopy(elements, 0, n, 0, elements.length);
        elements = n;
    }
    
    // Returns the index of the given element, or -1 if it is not found
    protected int indexOf(E element)
    {
        int indexOfElement = -1;
        for (int index = 0; index <= lastIndex; index++)
        {
            if (element.equals (elements[index]))
            {
                indexOfElement = index;
                break;
            }
        }

        return indexOfElement;
    }

    // Checks if the given element exists in the set
    public boolean contains(E element)
    {
        int indexOfElement = this.indexOf (element);
        boolean elementIsInSet = indexOfElement != -1;

        return elementIsInSet;
    }

    // Adds the given element to the set unless it already exits in the set
    public void add(E element)
    {
        // Ensure there are no duplicates in the set
        if(this.contains(element))
            return;
        
        // Enlargen the array if it is full
        if (lastIndex == elements.length - 1)
            this.enlarge ();

        // Add the element
        lastIndex = lastIndex + 1;
        elements[lastIndex] = element;
    }

    // Removes the given element from the set, if it exists in the set
    public void remove(E element)
    {
        int indexOfElement = this.indexOf (element);
        
        // Check if the element exists in the set
        if(indexOfElement == -1)
            return;
        
        // Copy all the elements beyond the index of the given element one step back
        for (int index = indexOfElement + 1; index <= lastIndex; index++)
            elements[index - 1] = elements[index];
            
        elements[lastIndex] = null;
        lastIndex--;
    }

    // Clear the set of elements
    public void clear()
    {
        for (int index = 0; index <= lastIndex; index++)
            elements[index] = null;
        lastIndex = -1;
    }

    // Checks if this set is a subset of the given set 
    public boolean isSubsetOf(Set<E> set)
    {
        boolean isSubset = true;
        // Checks if all the elements in this set exists in the given set
        for (int index = 0; index <= lastIndex; index++)
            if (!set.contains (elements[index]))
            {
                isSubset = false;
                break;
            }

        return isSubset;
    }

    // Returns the union of this set and the given set
    public Set<E> union(Set<E> set)
    {
        Set<E> u = new ArraySet(set);
        
        for (int index = 0; index <= lastIndex; index++)
            u.add (elements[index]);

        return u;
    }

    // Returns the intersection of this set and the given set
    public Set<E> intersection(Set<E> set)
    {
        Set<E> i = new ArraySet();
        for (int index = 0; index <= lastIndex; index++)
            if (set.contains(elements[index]))
                i.add (elements[index]);

        return i;
    }

    // Returns the difference of this set and the given set
    public Set<E> difference(Set<E> set)
    {
        Set<E> d = new ArraySet();
        for (int index = 0; index <= lastIndex; index++)
            if (!set.contains(elements[index]))
                d.add (elements[index]);

        return d;
    }

    // Override the toString method in order to print the set in a pretty way
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        Iterator itr = this.iterator();
        s.append("{");
        while(itr.hasNext())
        {
            s.append(itr.next());
            if(itr.hasNext())
                s.append(", ");
        }
        s.append("}");
        return s.toString();
    }
    
    // A representation of an iterator 
    private class SetIterator implements Iterator<E>
    {
        // The index of the next element
        protected int nextIndex;
        
        // The index of the element that was last deleted
        protected int lastReturnedIndex;
        
        public SetIterator()
        {
            nextIndex = 0;
            lastReturnedIndex = -1;
        }
        
        // Checks if there are anymore elements in the set
        public boolean hasNext()
        {
            return nextIndex <= lastIndex;
        }
        
        // Returns the next element in the set, if there is another element
        public E next() throws NoSuchElementException
        {
            if (!this.hasNext ())
                throw new NoSuchElementException ("No more elements left");

            E element = elements[nextIndex];
            lastReturnedIndex = nextIndex;
            nextIndex++;

            return element;
        }

        // Removes the element that was last returned
        public void remove() throws IllegalStateException
        {
            if (lastReturnedIndex == -1)
                throw new IllegalStateException ("No element to remove");

            for (int index = lastReturnedIndex + 1; index <= lastIndex; index++)
                elements[index - 1] = elements[index];
            elements[lastIndex] = null;
            lastIndex--;

            // Since we removed an element, we need to move back the index of the next element
            nextIndex--;

            // Since we removed an element, we can not remove another element until
            // a new element has been returned
            lastReturnedIndex = -1;
        }
    }

    // Returns an iterator
    public Iterator<E> iterator()
    {
        return this.new SetIterator ();
    }
}
