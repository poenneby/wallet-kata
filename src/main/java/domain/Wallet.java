package domain;

import spi.RateProvider;

import java.util.Arrays;
import java.util.List;

public class Wallet {
    private List<Investment> investments;

    private Wallet(List<Investment> investments) {
        this.investments = investments;
    }

    public static Wallet empty() {
        return new Wallet(null);
    }

    public static Wallet with(Investment... investments) {
        return new Wallet(Arrays.asList(investments));
    }

    public Value value(Currency toCurrency, RateProvider rateProvider) {
        if (investments == null) return CurrencyValue.zero(toCurrency);
        return investments.parallelStream().reduce(CurrencyValue.zero(toCurrency),
                (value1, investment) -> {
                    Rate rate = rateProvider.rate(investment, toCurrency);
                    return rate.apply(investment.value(toCurrency));
                },
                (value1, value2) -> value1.add(value2));
    }
}
