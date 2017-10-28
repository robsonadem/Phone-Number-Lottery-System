
/**
 *  An interface that defines two methods(add and select) from a sorted list
 * 
 * @author Robson 
 * @version 04/14/2017
 */
public interface PhoneContainer
{
    /**
     * A method to add a phoneNumber to a sorted list
     * @param    Long phoneNumber
     * @return boolean true if successful
     */
    public boolean add(long phoneNumber);

    /**
     * Find the kth smallest item in the sorted list
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the sorted list
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the sorted list
     */
    public long select(int k);
}
