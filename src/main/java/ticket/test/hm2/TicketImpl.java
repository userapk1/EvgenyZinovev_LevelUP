package ticket.test.hm2;

public class TicketImpl implements Ticket {

    @Override
    public String sumNumb(String input) {

        if (input.length() == 6) {
            char one = input.charAt(0);
            char two = input.charAt(1);
            char three = input.charAt(2);
            char four = input.charAt(3);
            char five = input.charAt(4);
            char six = input.charAt(5);
            int sum1 = one + two + three;
            int sum2 = four + five + six;
            if (sum1 == sum2) {
                return "true";
            }
            return "false";
        }

        return "Номер билета должен состоять из 6 цифр";
    }


}
