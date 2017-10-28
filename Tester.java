import java.util.*;
import java.util.concurrent.*;

/**
 * For testing of CS150 Project 2
 * @author Prof. Ge Xia
 */
public class Tester
{
    // Test program
    public static void main( String [ ] args )
    {
        int insertCount = Integer.parseInt(args[0]);
        int selectCount = Integer.parseInt(args[1]);
        
        System.out.println("Test RedBlackTreePhoneContainer...");
        test(new RedBlackTreePhoneContainer( ), insertCount, selectCount);

        System.out.println("Test ArrayListPhoneContainer...");
        test(new ArrayListPhoneContainer( ), insertCount, selectCount);

        System.out.println("Test HashSetPhoneContainer...");
        test(new HashSetPhoneContainer( ), insertCount, selectCount);
    }

    // test insertCount-many insertions and selectCount-many selections on a given PhoneBase
    public static void test(PhoneContainer b, int insertCount, int selectCount) {
        final int GAP  =  35461;
        ArrayList<String> errorMsg = new ArrayList<String>();

        long time0 = System.currentTimeMillis();
        
        for( long i = GAP % insertCount; i != 0; i = ( i + GAP ) % insertCount ) {
            b.add( i );
        }
        
        //System.out.println(Arrays.toString(b.toArray()));

        long time1 = System.currentTimeMillis();

        for( int i = 1; i < selectCount; i++ ) {
            int r = ThreadLocalRandom.current().nextInt(1,insertCount);
            long rThNumber = b.select( r );
            if( rThNumber != r ) {
                errorMsg.add( "Find error! " + r + "-th: " + rThNumber + "\n" );
            }
        }

        long time2 = System.currentTimeMillis();
        System.out.println( "Insertion Time: " + (time1-time0) + " ms, Selection Time: " + (time2-time1) + " ms, Total time: " + (time2-time0) + " ms");

        int errorCount = errorMsg.size();
        if (errorCount > 0) {
            System.out.println(errorMsg.toString());
            System.out.println("TEST FAILED on " + errorCount + " SELECTIONS!");
        } else {
            System.out.println("TEST PASSED!");
        }
        System.out.println();
    }

}