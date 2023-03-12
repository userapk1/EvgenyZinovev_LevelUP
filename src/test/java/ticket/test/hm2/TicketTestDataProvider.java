package ticket.test.hm2;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;


public class TicketTestDataProvider {

    static Stream<Arguments> dataProviderTrue() {
        return Stream.of(Arguments.of(Integer.toString(122221)));
    }

    static Stream<Arguments> dataProviderFalse() {
        return Stream.of(Arguments.of(Integer.toString(112212)));
    }

    static Stream<Arguments> dataProviderLessSix() {
        return Stream.of(Arguments.of(Integer.toString(121)));
    }

    static Stream<Arguments> dataProviderMoreSix() {
        return Stream.of(Arguments.of(Integer.toString(12222133)));
    }

    static Stream<Arguments> dataProviderLetter() {
        return Stream.of(Arguments.of("egjg"))
            ;
    }


}
