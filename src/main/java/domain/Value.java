package domain;

import java.util.Objects;

public class Value {

    protected final double amount;

    public Value(double amount) {
        this.amount = amount;
    }

    public static Value zero() {
        return new Value(0.0);
    }

    public static Value of(double amount) {
        return new Value(amount);
    }


    public Value add(Value value) {
        return new Value(this.amount + value.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return Double.compare(value.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Value{" +
                "amount=" + amount +
                '}';
    }
}
