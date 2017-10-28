import java.util.*;
import java.util.Collections;
/**
 *  ArrayListPhoneNumberContainer adds and selects a phone to/from an arrayList
 * @implements PhoneContainer interface
 * @author Robson 
 * @version 04/14/2017
 */
public class ArrayListPhoneContainer implements PhoneContainer
{
    private ArrayList<Long> phoneList;

    /**
     * Constructor for objects of class ArrayListPhoneNumberContainer
     */
    public ArrayListPhoneContainer()
    {
        phoneList= new ArrayList<Long>();
    }

    /**
     * A method to add a phoneNumber to a sorted list
     * @param    Long phoneNumber
     * @return boolean true if successful
     */
    public boolean add(long phoneNumber)
    {
        int i=Collections.binarySearch(phoneList,phoneNumber);
        if(i>0) phoneList.add(phoneNumber);
        else if(i<0)    phoneList.add(-i-1,phoneNumber);
        else               return false;
        return true;
    }
    
    /**
     * Find the kth smallest item in the list
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the list
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the list
     */
    public  long select(int k)
    {
        if(phoneList.size()>0 &&(k<=phoneList.size()&&k>0))return phoneList.get(k-1);
        else                                               return -1;
    }

}
