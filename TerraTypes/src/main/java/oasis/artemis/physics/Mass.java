package oasis.artemis.physics;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Mass</h2>
 * <p>
 * Represents the mass of an object.
 * Non-SI units should be declared only once.
 * Conversion between unit systems is lossy.
 * </p>
 *
 * @param value Value of mass
 * @param unit  Unit of mass
 */
public record Mass(
        @Nonnegative double value,
        @Nonnull Unit unit
) implements Serializable {
    /**
     * Default constructor. Properties default to {@link Mass#MASSLESS}.
     */
    public Mass() {this(MASSLESS.value, MASSLESS.unit);}

    /**
     * Represents infinite mass. Value is set to {@link Double#MAX_VALUE}, and unit to {@link Unit#TONNE}.
     * This ensures that every unit conversion will still result in the value being {@link Double#MAX_VALUE}.
     * When adding units, change the unit of this to the highest raw denotation.
     */
    public static final Mass INFINITE = new Mass(Double.MAX_VALUE, Unit.TONNE);

    /**
     * Represents a massless object. Value is set to {@link Double#MIN_VALUE}, and unit to {@link Unit#MILLIGRAM}.
     * Since objects with zero as their value will break physics calculations, use this instead of zero.
     * This will convert to {@link Double#MIN_VALUE} {@link Unit#KILOGRAM} in physics calculations.
     */
    public static final Mass MASSLESS = new Mass(Double.MIN_VALUE, Unit.MILLIGRAM);

    /**
     * Gets the value of this mass denoted in milligrams.
     *
     * @return Milligrams
     */
    @Nonnegative
    public double valueMilligrams() {return value * unit.toMilligrams();}

    /**
     * Gets the value of this mass denoted in grams.
     *
     * @return Grams
     */
    @Nonnegative
    public double valueGrams() {
        return value * unit.toGrams();
    }

    /**
     * Gets the value of this mass denoted in kilograms.
     * All physics calculations use kilograms.
     *
     * @return Kilograms
     */
    @Nonnegative
    public double valueKilograms() {
        return value * unit.toKilograms();
    }

    /**
     * Gets the value of this mass denoted in tons.
     *
     * @return Tons
     */
    @Nonnegative
    public double valueTons() {
        return value * unit.toTons();
    }

    /**
     * Gets the value of this mass denoted in given unit.
     * Imperial units do not have a dedicated getter by design.
     * <b>Conversion between unit systems is lossy.</b>
     * Do not use this repetitively.
     *
     * @param unit Unit of denotation
     * @return Value in unit
     */
    @Nonnegative
    public double value(@Nonnull Unit unit) {
        return unit.convert(value, unit);
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
     * Conversion between SI and imperial is lossy, do not use repetitively.
     */
    public enum Unit {
        // Metric
        MILLIGRAM,
        GRAM,
        KILOGRAM,
        /**
         * <b>METRIC TON</b>
         * <p>For imperial tons, use {@link Unit#TONNE}</p>
         */
        TON,

        //
        // Imperial
        // These are North American units.
        //
        GRAIN,
        OUNCE,
        POUND,
        // North American ton (2,000 lbs)
        TONNE;

        public double toMilligrams() {
            return switch (this) {
                case MILLIGRAM -> 1;
                case GRAM -> 1000;
                case KILOGRAM -> 1000000;
                case TON -> 1000000000;

                case GRAIN -> 64.7989;
                case OUNCE -> 28349.5;
                case POUND -> 453592;
                case TONNE -> 1.016e+9;
            };
        }

        public double toGrams() {
            return switch (this) {
                case MILLIGRAM -> 0.001;
                case GRAM -> 1;
                case KILOGRAM -> 1000;
                case TON -> 1000000;

                case GRAIN -> 15.4324;
                case OUNCE -> 28.3495;
                case POUND -> 453.592;
                case TONNE -> 907185;
            };
        }

        public double toKilograms() {
            return switch (this) {
                case MILLIGRAM -> 0.000001;
                case GRAM -> 0.001;
                case KILOGRAM -> 1;
                case TON -> 1000;

                case GRAIN -> 6.4799e-5;
                case OUNCE -> 0.0283495;
                case POUND -> 0.453592;
                case TONNE -> 907.185;
            };
        }

        public double toTons() {
            return switch (this) {
                case MILLIGRAM -> 1e-9;
                case GRAM -> 0.000001;
                case KILOGRAM -> 0.001;
                case TON -> 1;

                case GRAIN -> 6.47989e-8;
                case OUNCE -> 2.83495e-5;
                case POUND -> 0.000453592;
                case TONNE -> 1.01605;
            };
        }

        public double toGrains() {
            return switch (this) {
                case MILLIGRAM -> 0.0154324;
                case GRAM -> 15.4324;
                case KILOGRAM -> 15432.4;
                case TON -> 1.543e+7;

                case GRAIN -> 1;
                case OUNCE -> 437.5;
                case POUND -> 7000;
                case TONNE -> 1.4e+7;
            };
        }

        public double toOunces() {
            return switch (this) {
                case MILLIGRAM -> 3.5274e-5;
                case GRAM -> 0.035274;
                case KILOGRAM -> 35.274;
                case TON -> 35274;

                case GRAIN -> 0.00228571;
                case OUNCE -> 1;
                case POUND -> 16;
                case TONNE -> 32000;
            };
        }

        public double toPounds() {
            return switch (this) {
                case MILLIGRAM -> 2.20462e-6;
                case GRAM -> 0.00220462;
                case KILOGRAM -> 2.204;
                case TON -> 2204.62;

                case GRAIN -> 0.000142857;
                case OUNCE -> 0.0625;
                case POUND -> 1;
                case TONNE -> 2000;
            };
        }

        public double toTonnes() {
            return switch (this) {
                case MILLIGRAM -> 9.84207e-10;
                case GRAM -> 9.84207e-7;
                case KILOGRAM -> 0.000984207;
                case TON -> 0.984207;

                case GRAIN -> 6.37755e-8;
                case OUNCE -> 2.79018e-5;
                case POUND -> 0.000446429;
                case TONNE -> 1;
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
                case MILLIGRAM -> sourceUnit.toMilligrams();
                case GRAM -> sourceUnit.toGrams();
                case KILOGRAM -> sourceUnit.toKilograms();
                case TON -> sourceUnit.toTons();

                case GRAIN -> sourceUnit.toGrains();
                case OUNCE -> sourceUnit.toOunces();
                case POUND -> sourceUnit.toPounds();
                case TONNE -> sourceUnit.toTonnes();
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

        /**
         * Finalizes building process and builds the mass.
         *
         * @return Built mass
         * @throws IllegalArgumentException When unit is {@code null}, or mass is negative
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public Mass build() throws IllegalArgumentException {
            if (unit == null || value < 0) throw new IllegalArgumentException();
            return new Mass(this);
        }
    }
}
