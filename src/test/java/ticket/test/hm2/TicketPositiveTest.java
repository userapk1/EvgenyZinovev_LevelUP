package ticket.test.hm2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketPositiveTest extends BaseTicketTest {

    //POSITIVE

    @Test
     void isItHappyTrue() {
        var input = new String("122221");
        var expected = new String("true");

        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isItHappyFalse() {
        var input = new String("112212");
        var expected = new String("false");

        var actual = ticket.sumNumb(input);

        Assertions.assertEquals(expected, actual);
    }
}
