package kit.edu.kastel.rover;

/**
 * Represents the map by sending from the Rover.
 *
 * @author ufvgn
 */
public class MapV1 {
    private static final char[] BARRIERSYMBOLS = new char[]{'|', '_', '/', '\\', '*'};
    private static final char ROVERSYMBOL = 'R';
    private static final char GOALSYMBOL = 'x';
    private static final char ROVERANDGOALSYMBOL = 'X';
    private static final char PASSABLETARIN = ' ';
    //The first is the height and the sceond is the width
    private char[][] visibileMap;
    private MapSymboles[][] technicalMap;
    /**
     * set up the size of the Map.
     *
     * @param width  {@code int} is the width from the Map.
     * @param height {@code int} is the height from the Map.
     */
    public MapV1(int width, int height) {
        this.visibileMap = new char[height][width];
        this.technicalMap = new MapSymboles[height][width];
    }

    /**
     * Set a specif One specic line in Map. Will return if all oke.
     *
     * @param height is the current High of the line. ( count from Top to Bottom).
     * @param line   is a char Array which represent the line.
     * @return return {@code true} if the line is OK. Else will be return {@code false}
     */
    public boolean setOneLine(int height, char[] line) {
        //Check all chars vaild
        for (char checkingChar : line) {
            if (!checkCharIsVaild(checkingChar)) {
                return false;
            }
        }
        this.visibileMap[height] = line;
        setTechnicalMapLine(height, line);
        return true;
    }
    private void setTechnicalMapLine(int height, char[] line) {
        MapSymboles[] transfomrmedChars = new MapSymboles[technicalMap[height].length];
        for (int i = 0; i < transfomrmedChars.length; i++) {
            transfomrmedChars[i] = transformCharToMapSymboles(line[i]);
        }
        technicalMap[height] = transfomrmedChars;
    }

    private MapSymboles transformCharToMapSymboles(char c) {
        //Check GoalSymbols
        switch (c) {
            case ROVERSYMBOL:
                return MapSymboles.ROVER;
            case ROVERANDGOALSYMBOL:
                return MapSymboles.GOALANDROVER;
            case PASSABLETARIN:
                return MapSymboles.PASSABLETERRAIN;
            case GOALSYMBOL:
                return MapSymboles.GOAL;
            default:
                for (char barrierSymbol : BARRIERSYMBOLS) {
                    if (barrierSymbol == c) {
                        return MapSymboles.BARRIER;
                    }
                }
                throw new IllegalArgumentException(); //todo find better Solution as a Exception

        }
    }

    private boolean checkCharIsVaild(char character) {
        //check passibleTarain
        if (PASSABLETARIN == character) {
            return true;
        }
        //Check Barriers
        for (char barrier : BARRIERSYMBOLS) {
            if (barrier == character) {
                return true;
            }
        }
        //Check Rover
        if (ROVERSYMBOL == character) {
            return true;
        }
        if (GOALSYMBOL == character || ROVERANDGOALSYMBOL == character) {
            return true;
        }

        return false;
    }

    /**
     * Check the map is Vaild.
     *
     * @return if the map is vaild {@code true}. Else will be return {@code false}
     */
    public boolean mapIsVaild() {
        for (char[] line : visibileMap) {
            for (char checkingChar : line) {
                if (!checkCharIsVaild(checkingChar)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Moves the Rover by  given direction and count.
     *
     * @param count     the number of Moves that the Rover do
     * @param direction the Direction of the move for the Rover
     */
    public void moveRover(Direction direction, int count) {
        for (int i = 0; i < count; i++) {
            int[] newRoverPostion = newRoverPostion(direction);
            int[] currendRoverPostion = roverPostion();
            if (canMove(newRoverPostion)) {
                technicalMap[currendRoverPostion[0]][currendRoverPostion[1]] = MapSymboles.PASSABLETERRAIN;
                visibileMap[currendRoverPostion[0]][currendRoverPostion[1]] = PASSABLETARIN;
                technicalMap[newRoverPostion[0]][newRoverPostion[1]] = MapSymboles.ROVER;
                visibileMap[newRoverPostion[0]][newRoverPostion[1]] = ROVERSYMBOL;
                //todo Ziel erreicht Alog reinbringen
            } else {
                break;
            }
        }
    }

    private int[] newRoverPostion(Direction direction) {
        int[] roverPostion = roverPostion();
        switch (direction) {
            case UP:
                roverPostion[0]--;
                break;
            case DOWN:
                roverPostion[0]++;
                break;
            case LEFT:
                roverPostion[1]--;
                break;
            case RIGHT:
                roverPostion[1]++;
                break;
            default:
                break;
        }
        return roverPostion;
    }

    private boolean canMove(int[] postion) {
        if (postion[0] < 0 || postion[0] >= visibileMap.length) {
            return false;
        }
        if (postion[1] < 0 || postion[1] >= visibileMap[0].length) {
            return false;
        }
        return technicalMap[postion[0]][postion[1]] != MapSymboles.BARRIER;
    }

    //
    /* Return the Rover postion
    int[0] --> Height
    int[1] --> width
     */
    private int[] roverPostion() {
        for (int height = 0; height < visibileMap.length; height++) {
            for (int width = 0; width < visibileMap[height].length; width++) {
                if (technicalMap[height][width] == MapSymboles.ROVER) {
                    return new int[]{height, width};
                }
            }
        }
        return null; //todo find better solution
    }

    /**
     * Return the visible Map.
     * @return Return a {@code char[][]} wich one represent the Map.
     */
    public char[][] getVisibileMap() {
        return visibileMap;
    }
}
