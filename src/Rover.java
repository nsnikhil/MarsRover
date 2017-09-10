import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * USE OF BUILDER PATTERN TO CONSTRUCT THE
 * ROVER OBJECT
 */
class Rover {

    private int mX_Coordinate,mY_Coordinate;
    private char mDirection;


    Rover(RoverBuilder roverBuilder){
        mX_Coordinate = roverBuilder.mX_Coordinate;
        mY_Coordinate = roverBuilder.mY_Coordinate;
        mDirection = roverBuilder.mDirection;
    }

    @Setter
    void setDirection(char direction) {
        this.mDirection = direction;
    }

    @Setter
    void setX_Coordinate(int xCoordinate) {
        this.mX_Coordinate = xCoordinate;
    }

    @Setter
    void setY_Coordinate(int yCoordinate) {
        this.mY_Coordinate = yCoordinate;
    }

    @Getter
    char getDirection() {
        return this.mDirection;
    }

    @Getter
    int getX_Coordinate() {
        return this.mX_Coordinate;
    }

    @Getter
    int getY_Coordinate() {
        return this.mY_Coordinate;
    }

    @Override
    public String toString() {
        return this.mX_Coordinate+" "
                +this.mY_Coordinate+" "
                +this.mDirection;
    }

    static class RoverBuilder{

        private int mX_Coordinate,mY_Coordinate;
        private char mDirection;

        RoverBuilder setX_Coordinate(int xCoordinate){
            this.mX_Coordinate = xCoordinate;
            return this;
        }

        RoverBuilder setY_Coordinate(int yCoordinate){
            this.mY_Coordinate = yCoordinate;
            return this;
        }

        RoverBuilder setDirection(char direction){
            this.mDirection = direction;
            return this;
        }

        Rover build(){
            return new Rover(this);
        }

    }
}
