

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RedBlackTreePhoneNumberContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RedBlackTreePhoneNumberContainerTest
{
    /**
     * Default constructor for test class RedBlackTreePhoneNumberContainerTest
     */
    public RedBlackTreePhoneNumberContainerTest()
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
        RedBlackTreePhoneContainer redBlack1 = new RedBlackTreePhoneContainer();
        redBlack1.add(new Long(484891592));
    }
}

