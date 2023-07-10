package oasis.artemis.economy;

import oasis.artemis.exception.economy.DifferentAssetException;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>AssetStack</h2>
 * <p>
 * Represents the holding of an {@link Asset}.
 * Asset stacks are immutable, and require the use of a builder to modify.
 * </p>
 *
 * @param asset    Asset to hold within this stack
 * @param quantity Quantity of asset held within this stack
 */
public record AssetStack(
        @Nonnull Asset asset,
        double quantity
) implements Serializable {
    /**
     * Creates a new empty asset stack.
     *
     * @param asset Asset to hold
     */
    public AssetStack(@Nonnull Asset asset) {
        this(asset, 0);
    }

    /**
     * Gets a new builder instance.
     *
     * @return {@link Builder}
     */
    @Nonnull
    public static Builder builder() {return new Builder();}

    /**
     * Gets the quantity of this asset stack.
     * If the asset is integral, the quantity will be rounded to the nearest integer.
     *
     * @return Quantity
     */
    @Override
    public double quantity() {
        return asset.isIntegral() ? Math.round(quantity) : quantity;
    }

    /**
     * Changes the quantity of this asset stack.
     * This does not change the quantity of this instance, but returns a new stack with the given quantity.
     *
     * @param quantity New quantity
     * @return Resulting asset stack
     */
    @Nonnull
    public AssetStack setQuantity(double quantity) {
        return toBuilder().quantity(quantity).build();
    }

    /**
     * Adds delta to this asset stack's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the given delta added.
     *
     * @param delta Delta to add
     * @return Resulting asset stack
     */
    @Nonnull
    public AssetStack plusQuantity(double delta) {
        return toBuilder().quantity(quantity + delta).build();
    }

    /**
     * Removes delta from this asset stack's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the given delta removed.
     *
     * @param delta Delta to remove
     * @return Resulting asset stack
     */
    @Nonnull
    public AssetStack minusQuantity(double delta) {
        return toBuilder().quantity(quantity - delta).build();
    }

    /**
     * Modifies the quantity of this asset stack.
     * This does not change the quantity of this instance, but returns a new stack with the given modifier applied.
     *
     * @param modifier Modifier to apply
     * @return Resulting asset stack
     */
    @Nonnull
    public AssetStack modifyQuantity(double modifier) {
        return toBuilder().quantity(quantity * modifier).build();
    }

    /**
     * Divides the quantity of this asset stack.
     * This does not change the quantity of this instance, but returns a new stack with the quantity divided by given denominator.
     *
     * @param denominator Denominator to divide by
     * @return Resulting asset stack
     * @throws ArithmeticException When denominator is zero
     */
    @Nonnull
    public AssetStack divideQuantity(double denominator) throws ArithmeticException {
        return toBuilder().quantity(quantity / denominator).build();
    }

    /**
     * Adds the given stack's quantity to this stack's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the resulting quantity.
     *
     * @param other Other stack
     * @return Resulting stack
     * @throws DifferentAssetException When the assets are different
     */
    @Nonnull
    public AssetStack plus(@Nonnull AssetStack other) throws DifferentAssetException {
        if (!asset.equals(other.asset)) throw new DifferentAssetException(asset, other.asset);
        return plusQuantity(other.quantity);
    }

    /**
     * Subtracts the given stack's quantity from this asset's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the resulting quantity.
     *
     * @param other Other stack
     * @return Resulting stack
     * @throws DifferentAssetException When the assets are different
     */
    @Nonnull
    public AssetStack minus(@Nonnull AssetStack other) throws DifferentAssetException {
        if (!asset.equals(other.asset)) throw new DifferentAssetException(asset, other.asset);
        return minusQuantity(other.quantity);
    }

    /**
     * Multiplies this stack's quantity from the given stack's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the resulting quantity.
     *
     * @param other Other stack
     * @return Resulting stack
     * @throws DifferentAssetException When the assets are different
     */
    @Nonnull
    public AssetStack modify(@Nonnull AssetStack other) throws DifferentAssetException {
        if (!asset.equals(other.asset)) throw new DifferentAssetException(asset, other.asset);
        return modifyQuantity(other.quantity);
    }

    /**
     * Divides this stack's quantity by the given stack's quantity.
     * This does not change the quantity of this instance, but returns a new stack with the resulting quantity.
     *
     * @param other Other stack
     * @return Resulting stack
     * @throws DifferentAssetException When the assets are different
     * @throws ArithmeticException     When the given stack's quantity is zero
     */
    @Nonnull
    public AssetStack divide(@Nonnull AssetStack other) throws DifferentAssetException, ArithmeticException {
        if (!asset.equals(other.asset)) throw new DifferentAssetException(asset, other.asset);
        return divideQuantity(other.quantity);
    }


    /**
     * Gets a new builder instance populated with this stack's data.
     *
     * @return Builder instance
     */
    @Nonnull
    public Builder toBuilder() {return new Builder(this);}

    private AssetStack(@Nonnull Builder builder) {
        this(builder.asset, builder.quantity);
    }

    public static class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.asset = null;
            this.quantity = 0;
        }

        private Builder(@Nonnull AssetStack stack) {
            this.asset = stack.asset;
            this.quantity = stack.quantity;
        }

        @Nonnull
        private Asset asset;
        private double quantity;

        @Nonnull
        public Builder asset(@Nonnull Asset asset) {
            this.asset = asset;
            return this;
        }

        @Nonnull
        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Finishes the building sequence and builds the asset stack.
         *
         * @return Built {@link AssetStack}
         * @throws IllegalArgumentException When the required field {@link Builder#asset} is null,
         *                                  or a number with decimal points were provided as quantity for an integral asset
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public AssetStack build() throws IllegalArgumentException {
            if (asset == null || (asset.isIntegral() && quantity % 1 != 0)) throw new IllegalArgumentException();
            return new AssetStack(this);
        }
    }
}
