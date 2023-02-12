package first.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void test() {
        PageHandler ph = new PageHandler(250, 1);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);

        PageHandler ph2 = new PageHandler(250, 11);
        ph2.print();
        System.out.println("ph2 = " + ph2);
        assertTrue(ph2.getBeginPage() == 11);
        assertTrue(ph2.getEndPage() == 20);

        PageHandler ph3 = new PageHandler(330, 25);
        ph3.print();
        System.out.println("ph3 = " + ph3);
        assertTrue(ph3.getBeginPage() == 21);
        assertTrue(ph3.getEndPage() == 30);

        PageHandler ph4 = new PageHandler(455,38);
        ph4.print();
        System.out.println("ph4 = " + ph4);
        assertTrue(ph4.getBeginPage() == 31);
        assertTrue(ph4.getEndPage() == 40);

        PageHandler ph5 = new PageHandler(120, 10);
        ph5.print();
        System.out.println("ph5 = " + ph5);
        assertTrue(ph5.getBeginPage() == 1);
        assertTrue(ph5.getEndPage() == 10);
    }

}