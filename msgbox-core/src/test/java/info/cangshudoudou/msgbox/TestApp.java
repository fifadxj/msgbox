package info.cangshudoudou.msgbox;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class TestApp {
    public static void main(String args[]) {
        //java org.junit.runner.JUnitCore TestClass1 [...other test classes...]
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TestApp.class);
    }

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeBehavior() {
        assertTrue(true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testForException() {
        throw new IndexOutOfBoundsException();
    }

}

