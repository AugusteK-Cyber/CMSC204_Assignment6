import java.util.HashSet;
import java.util.Set;

/**
 * Represents an town as a node of a graph. The Town class holds the name of the town and a list of adjacent towns,
 * and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.). 
 * It will implement the Comparable interface These are the minimum methods that are needed. Please feel free to add 
 * as many methods as you need.
 * @author Auguste Kiendrebeogo
 *
 */

public class Town implements Comparable<Town> {

	// Variables
	private String townName;
	private int weight;
	private Town previousTown;
	private Set<Town> towns;
	
	/**
	 * Constructor
	 * @param townName the name of the town
	 */
	public Town(String townName) {
		this.townName = townName;
		weight = Integer.MAX_VALUE;
		previousTown = null;
		towns = new HashSet<>();
	}
	
	/**
	 * Copy constructor
	 * @param templateTown an instance of Town
	 */
	public Town (Town templateTown) {
		this.townName = templateTown.townName;
		this.weight = templateTown.weight;
		this.previousTown = templateTown.previousTown;
		this.towns = templateTown.towns;
	}
	
	public void reset() {
		this.weight = Integer.MAX_VALUE;
		this.previousTown = null;
	}

	/**
	 * Get town name
	 * @return townName name of the town
	 */
	public String getTownName() {
		return townName;
	}

	/**
	 * Set town name
	 * @param townName name of the town
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}
	
	/**
	 * Get distance between towns
	 * @return weight distance between towns
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Set distance between towns
	 * @param weight distance between towns
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Get previous town on the graph
	 * @return previousTown previous town on the graph
	 */
	public Town getPreviousTown() {
		return previousTown;
	}

	/**
	 * Set previous town on the graph
	 * @param previousTown previous town on the graph
	 */
	public void setPreviousTown(Town previousTown) {
		this.previousTown = previousTown;
	}

	/**
	 * Get the set of towns on the graph
	 * @return towns the set of towns on the graph
	 */
	public Set<Town> getTowns() {
		return towns;
	}

	/**
	 * Set the set of towns on the graph
	 * @param towns the set of towns on the graph
	 */
	public void setTowns(Set<Town> towns) {
		this.towns = towns;
	}

	/**
	 * Returns the town's name
	 * @return townName the town's name
	 */
	public String getName() {
		return townName;
	}
	
	/**
	 * Returns true if the town names are equal, false if not
	 * 
	 */
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		
		if (!(o instanceof Town)) {
			return false;
		}
		
		if (o == this) {
			return true;
		}
		
		Town t = (Town) o;
		return townName.equals(t.townName);
	}

	/**
	 * Returns 0 if names are equal, a positive or negative number if the names are not equal
	 * @param o Town object to compare it to
	 */
	@Override
	public int compareTo(Town o) {
		return townName.compareTo(o.townName);
	}
	
	/**
	 * Returns the hashcode for the name of the town
	 */
	public int hashCode() {
		return townName.hashCode();
	}
	
	/**
	 * Return the town's name
	 */
	@Override
	public String toString() {
		return townName;	
	}

}
