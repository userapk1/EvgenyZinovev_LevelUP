package ticket.test.hm2;

import com.github.javafaker.Faker;

public class App {

    public static void main(String[] args){

        /*String input = String.valueOf(112217);


            char one = input.charAt(0), two = input.charAt(1), three = input.charAt(2), four = input.charAt(3),
                five = input.charAt(4), six = input.charAt(5);
            int sum1 = one + two + three, sum2 = four + five + six;
            String result = (sum1 == sum2 && input.length()<=6) ? "true"
                :(sum1==sum2 && input.length()>6)? "Число должно состоять из 6 цифр"
                    :(sum1!=sum2 && input.length()>6)? "Число должно состоять из 6 цифр" : "false";

            System.out.printf("%s", result);*/


        final var faker = new Faker();
        final var email = faker.internet().emailAddress().toLowerCase();
        System.out.println(email);

    }


}
