import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Supported currencies
        String[] currencies = {"INR", "USD", "EUR", "GBP", "JPY"};
        double[][] rates = {
                // To: INR,   USD,   EUR,   GBP,   JPY
                {1.0,   0.012, 0.011, 0.0096, 1.74},   // From INR
                {83.5,  1.0,   0.92,  0.80,   145.6},  // From USD
                {91.0,  1.09,  1.0,   0.87,   158.3},  // From EUR
                {105.0, 1.25,  1.15,  1.0,    182.2},  // From GBP
                {0.58,  0.0069,0.0063,0.0055, 1.0}     // From JPY
        };

        System.out.println("Available Currencies: INR, USD, EUR, GBP, JPY");

        // Base currency input
        System.out.print("Enter base currency: ");
        String base = sc.next().toUpperCase();

        // Target currency input
        System.out.print("Enter target currency: ");
        String target = sc.next().toUpperCase();

        // Amount input
        System.out.print("Enter amount to convert: ");
        double amount = sc.nextDouble();

        // Find index of currencies
        int baseIndex = -1, targetIndex = -1;
        for (int i = 0; i < currencies.length; i++) {
            if (currencies[i].equals(base)) baseIndex = i;
            if (currencies[i].equals(target)) targetIndex = i;
        }

        // Validation
        if (baseIndex == -1 || targetIndex == -1) {
            System.out.println("Invalid currency selection!");
        } else {
            double converted = amount * rates[baseIndex][targetIndex];
            System.out.printf("%.2f %s = %.2f %s%n", amount, base, converted, target);
        }

        sc.close();
    }
}