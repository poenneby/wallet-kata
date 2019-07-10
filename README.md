# Wallet
## Subject
Given a Wallet containing Investments, build a function that compute the value of wallet in a currency.

Each Investment has a quantity and an InvestmentType. The InvestmentType can be for example petroleum, Euros, bitcoins and Dollars.

In order to value the portfolio in a Currency you can use an external API to get exchange rates (examples provided below).

## Object sample
`Value value = Wallet(Investment(5, PETROLEUM)).value(EUR, rateProvider)`

With `rateProvider` an implementation of this interface :

`rateProvider.rate(Investment, ToCurrency)` -> `Amount`
and `PETROLEUM` is a `InvestmentType` and `EUR` is a `Currency`.

## Functional sample
`Value value = compute_value(Wallet(Investment(5, PETROLEUM), EUR, rateProvider))`

Where `rateProvider` is a function with this signature :

`rateProvider(Investment, ToCurrency) -> Amount`
and `PETROLEUM` is a `InvestmentType` and `EUR` is a `Currency`.

## Suggested rates sources
Some APIs can be used to provide rates:

- http://api.fixer.io/
- https://1forge.com/forex-data-api
- https://finance.google.com/