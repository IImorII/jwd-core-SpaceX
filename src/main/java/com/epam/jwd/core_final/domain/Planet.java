package com.epam.jwd.core_final.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Expected fields:
 * <p>
 * location {@link java.util.Map} - planet coordinate in the universe
 */
public class Planet extends AbstractBaseEntity{
    private Point location;
    public Planet(String name, Integer x, Integer y) {
        location = new Point(x, y);
        this.name = name;
        this.id = getId();
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return name;
    }

    public class Point {
        private Integer x;
        private Integer y;
        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public Double distanceTo(Point to) {
            Double result = Math.sqrt(Math.pow(Math.abs(x - to.x), 2) + Math.pow(Math.abs(y - to.y), 2));
            return result;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        @Override
        public String toString() {
            return "x: " + x + " | " + "y: " + y;
        }
    }
}
