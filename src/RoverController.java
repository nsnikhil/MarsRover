/*
 * QUESTION :
 *
 *  A SQUAD OF ROBOTIC ROVERS ARE TO BE LANDED BY NASA ON A PLATEAU ON MARS. THIS PLATEAU, WHICH IS CURIOUSLY RECTANGULAR,
 *  MUST BE NAVIGATED BY THE ROVERS SO THAT THEIR ON-BOARD CAMERAS CAN GET A COMPLETE VIEW OF THE SURROUNDING TERRAIN TO SEND BACK TO EARTH.
 *  A ROVER'S POSITION AND LOCATION IS REPRESENTED BY A COMBINATION OF X AND Y CO-ORDINATES AND A LETTER REPRESENTING ONE OF
 *  THE FOUR CARDINAL COMPASS POINTS. THE PLATEAU IS DIVIDED UP INTO A GRID TO SIMPLIFY NAVIGATION. AN EXAMPLE POSITION
 *  MIGHT BE 0, 0, N, WHICH MEANS THE ROVER IS IN THE BOTTOM LEFT CORNER AND FACING NORTH.
 *
 *  IN ORDER TO CONTROL A ROVER, NASA SENDS A SIMPLE STRING OF LETTERS. THE POSSIBLE LETTERS ARE 'L', 'R' AND 'M'. 'L' AND 'R'
 *  MAKES THE ROVER SPIN 90 DEGREES LEFT OR RIGHT RESPECTIVELY, WITHOUT MOVING FROM ITS CURRENT SPOT. 'M' MEANS MOVE FORWARD ONE
 *  GRID POINT, AND MAINTAIN THE SAME HEADING.
 *
 * INPUT FORMAT
 *
 * FIRS LINE OF THE INPUT CONTAINS ONE INTEGER N
 * IF N == 1 YOU CONTROL A ROVER
 * IF N == 2 EXIT
 *
 * THE NEXT INPUT TAKE A INTEGER S WHICH IS THE SIZE
 * OF PLATEAU
 *
 * THE NEXT TWO INTEGER ARE X AND Y WHICH REPRESENT THE
 * X AND Y COORDINATE OF THE ROVER IN PLATEAU
 *
 * THE NEXT INPUT IA CHARACTER WHICH GIVES THE CURRENT
 * DIRECTION THE ROVER IS FACING
 *
 * NEXT INPUT IS A STRING WHICH ARE THE COMMANDS THE ROVER
 * HAS TO FOLLOW
 *
 * OUTPUT FORMAT :
 *
 * 'X Y D' CONTAINS TWO INTEGER X AND Y REPRESENTING
 * THE CURRENT COORDINATE AND D WHICH GIVES THE CURRENT
 * DIRECTION IN WHICH IS FACING
 *
 *
 * INPUT :
 *
 * ENTER 1 TO CONTROL A ROVER
 * ENTER 0 TO EXIT
 *
 * 1
 *
 * ENTER THE SIZE OF PLATEAU
 * 5
 *
 * ENTER THE X AND Y COORDINATES OF THE BOT
 * 1
 * 2
 *
 * ENTER THE BEARING FOR ROBOT
 * N
 *
 * ENTER THE COMMAND
 * LMLMLMLMM
 *
 * OUTPUT :
 * 1 3 N
 *
 * ENTER 1 TO CONTROL A ROVER
 * ENTER 0 TO EXIT
 *
 * 0
 *
 * PROCESS FINISHED WITH EXIT CODE 0
 */

import org.jetbrains.annotations.Contract;

import java.util.Scanner;

public class RoverController {

    public static void main(String... args) {
        RoverController roverController = new RoverController();
        roverController.operation();
    }

    private void operation() {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nEnter 1 to control a rover" +
                    "\nEnter 0 exit\n");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    int size, xCoordinate, yCoordinate;
                    char direction;
                    String command;

                    System.out.println("\nEnter the size of plateau\n");

                    size = scanner.nextInt();

                    /*
                     * VERIFY IF THE GIVEN COORDINATES FALL WITHIN
                     * THE RANGE OF PLATEAU ELSE KEEP ASKING FOR
                     * COORDINATES
                     */
                    do {
                        System.out.println("\nEnter the X and Y coordinates of the bot\n");

                        xCoordinate = scanner.nextInt();

                        yCoordinate = scanner.nextInt();

                    } while (!verifyCoordinates(xCoordinate, yCoordinate, size));


                    /*
                     * VERIFY IF THE BEARING GIVEN FOR THE
                     * ROVER IS VALID ELSE KEEP ASKING FOR
                     * VALID BEARING
                     */
                    do {

                        System.out.println("\nEnter the Bearing for robot\n");

                        direction = scanner.next(".").charAt(0);

                        direction = Character.toUpperCase(direction);

                    } while (!verifyDirection(direction));


