package domain;

import java.util.Objects;

public class CurrencyInvestment implements Investment {
    private final double amount;
    private final Currency currency;

    public CurrencyInvestment(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static CurrencyInvestment of(double amount, Currency currency) {
        return new CurrencyInvestment(amount, currency);
    }

    public CurrencyValue value(Currency toCurrency) {
        return CurrencyValue.of(amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyInvestment that = (CurrencyInvestment) o;
        return Double.compare(that.amount, amount) == 0 &&
                currency == that.currency;
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, currency);
    }
}
