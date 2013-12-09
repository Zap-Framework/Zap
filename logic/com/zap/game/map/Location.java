/**
 *  This file is part of Zap Framework.
 * 
 *  Zap is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Zap is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Zap.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zap.game.map;

/**
 *
 * @author Faris
 */
public class Location {

	protected int x, y, z;
	private Region region;

	public Location(int x, int y) {
		setX(x).setY(y).setH(0);
		this.region = new Region(this);
	}

	public Location(int x, int y, int h) {
		setX(x).setY(y).setH(z);
		this.region = new Region(this);
	}

	public Location setX(int x) {
		lastX = this.x;
		this.x = x;
		return this;
	}

	public int lastX = 0, lastY = 0;

	public Location newLocation(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.region = new Region(this);
		return this;
	}

	public Location newLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.region = new Region(this);
		return this;
	}

	public int getX() {
		return x;
	}

	public Location setY(int y) {
		lastY = this.y;
		this.y = y;
		return this;
	}

	public int getY() {
		return y;
	}

	public Location setH(int z) {
		this.z = z;
		return this;
	}

	public int getH() {
		return z;
	}

	public Region getRegion() {
		return region;
	}

	/**
	 * Transforms this location.
	 * 
	 * @param x
	 *            the x difference
	 * @param y
	 *            the y difference
	 * 
	 * @return the transformed location
	 */
	public Location transform(int x, int y) {
		return setX(getX() + x).setY(getY() + y);
	}

	/**
	 * Transforms this location.
	 * 
	 * @param x
	 *            the x difference
	 * @param y
	 *            the y difference
	 * @param setH
	 *            the setH difference
	 * 
	 * @return the transformed location
	 */
	public Location transform(int x, int y, int z) {
		return setX(getX() + x).setY(getY() + y).setH(getH() + z);
	}

	/**
	 * Gets the copy of this location.
	 * 
	 * @return the copy of this location
	 */
	public Location copy() {
		return new Location(getX(), getY(), getH());
	}

	/**
	 * Checks if this Location is viewable from the other Location.
	 * 
	 * @param other
	 *            the other Location
	 * @return true if it is viewable, false otherwise
	 */
	public boolean withinDistance(Location other) {
		if (this.getH() != other.getH()) {
			return false;
		}
		Location p = delta(this, other);
		return p.getX() <= 14 && p.getX() >= -15 && p.getY() <= 14
				&& p.getY() >= -15;
	}

	/**
	 * Returns the delta coordinates. Note that the returned Location is not an
	 * actual Location, instead it's values represent the delta values between
	 * the two arguments.
	 * 
	 * @param a
	 *            the first Location
	 * @param b
	 *            the second Location
	 * @return the delta coordinates contained within a Location
	 */
	public static Location delta(Location a, Location b) {
		return new Location(b.getX() - a.getX(), b.getY() - a.getY());
	}

	/**
	 * Checks if other location is within distance with this location.
	 * 
	 * @param location
	 *            the other location
	 * 
	 * @param distance
	 *            the distance
	 */
	public boolean withinDistance(Location location, int distance) {
		if (z != location.getH()) {
			return false;
		}
		int diffX = Math.abs(location.getX() - x);
		int diffY = Math.abs(location.getY() - y);
		return diffX < distance && diffY < distance;
	}

	/**
	 * Checks if two locations represent the same spot in the world.
	 * 
	 * @param location
	 *            the other location
	 */
	public boolean sameAs(Location location) {
		return location.getX() == x && location.getY() == y
				&& location.getH() == z;
	}

	/**
	 * Checks to see if the location is within an area
	 * 
	 * @param location
	 *            The location
	 * @param north
	 *            The northern most coordinate
	 * @param east
	 *            The eastern most coordinate
	 * @param south
	 *            The southern most coordinate
	 * @param west
	 *            The western most coordinate
	 * @return
	 */
	public boolean withinArea(final Location location, final int north,
			final int east, final int south, final int west) {
		if (location.getY() <= north && location.getX() <= east
				&& location.getY() >= south && location.x >= west) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the region coordinate of this location.
	 * 
	 * @return the region x coordinate
	 */
	public int regionX() {
		return (x >> 3) - 6;
	}

	/**
	 * Gets the region coordinate of this location.
	 * 
	 * @return the region y coordinate
	 */
	public int regionY() {
		return (y >> 3) - 6;
	}

	/**
	 * Gets the local coordinate of this location.
	 * 
	 * @return the local x coordinate
	 */
	public int localX() {
		return localX(this);
	}

	/**
	 * Gets the local coordinate of this location.
	 * 
	 * @return the local y coordinate
	 */
	public int localY() {
		return localY(this);
	}

	/**
	 * Gets the relative local coordinate to another region.
	 * 
	 * @return the local x coordinate
	 */
	public int localX(Location location) {
		return location.x - 8 * location.regionX();
	}

	/**
	 * Gets the relative local coordinate to another region.
	 * 
	 * @return the local y coordinate
	 */
	public int localY(Location location) {
		return location.y - 8 * location.regionY();
	}
        
        /**
	 * Checks if the direction represented by the two delta values can connect
	 * two points together in a single direction.
	 * @param deltaX The difference in X coordinates.
	 * @param deltaY The difference in X coordinates.
	 * @return {@code true} if so, {@code false} if not.
	 */
	public static boolean isConnectable(int deltaX, int deltaY) {
		return Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0 || deltaY == 0;
	}
	
	public boolean inArea(Location entity, Location lowest, Location highest) {
		return (entity.getX() >= lowest.getX() && entity.getX() <= highest.getX() && entity.getY() >= lowest.getY() && entity.getY() <= highest.getY());
	}

	/**
	 * Checks to see if the entity is inside a bank
	 * 
	 * @param location
	 *            The location of the entity
	 * @return If the entity is inside a bank
	 */
	public boolean inBank(final Location location) {
		return withinArea(location, 3090, 3099, 3487, 3500)
				|| withinArea(location, 3089, 3090, 3492, 3498)
				|| withinArea(location, 3248, 3258, 3413, 3428)
				|| withinArea(location, 3179, 3191, 3432, 3448)
				|| withinArea(location, 2944, 2948, 3365, 3374)
				|| withinArea(location, 2942, 2948, 3367, 3374)
				|| withinArea(location, 2944, 2950, 3365, 3370)
				|| withinArea(location, 3008, 3019, 3352, 3359)
				|| withinArea(location, 3017, 3022, 3352, 3357)
				|| withinArea(location, 3203, 3213, 3200, 3237)
				|| withinArea(location, 3212, 3215, 3200, 3235)
				|| withinArea(location, 3215, 3220, 3202, 3235)
				|| withinArea(location, 3220, 3227, 3202, 3229)
				|| withinArea(location, 3227, 3230, 3208, 3226)
				|| withinArea(location, 3226, 3228, 3230, 3211)
				|| withinArea(location, 3227, 3229, 3208, 3226);
	}
}
