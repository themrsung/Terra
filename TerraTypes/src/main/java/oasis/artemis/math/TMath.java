package oasis.artemis.math;

import javax.annotation.Nonnegative;

/**
 * <h2>TMath</h2>
 * <p>A utility interface containing math functions.</p>
 */
public interface TMath {
    /**
     * Adds magnitude to given number.
     * Performs {@link Math#abs(double)} to number, adds delta,
     * then converts it back a negative number if number was negative.
     *
     * @param number Number to add magnitude to
     * @param delta Delta to add (must be non-negative)
     * @return Resulting number
     */
    static double addMagnitude(double number, @Nonnegative double delta) {
        return (Math.abs(number) + delta) * (number < 0 ? -1 : 1);
    }

    /**
     * Performs an inverse tangent operation on given number.
     *
     * @param x Number to perform operation on
     * @return Inverse tangent of x
     */
    static double itan(double x) {
        return Math.toDegrees(Math.atan(x));
    }
}
