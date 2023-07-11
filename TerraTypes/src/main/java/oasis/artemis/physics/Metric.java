package oasis.artemis.physics;

/**
 * <h2>Metric</h2>
 * <p>
 * Contains utility functions related to the metric system.
 * Every return value is denoted in SI standard units,
 * unless explicitly specified.
 * </p>
 */
public interface Metric {
    //
    // Distance
    //

    /**
     * Converts millimeter to meter.
     *
     * @param millimeter Millimeter
     * @return Meter
     */
    static double millimeter(double millimeter) {return millimeter / 1000;}

    /**
     * Converts centimeter to meter.
     *
     * @param centimeter Centimeter
     * @return Meter
     */
    static double centimeter(double centimeter) {return centimeter / 100;}

    /**
     * Converts kilometer to meter.
     *
     * @param kilometer Kilometer
     * @return Meter
     */
    static double kilometer(double kilometer) {return kilometer * 1000;}

    /**
     * Converts inch to meter.
     *
     * @param inch Inch
     * @return Meter
     */
    static double inch(double inch) {return inch * 0.0254;}

    /**
     * Converts feet to meter.
     *
     * @param feet Feet
     * @return Meter
     */
    static double feet(double feet) {return feet * 0.3048;}

    /**
     * Converts yard to meter.
     *
     * @param yard Yard
     * @return Meter
     */
    static double yard(double yard) {return yard * 0.9144;}

    /**
     * Converts mile to meter.
     *
     * @param mile Mile
     * @return Meter
     */
    static double mile(double mile) {return mile * 1609.34;}

    //
    // Speed
    //

    /**
     * Converts kilometers per hour to meters per second.
     *
     * @param kph km/h
     * @return m/s
     */
    static double kilometersPerHour(double kph) {return kph * 0.277778;}

    /**
     * Converts miles per hour to meters per second.
     *
     * @param mph miles/h
     * @return meters/s
     */
    static double milesPerHour(double mph) {return mph * 0.44704;}

    /**
     * Converts feet per second to meters per second.
     * Mainly used for bullet velocities.
     *
     * @param fps f/s
     * @return m/s
     */
    static double feetPerSecond(double fps) {return fps * 0.3048;}
}
