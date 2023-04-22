package ticket.test.hm2;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;


public class TicketTestDataProvider {

    static Stream<Arguments> dataProviderTrue() {
        return Stream.of(Arguments.of(122221));
    }

    static Stream<Arguments> dataProviderFalse() {
        return Stream.of(Arguments.of(112212));
    }

    static Stream<Arguments> dataProviderLessSix() { return Stream.of(Arguments.of(121)); }

    static Stream<Arguments> dataProviderMoreSix() {
        return Stream.of(Arguments.of(12222133));
    }
}
