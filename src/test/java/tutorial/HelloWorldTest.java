package tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    @Test
    public void HelloWorldNormalNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("1", hw.convert(1));
        Assertions.assertEquals("2", hw.convert(2));
    }

    @Test
    public void HelloWorldThreeNumbers() {

        HelloWorld fb = new HelloWorld();
        Assertions.assertEquals("Hello", fb.convert(3));
    }

    @Test
    public void HelloWorldFiveNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("World", hw.convert(5));
    }

    @Test
    public void HelloWorldThreeAndFiveNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("World", hw.convert(5));
    }
}