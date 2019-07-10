package domain;

public class Rate {
    private double rate;

    private Rate(double rate) {
        this.rate = rate;
    }

    public static Rate of(double amount) {
        return new Rate(amount);
    }

    public CurrencyValue apply(CurrencyValue value) {
        return CurrencyValue.of(value.amount * rate, Currency.EUR);
    }
}
