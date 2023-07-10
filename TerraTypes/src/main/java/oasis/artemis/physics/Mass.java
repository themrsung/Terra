package oasis.artemis.physics;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Mass</h2>
 * <p>Represents the mass of an object.</p>
 *
 * @param value Value of mass
 * @param unit  Unit of mass
 */
public record Mass(
        @Nonnegative double value,
        @Nonnull Unit unit
) {
    /**
     * Gets the value of this mass denoted in grams.
     *
     * @return Grams
     */
    @Nonnegative
    public double valueGrams() {
        return value() * unit.toGrams();
    }

    /**
     * Gets the value of this mass denoted in kilograms.
     *
     * @return Kilograms
     */
    @Nonnegative
    public double valueKilograms() {
        return value() * unit.toKilograms();
    }

    /**
     * Gets the value of this mass denoted in tons.
     *
     * @return Tons
     */
    @Nonnegative
    public double valueTons() {
        return value() * unit.toTons();
    }

    /**
     * Adds delta to this mass.
     *
     * @param delta Delta to add
     * @param unit  Unit of delta to add
     * @return Resulting mass
     * @throws IllegalArgumentException When the resulting mass has a negative value
     */
    @Nonnull
    public Mass addValue(double delta, @Nonnull Unit unit) throws IllegalArgumentException {
        return toBuilder().value(value + this.unit.convert(delta, unit)).build();
    }

    /**
     * Changes the value of this mass.
     *
     * @param value New value
     * @param unit  New unit
     * @return Resulting mass
     */
    @Nonnull
    public Mass setValue(@Nonnegative double value, @Nonnull Unit unit) {
        return toBuilder().value(value).unit(unit).build();
    }

    /**
     * The unit of mass.
     */
    public enum Unit {
        GRAM,
        KILOGRAM,
        TON;

        public double toGrams() {
            return switch (this) {
                case GRAM -> 1;
                case KILOGRAM -> 1000;
                case TON -> 1000000;
            };
        }

        public double toKilograms() {
            return switch (this) {
                case GRAM -> 0.001;
                case KILOGRAM -> 1;
                case TON -> 1000;
            };
        }

        public double toTons() {
            return switch (this) {
                case GRAM -> 0.000001;
                case KILOGRAM -> 0.001;
                case TON -> 1;
            };
        }

        /**
         * Converts given value's unit to this unit.
         *
         * @param sourceValue Source value
         * @param sourceUnit  Unit to convert from
         * @return Converted value
         */
        public double convert(@Nonnegative double sourceValue, @Nonnull Unit sourceUnit) {
            double modifier = switch (this) {
                case GRAM -> sourceUnit.toGrams();
                case KILOGRAM -> sourceUnit.toKilograms();
                case TON -> sourceUnit.toTons();
            };

            return sourceValue * modifier;
        }
    }

    /**
     * Converts this mass to builder for modification.
     *
     * @return Builder
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    private Mass(@Nonnull Builder builder) {
        this(builder.value, builder.unit);
    }

    public static final class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.value = 0;
            this.unit = null;
        }

        private Builder(@Nonnull Mass mass) {
            this.value = mass.value;
            this.unit = mass.unit;
        }

        @Nonnegative
        private double value;
        @Nonnull
        private Unit unit;

        @Nonnull
        public Builder value(@Nonnegative double value) {
            this.value = value;
            return this;
        }

        @Nonnull
        public Builder unit(@Nonnull Unit unit) {
            this.unit = unit;
            return this;
        }

        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public Mass build() throws IllegalArgumentException {
            if (unit == null) throw new IllegalArgumentException();
            return new Mass(this);
        }
    }
}