                    /*
                     * VERIFY IF THE COMMAND GIVEN TO
                     * ROVER IS VALID ELSE KEEP ASKING UNTIL
                     * A PROPER COMMAND IS GIVEN
                     */
                    do {

                        System.out.println("\nEnter the command\n");

                        command = scanner.next();

                        command = command.toUpperCase();

                    } while (!verifyCommand(command));


                    Rover rover = new Rover.RoverBuilder()
                            .setX_Coordinate(xCoordinate)
                            .setY_Coordinate(yCoordinate)
                            .setDirection(direction)
                            .build();

                    moveRobot(rover, command,size);

                    System.out.println(rover.toString());

                    break;
            }

        } while (choice != 0);
    }


    /**
     * @param rover     THE ROVER TO CONTROL
     * @param command   THE CURRENT BEARING OF THE ROBOT
     * @param size      THE SIZE OF PLATEAU
     */
    private void moveRobot(Rover rover, String command,int size) {
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            switch (c) {
                case 'M':
                    moveRover(rover, rover.getDirection(),size);
                    break;
                case 'L':
                    rotateLeft(rover, rover.getDirection());
                    break;
                case 'R':
                    rotateRight(rover, rover.getDirection());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command " + c);
            }
        }
    }

    /**
     * @param rover             THE ROVER TO CONTROL
     * @param robotDirection    THE CURRENT BEARING OF THE ROBOT
     * @param size              THE SIZE OF PLATEAU
     */
    private void moveRover(Rover rover, char robotDirection,int size) {
        switch (robotDirection) {
            case 'N':
                if(isValidMovement(rover.getY_Coordinate()+1,size)){
                    rover.setY_Coordinate(rover.getY_Coordinate() + 1);
                }
                break;
            case 'S':
                if(isValidMovement(rover.getY_Coordinate()+1,size)) {
                    rover.setY_Coordinate(rover.getY_Coordinate() - 1);
                }
                break;
            case 'E':
                if(isValidMovement(rover.getX_Coordinate()+1,size)) {
                    rover.setX_Coordinate(rover.getX_Coordinate() + 1);
                }
                break;
            case 'W':
                if(isValidMovement(rover.getX_Coordinate()-1,size)) {
                    rover.setX_Coordinate(rover.getX_Coordinate() - 1);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid direction : " + robotDirection);
        }
    }

    /**
     * @param rover             THE ROVER TO CONTROL
     * @param robotDirection    THE PRESENT BEARING OF THE ROVER
     */
    private void rotateLeft(Rover rover, char robotDirection) {
        switch (robotDirection) {
            case 'N':
                rover.setDirection('W');
                break;
            case 'S':
                rover.setDirection('E');
                break;
            case 'E':
                rover.setDirection('N');
                break;
            case 'W':
                rover.setDirection('S');
                break;
            default:
                throw new IllegalArgumentException("Invalid direction : " + robotDirection);
        }
    }

    /**
     * @param rover             THE ROVER TO CONTROL
     * @param robotDirection    THE PRESENT BEARING OF THE ROVER
     */
    private void rotateRight(Rover rover, char robotDirection) {
        switch (robotDirection) {
            case 'N':
                rover.setDirection('E');
                break;
            case 'S':
                rover.setDirection('W');
                break;
            case 'E':
                rover.setDirection('S');
                break;
            case 'W':
                rover.setDirection('N');
                break;
            default:
                throw new IllegalArgumentException("Invalid direction : " + robotDirection);
        }
    }

    /**
     * @param xCoordinate       THE X-COORDINATES OF THE ROVER
     * @param yCoordinate       THE Y-COORDINATES OF THE ROVER
     * @param size              THE SIZE OF THE PLATEAU
     * @return                  TRUE IF THE COORDINATES LIES WITHIN PLATEAU
     */
    @Contract(pure = true)
    private boolean verifyCoordinates(int xCoordinate, int yCoordinate, int size) {
        return xCoordinate <= size && yCoordinate <= size;
    }

    /**
     * @param c     THE DIRECTION PASSED VIA INPUT
     * @return      TRUE IF THE DIRECTION IS VALID
     */
    @Contract(pure = true)
    private boolean verifyDirection(char c) {
        return c == 'N' || c == 'S' || c == 'E' || c == 'W';
    }

    /**
     * @param command   THE COMMAND GIVEN TO ROVER
     * @return          RETURN TRUE IF THE COMMAND GIVEN IS VALID
     */
    private boolean verifyCommand(String command) {
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if (c != 'L' && c != 'M' && c != 'R') {
                return false;
            }
        }
        return true;
    }

    /**
     * @param newLocation   THE NEW COORDINATE OF THE ROBOT
     * @param size          THE SIZE OF THE PLATEAU
     * @return              TRUE IF THE ROVER IS MOVED TO A VALID LOCATION
     */
    @Contract(pure = true)
    private boolean isValidMovement(int newLocation, int size){
        return newLocation >= 0 && newLocation <= size;
    }

}
