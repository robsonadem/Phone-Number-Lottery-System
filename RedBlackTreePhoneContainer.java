
/**
 *  RedBlackTreePhoneContainer adds and selects a phone to/from a RedBlackTree data structure
 * @implements PhoneContainer interface
 * @author Robson 
 * @version 04/14/2017
 */
public class RedBlackTreePhoneContainer implements PhoneContainer
{
    private RedBlackTree<Long> phoneList;

    /**
     * Constructor for objects of class RedBlackTreePhoneNumberContainer
     */
    public RedBlackTreePhoneContainer()
    {
        phoneList= new RedBlackTree<Long>();
    }

    /**
     * A method to add a phoneNumber to a sorted list
     * @param    Long phoneNumber
     * @return boolean true if successful
     */
    public boolean add(long phoneNumber)
    {
        try{
            phoneList.insert(phoneNumber);
            return true;//successful
        }
        catch(Exception e){
          //duplicate items through excpetions
          return false;
        }
    }
    
   /**
     * Find the kth smallest item in the tree.
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the tree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    public long select(int k)
    {
        return phoneList.select(k);
    }
}
