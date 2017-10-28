

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ArrayListPhoneContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ArrayListPhoneContainerTest
{
    /**
     * Default constructor for test class ArrayListPhoneContainerTest
     */
    public ArrayListPhoneContainerTest()
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
    public void testAdd()
    {
        ArrayListPhoneContainer arrayLis1 = new ArrayListPhoneContainer();
        arrayLis1.add(484891988);
        assertEquals(484891988, arrayLis1.select(1));
    }

    @Test
    public void testSelect()
    {
        ArrayListPhoneContainer arrayLis1 = new ArrayListPhoneContainer();
        arrayLis1.add(111111111);
        //test the duplicate insertion, if the test fails
        //it means we have allowed addition of duplicates
        arrayLis1.add(111111111);
        arrayLis1.add(223254562);
        assertEquals(223254562, arrayLis1.select(2));
    }
}


