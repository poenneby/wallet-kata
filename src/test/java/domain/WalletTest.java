package domain;

import org.junit.Test;
import spi.RateProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WalletTest {
    @Test
    public void should_have_zero_value_when_there_are_no_investments() {
        Wallet wallet = Wallet.empty();

        RateProvider rateProvider = mock(RateProvider.class);

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.zero(Currency.EUR));
    }

    @Test
    public void should_value_one_investment() {
        Wallet wallet = Wallet.with(CurrencyInvestment.of(10.0, Currency.EUR));

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(Investment.class), any(Currency.class))).thenReturn(Rate.of(1.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(10.0, Currency.EUR));

    }

    @Test
    public void should_value_multiple_investments() {
        Wallet wallet = Wallet.with(
                CurrencyInvestment.of(10.0, Currency.EUR),
                CurrencyInvestment.of(20.0, Currency.EUR));

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(Investment.class), any(Currency.class))).thenReturn(Rate.of(1.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(30.0, Currency.EUR));

    }

    @Test
    public void should_value_different_investments() {
        PetroleumInvestment petroleumInvestment = PetroleumInvestment.of(1);

        Wallet wallet = Wallet.with(petroleumInvestment);

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(petroleumInvestment, Currency.EUR)).thenReturn(Rate.of(58.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(58.0, Currency.EUR));
    }

    @Test
    public void should_value_mixed_investments() {
        PetroleumInvestment petroleumInvestment = PetroleumInvestment.of(1);
        CurrencyInvestment currencyInvestment = CurrencyInvestment.of(10.0, Currency.EUR);
        Wallet wallet = Wallet.with(petroleumInvestment, currencyInvestment);

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(petroleumInvestment, Currency.EUR)).thenReturn(Rate.of(58.0));
        when(rateProvider.rate(currencyInvestment, Currency.EUR)).thenReturn(Rate.of(1.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(68.0, Currency.EUR));
    }

    @Test
    public void should_value_investment_into_different_currency() {
        CurrencyInvestment currencyInvestment = CurrencyInvestment.of(10.0, Currency.USD);
        Wallet wallet = Wallet.with(currencyInvestment);

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(currencyInvestment, Currency.EUR)).thenReturn(Rate.of(0.9));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(9.0, Currency.EUR));
    }

    @Test
    public void should_value_multiple_currency_investments_into_different_currency() {
        CurrencyInvestment eurCurrencyInvestment = CurrencyInvestment.of(10.0, Currency.EUR);
        CurrencyInvestment usdCurrencyInvestment = CurrencyInvestment.of(10.0, Currency.USD);
        Wallet wallet = Wallet.with(eurCurrencyInvestment, usdCurrencyInvestment);

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(usdCurrencyInvestment, Currency.EUR)).thenReturn(Rate.of(0.9));
        when(rateProvider.rate(eurCurrencyInvestment, Currency.EUR)).thenReturn(Rate.of(1.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(19.0, Currency.EUR));
    }

    @Test
    public void should_value_multiple_mixed_investments_into_different_currency() {
        CurrencyInvestment eurCurrencyInvestment = CurrencyInvestment.of(10.0, Currency.EUR);
        CurrencyInvestment usdCurrencyInvestment = CurrencyInvestment.of(10.0, Currency.USD);
        PetroleumInvestment petroleumInvestment = PetroleumInvestment.of(1);
        Wallet wallet = Wallet.with(eurCurrencyInvestment, usdCurrencyInvestment, petroleumInvestment);

        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(usdCurrencyInvestment, Currency.EUR)).thenReturn(Rate.of(0.9));
        when(rateProvider.rate(eurCurrencyInvestment, Currency.EUR)).thenReturn(Rate.of(1.0));
        when(rateProvider.rate(petroleumInvestment, Currency.EUR)).thenReturn(Rate.of(58.0));

        assertThat(wallet.value(Currency.EUR, rateProvider)).isEqualTo(CurrencyValue.of(77.0, Currency.EUR));
    }


}