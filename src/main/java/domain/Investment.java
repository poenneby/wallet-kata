package domain;

public interface Investment {
    CurrencyValue value(Currency toCurrency);
}
