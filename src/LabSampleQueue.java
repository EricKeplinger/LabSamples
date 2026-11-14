
/**
 * A simple fixed-size queue implementation for {@link LabSample} objects.
 * <p>
 * The queue is implemented as a circular buffer using an array.
 * Elements are inserted at the tail and removed from the head (FIFO principle).
 * </p>
 */
public class LabSampleQueue {

    /**
     * Array used as circular buffer for storing the samples.
     */
    protected LabSample[] data;

    /**
     * Current number of elements stored in the queue.
     */
    protected int n;

    /**
     * Index of the first element (head of the queue).
     */
    protected int head;

    /**
     * Index where the next element will be inserted (tail of the queue).
     */
    protected int tail;

    /**
     * Creates a new queue with the specified maximum capacity.
     *
     * @param size the maximum number of {@link LabSample} objects the queue can store
     */
    public LabSampleQueue(int size) {
        data = new LabSample[size];
        head = 0;
        tail = 0;
        n = 0;
    }

    /**
     * Inserts a {@link LabSample} at the end of the queue.
     *
     * @param s the sample to insert
     * @return {@code true} if the sample was successfully added,
     * {@code false} if the sample is {@code null} or the queue is already full
     */
    public boolean put(LabSample s) {
        if (s == null || n == data.length) {
            return false;
        } else {
            data[tail] = s;
            n++;
            tail = (tail + 1) % data.length;
            return true;
        }
    }

    /**
     * Removes and returns the next {@link LabSample} from the queue.
     *
     * @return the next sample in FIFO order, or {@code null} if the queue is empty
     */
    public LabSample get() {
        if (n == 0) {
            return null;
        } else {
            LabSample p = data[head];
            data[head] = null; // avoids memory leaks
            n--;
            head = (head + 1) % data.length;
            return p;
        }
    }
}



