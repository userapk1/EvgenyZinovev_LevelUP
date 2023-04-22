package ticket.test.hm2;

public abstract class TicketImpl implements Ticket {

    @Override
    public boolean sumNumb(Integer input) {
        if (input == null) {
            throw new NullPointerException("Значение не может быть пустым");
        }
        if (input <= 99999) {
            throw new IllegalArgumentException("В номере билета должно быть 6 цифр");
        }
        if (input > 999999) {
            throw new IllegalArgumentException("В номере билета должно быть 6 цифр");
        }
        return ((input % 10) + (input / 10) % 10 + (input / 100) % 10 ==
            (input / 1000) % 10 + (input / 10000) % 10 + (input / 100000) % 10);
    }
}

