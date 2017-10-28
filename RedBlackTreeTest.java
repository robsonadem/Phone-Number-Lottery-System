
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RedBlackTreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RedBlackTreeTest
{
    /**
     * Default constructor for test class RedBlackTreeTest
     */
    public RedBlackTreeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testFindKth()
    {
        RedBlackTree<Integer> redBlack2 = new RedBlackTree<Integer>();
        try{
            redBlack2.insert(100);
            redBlack2.insert(10);
            redBlack2.insert(110);
            redBlack2.insert(101);
            redBlack2.insert(120);
            redBlack2.insert(9);
            redBlack2.insert(11);
            redBlack2.insert(90);
            assertEquals(new Integer(9), redBlack2.select(1));
            assertEquals(new Integer(10), redBlack2.select(2));
        }
        catch(Exception e){}
    }
}

