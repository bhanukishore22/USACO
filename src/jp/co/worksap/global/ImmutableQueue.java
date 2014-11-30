package jp.co.worksap.global;

import java.util.NoSuchElementException;

/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of objects.
 * 
 * @param <E>
 */
public class ImmutableQueue<E extends Object> {

    /**
     * Front Element represents the Head Portion
     */
    private final E front;
    /**
     * Tail Element represents the Tail Portion
     */
    private final E tail;
    /**
     * Recursive reference to represent the rest of the Queue except front and tail.
     */
    private final ImmutableQueue<E> inside;
    /**
     * Size of the Queue.
     */
    private int size;

    /**
     * Default Constructor.
     */
    public ImmutableQueue() {
        this.front = null;
        this.tail = null;
        this.inside = null;
        this.size = 0;
    }

    /**
     * Private Constructor.
     * 
     * @param front head element
     * @param inside middle immutable queue
     * @param tail tail element
     */
    private ImmutableQueue(final E front, final ImmutableQueue<E> inside, final E tail) {
        // initialize variables.
        this.front = front;
        this.inside = inside;
        this.tail = tail;

        // Recursively compute the size.
        if (this.front != null) {
            this.size++;
        }
        if (this.inside != null) {
            this.size += this.inside.size();
        }

        if (this.tail != null) {
            this.size++;
        }
    }

    /**
     * Returns the queue that adds an item into the tail of this queue without modifying this queue.
     * 
     * <pre>
     * e.g.
     * When this queue represents the queue (2, 1, 2, 2, 6) and we enqueue the value 4 into this queue,
     * this method returns a new queue (2, 1, 2, 2, 6, 4)
     * and this object still represents the queue (2, 1, 2, 2, 6) .
     * </pre>
     * 
     * If the element e is null, throws IllegalArgumentException.
     * 
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public ImmutableQueue<E> enqueue(final E e) {
        if (e == null) {
            throw new IllegalArgumentException("Parameter can not be null");
        }

        ImmutableQueue<E> newQueue = null;
        if (this.size == 0) {
            // Add the Element to the Head Portion
            newQueue = new ImmutableQueue<E>(e, null, null);
        } else if (this.size == 1) {
            // Add the Element to the Tail Portion
            newQueue = new ImmutableQueue<E>(this.front, null, e);
        } else if (this.size >= 2) {
            // Construct the Middle Portion Recursively and represent the Queue as Head, Middle,
            // Tail.
            ImmutableQueue<E> middle = null;
            if (this.inside == null) {
                middle = new ImmutableQueue<E>(this.tail, null, null);
            } else {
                middle = this.inside.enqueue(this.tail);
            }
            newQueue = new ImmutableQueue<E>(this.front, middle, e);
        }

        return newQueue;
    }

    /**
     * Returns the queue that removes the object at the head of this queue without modifying this
     * queue.
     * 
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1) ,
     * this method returns a new queue (1, 3, 3, 5, 1)
     * and this object still represents the queue (7, 1, 3, 3, 5, 1) .
     * </pre>
     * 
     * If this queue is empty, throws java.util.NoSuchElementException.
     * 
     * @return
     * @throws java.util.NoSuchElementException
     */
    public ImmutableQueue<E> dequeue() {

        ImmutableQueue<E> newQueue = null;
        if (this.size == 0) {
            // Throw Exception.
            throw new NoSuchElementException();
        } else if (this.size == 1) {
            // Make Empty Queue.
            newQueue = new ImmutableQueue<E>(null, null, null);
        } else if (this.size == 2) {
            // Make Queue with only 1 element
            newQueue = new ImmutableQueue<E>(this.tail, null, null);
        }
        else if (this.size > 2) {
            // For Size > 2, Middle Portion is represented as another Queue. Construct new Queue by
            // moving the First Element of Middle Portion.
            ImmutableQueue<E> middle = null;
            final E newFirst = this.inside.peek();
            middle = this.inside.dequeue();
            if (middle.size() == 0) {
                middle = null;
            }
            newQueue = new ImmutableQueue<E>(newFirst, middle, this.tail);
        }

        return newQueue;
    }

    /**
     * Looks at the object which is the head of this queue without removing it from the queue.
     * 
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1),
     * this method returns 7 and this object still represents the queue (7, 1, 3, 3, 5, 1)
     * </pre>
     * 
     * If the queue is empty, throws java.util.NoSuchElementException.
     * 
     * @return
     * @throws java.util.NoSuchElementException
     */
    public E peek() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is Empty");
        } else if (this.size >= 1) {
            return this.front;
        }

        return null;
    }

    /**
     * Returns the number of objects in this queue.
     * 
     * @return
     */
    public int size() {
        return this.size;
    }

    public String trace() {
        if (this.size == 0) {
            return "";
        }
        else if (this.size == 1) {
            return this.front.toString();
        }
        else if (this.size == 2) {
            return this.front.toString() + ", " + this.tail.toString();
        } else if (this.size > 2) {
            return this.front.toString() + ", " + this.inside.trace() + ", " + this.tail.toString();
        }

        return null;
    }
}
