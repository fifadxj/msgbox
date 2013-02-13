package info.cangshudoudou.msgbox;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestApp2 extends TestCase {
    public void test1() {
        //assertTrue(false);
    }

    public void test2() {
        assertTrue(true);
    }

    public static void main(String[] args) {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestApp2.class);
        TestRunner.run(suite);
    }
}
