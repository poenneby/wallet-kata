package domain;

public class PetroleumInvestment implements Investment {
    private int amount;

    public PetroleumInvestment(int amount) {
        this.amount = amount;
    }

    public static PetroleumInvestment of(int amount) {
        return new PetroleumInvestment(amount);
    }

    @Override
    public CurrencyValue value(Currency toCurrency) {
        return CurrencyValue.of(Double.valueOf(amount), toCurrency);
    }
}
