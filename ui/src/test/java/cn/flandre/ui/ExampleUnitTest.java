package cn.flandre.ui;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

//        System.out.println(getNumber(11.02f, true));
    }
//    private float getNumber(float n, boolean up) {
//        String s = String.valueOf(n);
//        int i, j = 0;
//        if (s.charAt(0) == '0') {
//            for (i = 0; i < s.length() & s.charAt(i) != '.'; i++) ;
//            for (++i; i < s.length() & s.charAt(i) == '0'; i++) j++;
//            return (float) ((s.charAt(i - 1) - '0' + 1) * Math.pow(10, -j));
//        } else {
//            for (i = 0; i < s.length() & s.charAt(i) != '.'; i++) j++;
//            return (float) ((s.charAt(0) - '0' + 1) * Math.pow(10, j));
//        }
//    }
}