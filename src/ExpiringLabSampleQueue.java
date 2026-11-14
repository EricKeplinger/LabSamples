import java.time.LocalDateTime;

/**
 * A laboratory sample queue that automatically discards
 * expired samples when retrieving elements.
 *
 * <p>This class extends {@link ExpandableLabSampleQueue}
 * and inherits its dynamically growing capacity.</p>
 *
 * <p>When retrieving a sample, expired samples are removed
 * and discarded until a valid sample is found or the
 * queue becomes empty.</p>
 */
public class ExpiringLabSampleQueue extends ExpandableLabSampleQueue{

    /**
     * Creates a new expiring laboratory sample queue
     * with the specified initial capacity.
     *
     * @param size the initial capacity of the queue
     */
    public ExpiringLabSampleQueue(int size) {
        super(size);
    }

    /**
     * Removes and returns the first non-expired sample.
     *
     * <p>Samples are considered expired when their
     * collection time plus their maximum stability
     * duration is not after the current time.</p>
     *
     * <p>Expired samples are permanently removed from
     * the queue. The process continues until a valid
     * sample is found or the queue is empty.</p>
     *
     * @return the first non-expired sample in FIFO order,
     *         or null if no valid sample is available
     */
    @Override
    public LabSample get() {
        LabSample sample = super.get();

        while(sample != null){
            LocalDateTime expirationTime = sample.getCollectedAt().plus(sample.getMaxStability());

            if(expirationTime.isAfter(LocalDateTime.now())){
                return sample;
            }

            sample = super.get();
        }
        return null;
    }
}
