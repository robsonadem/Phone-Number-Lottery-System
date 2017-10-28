

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HashSetPhoneContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HashSetPhoneContainerTest
{
    /**
     * Default constructor for test class HashSetPhoneContainerTest
     */
    public HashSetPhoneContainerTest()
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
        HashSetPhoneContainer hashSetP1 = new HashSetPhoneContainer();
        hashSetP1.add(467890567);
        assertEquals(467890567, hashSetP1.select(1));
    }

    @Test
    public void testSelect()
    {
        HashSetPhoneContainer hashSetP1 = new HashSetPhoneContainer();
        hashSetP1.add(48459165);
        //test the duplicate insertion, if the test fails
        //it means we have allowed addition of duplicates
        hashSetP1.add(48459165);
        hashSetP1.add(467890567);
        assertEquals(467890567, hashSetP1.select(2));
    }
}


