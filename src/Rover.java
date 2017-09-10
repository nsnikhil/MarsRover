/*
 * Copyright (C) 2017 nsnikhil
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

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
