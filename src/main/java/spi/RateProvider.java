package spi;

import domain.Currency;
import domain.Investment;
import domain.Rate;

public interface RateProvider {
    Rate rate(Investment investment, Currency toCurrency);
}
