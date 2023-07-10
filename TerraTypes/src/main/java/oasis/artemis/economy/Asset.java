package oasis.artemis.economy;

import oasis.artemis.util.Unique;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.UUID;

/**
 * <h2>Asset</h2>
 * <p>
 * An asset represents an economic ownership or right.
 * This is used in economic context.
 * </p>
 * <p>
 * An asset itself does not contain quantitative information.
 * It is simply a representation of the <b>type</b> of ownership or right.
 * For example, cash does not represent the amount of cash an actor is holding,
 * but the currency an actor is holding.
 * </p>
 * <p>
 * To contain quantitative information, use AssetStack.
 * </p>
 * <p>
 * Assets are designed to be immutable, since they are simple types.
 * </p>
 */
public interface Asset extends Unique, Serializable {
    /**
     * Gets the symbol of this asset.
     * <p>
     * Assets use unique symbols instead of UUIDs.
     * Their UUIDs are derived from their symbol by delegation to
     * {@link UUID#nameUUIDFromBytes(byte[])}.
     * </p>
     *
     * @return Symbol of this asset
     */
    @Nonnull
    String getSymbol();

    /**
     * Whether this asset is integral.
     * <p>
     * Integral assets do not allow decimal points in their quantity.
     * The enforcing of decimal points is handled in AssetStack.
     * </p>
     *
     * @return {@code true} if this asset is integral, and does not allow decimal points in its quantity
     */
    boolean isIntegral();

    @Nonnull
    @Override
    default UUID getUniqueId() {return UUID.nameUUIDFromBytes(getSymbol().getBytes());}
}
