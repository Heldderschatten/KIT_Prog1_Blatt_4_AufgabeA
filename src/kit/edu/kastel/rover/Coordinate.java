package kit.edu.kastel.rover;

/**
 * The Class represents Coordinates on the Map.
 *
 * <p>
 * The Coordinates System  starts in the Top left Corner. and Goas to the Down right:
 * example: <br>
 * (1|1) (1|2) (1|3) <br>
 * (2|1) (2|2) (2|2) <br>
 * (3|1) (3|2) (3|3) <br>
 * </p>
 *
 * @author ufvgn
 */
public class Coordinate {

    private int height;
    private int width;

    /**
     * Set up the Coordinates with the two given Parametrs.
     *
     * @param height {@code int} how represent the height
     * @param width  {@code int} how represent the width
     */
    public Coordinate(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Return the Height value.
     *
     * @return {@code int} the Height coordinate.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the width value.
     *
     * @return {@code int} the width coordinate.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the neibourcoordinate from current Coordinate.
     * <p>
     *     have the Directions:
     *     <li>{@link Direction#UP}</li>
     *     <li>{@link Direction#DOWN}</li>
     *     <li>{@link Direction#RIGHT}</li>
     *     <li>{@link Direction#LEFT}</li>
     * </p>
     * @param direction the direction of the Neibour
     * @return the neibourcoordinate from current Coordinate. Return {@code null} if the Direction is not declared
     */

    public Coordinate getNeibourCoordinate(Direction direction) {
        switch (direction) {
            case UP:
                return new Coordinate(height - 1, width);
            case DOWN:
                return new Coordinate(height + 1, 0);
            case LEFT:
                return new Coordinate(height, width - 1);
            case RIGHT:
                return new Coordinate(height, width + 1);
            default:
                return null;
        }
    }
}
