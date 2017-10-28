import java.util.*;
/**
 *  HashSetPhoneNumberContainer adds and selects a phone to/from a HashSet
 * @implements PhoneContainer interface
 * @author Robson 
 * @version 04/14/2017
 */
public class HashSetPhoneContainer implements PhoneContainer
{
    private HashSet<Long> phoneList;

    /**
     * Constructor for objects of class HashListPhoneNumberContainer
     */
    public HashSetPhoneContainer()
    {
        phoneList= new HashSet<Long>();
    }

    /**
     * A method to add a phoneNumber to a sorted list
     * @param    Long phoneNumber
     * @return boolean true if successful
     */
    public boolean add(long phoneNumber)
    {
        return phoneList.add(phoneNumber);
    }

   /**
     * Find the kth smallest item in the set
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the set
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the set
     */
    public  long select(int k)
    {
        if(k>phoneList.size()||k<=0)
            return -1;//violates our requirement
        Long[] hashToArray= phoneList.toArray(new Long [phoneList.size()]);
        quickSelect(hashToArray, k);
        return hashToArray[k-1];
    }
    
    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Long items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */     
    public void quickSelect( Long [ ] a, int k )
    {
        quickSelect( a, 0, a.length - 1, k );
    }
    
    /**
     * Internal selection method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Long items.
     * @param low the left-most index of the subarray.
     * @param high the right-most index of the subarray.
     * @param k the desired rank (1 is minimum) in the entire array.
     */
    private void quickSelect( Long [ ] a, int low, int high, int k )
    {
        if( low + 10 > high )
            insertionSort( a, low, high );
        else
        {
                // Sort low, middle, high
            int middle = ( low + high ) / 2;
            if( a[ middle ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, middle );
            if( a[ high ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, high );
            if( a[ high ].compareTo( a[ middle ] ) < 0 )
                swapReferences( a, middle, high );

                // Place pivot at position high - 1
            swapReferences( a, middle, high - 1 );
            Long pivot = a[ high - 1 ];

                // Begin partitioning
            int i, j;
            for( i = low, j = high - 1; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 )
                    ;
                while( pivot.compareTo( a[ --j ] ) < 0 )
                    ;
                if( i >= j )
                    break;
                swapReferences( a, i, j );
            }

                // Restore pivot
            swapReferences( a, i, high - 1 );

                // Recurse; only this part changes
            if( k <= i )
                quickSelect( a, low, i - 1, k );
            else if( k > i + 1 )
                quickSelect( a, i + 1, high, k );
        }
    }
    
    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     * @param a an array of Long items.
     * @param low the left-most index of the subarray.
     * @param n the number of items to sort.
     */
    private static void insertionSort( Long [ ] a, int low, int high )
    {
        for( int p = low + 1; p <= high; p++ )
        {
            Long tmp = a[ p ];
            int j;

            for( j = p; j > low && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }
    
    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    private void swapReferences( Object [ ] a, int index1, int index2 )
    {
        Object tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

}
