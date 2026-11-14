/**
 * A dynamically growing queue for laboratory samples.
 *
 * <p>This class extends {@link LabSampleQueue} and
 * automatically doubles the capacity of the underlying
 * array when the queue is full.</p>
 *
 * <p>The FIFO order of the stored samples is preserved
 * during resizing.</p>
 */
public class ExpandableLabSampleQueue extends LabSampleQueue{
    /**
     * Creates a new expandable laboratory sample queue
     * with the specified initial capacity.
     *
     * @param size the initial capacity of the queue
     */
    public ExpandableLabSampleQueue(int size){
        super(size);
    }

    /**
     * Inserts a laboratory sample into the queue.
     *
     * <p>If the queue is full, its capacity is doubled
     * before the sample is inserted using the
     * superclass implementation.</p>
     *
     * @param s the laboratory sample to insert
     * @return true if the sample was successfully added,
     *         false if the sample is null
     */
    @Override
    public boolean put(LabSample s){
        if(n == data.length){
            resizeArray();
        }
        return super.put(s);
    }

    /**
     * Doubles the capacity of the underlying array.
     *
     * <p>Existing samples are copied into the new array
     * in FIFO order. The head index is reset to zero
     * and the tail index is updated to the position
     * following the last stored sample.</p>
     *
     * <p>The number of stored samples remains unchanged.</p>
     */
    protected void resizeArray(){
        LabSample[] newData = new LabSample[data.length * 2];
        for (int i = 0; i < n; i++){
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = n;
    }

}
