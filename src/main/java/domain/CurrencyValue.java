package domain;

import java.util.Objects;

public class CurrencyValue extends Value {
    private final Currency currency;

    public CurrencyValue(double amount, Currency currency) {
        super(amount);
        this.currency = currency;
    }

    public static CurrencyValue zero(Currency currency) {
        return new CurrencyValue(0.0, currency);
    }

    public static CurrencyValue of(double amount, Currency currency) {
        return new CurrencyValue(amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyValue value = (CurrencyValue) o;
        return Double.compare(value.amount, amount) == 0 &&
                currency == value.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public CurrencyValue add(CurrencyValue value) {
        return new CurrencyValue(this.amount + value.amount, currency);
    }
}
