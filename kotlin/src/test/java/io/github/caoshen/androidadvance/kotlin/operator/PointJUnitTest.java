package io.github.caoshen.androidadvance.kotlin.operator;

import org.junit.Test;

public class PointJUnitTest {

    @Test
    public void pointTest() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
//        System.out.println(a + b); // error
        System.out.println(PointKt.plus(a, b));
    }
}
