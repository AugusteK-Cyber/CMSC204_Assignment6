
/**
 * The class Road that can represent the edges of a Graph of Towns. The class must implement Comparable. 
 * The class stores references to the two vertices(Town end-points), the distance between vertices, and a 
 * name, and the traditional methods (constructors, getters/setters, toString, etc.), and a compareTo, 
 * which compares two Road objects. Since this is a undirected graph, an edge from A to B is equal to an 
 * edge from B to A.
 * @author Auguste Kiendrebeogo
 *
 */

public class Road implements Comparable<Road> {
	
	// Variables
	private Town source, destination;
	protected int weight;
	private String roadName;

	/**
	 * Constructor
	 * @param source One town on the road
	 * @param destination Another town on the road
	 * @param weight weight of the edge, i.e. distance from one town to the other
	 * @param roadName name of the road
	 */
	public Road(Town source, Town destination, int weight, String roadName) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.roadName = roadName;
	}
	
	/**
	 * Constructor with weight preset at 1
	 * @param source One town on the road
	 * @param destination Another town on the road
	 * @param roadName name of the road
	 */
	public Road(Town source, Town destination, String roadName) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.roadName = roadName;
	}

	/**
	 * Returns the road name
	 * @return name the road name
	 */
	public String getName() {
		return roadName;
	}

	/**
	 * Returns the first town on the road
	 * @return source the first town on the road
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * The source of the vertices
	 * @param source source of the vertices
	 */
	public void setSource(Town source) {
		this.source = source;
	}

	/**
	 * Returns the second town on the road
	 * @return destination the second town on the road
	 */
	public Town getDestination() {
		return destination;
	}

	/**
	 * The destination of the vertices
	 * @param destination destination of the vertices
	 */
	public void setDestination(Town destination) {
		this.destination = destination;
	}

	/**
	 * Returns the distance of the road
	 * @return weight the distance of the road
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Distance between vertices
	 * @param weight the distance between vertices
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Set the name of the edge
	 * @param roadName name of the edge
	 */
	public void setName(String roadName) {
		this.roadName = roadName;
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town vertex of the graph
	 * @return true only if the edge is connected to the given town, false otherwise
	 */
	public boolean contains(Town town) {
		return source.getName().equals(town.getName()) 
				|| destination.getName().equals(town.getName());
	}
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road. Remember that
	 * a road that goes from point A to point B is the same as a road that goes from point B to point A.
	 * @param o road object to compare it to
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (!(o instanceof Road)) {
			return false;
		}
		
		if (o == this) {
			return true;
		}
		
		Road r = (Road) o;
		return source.equals(r.source) && destination.equals(r.destination) 
				|| source.equals(r.destination) && destination.equals(r.source);
	}

	/**
	 * Comparing two roads. 0 if the road names are the same, a positive or negative
	 * number if the road names are not the same
	 * @param o road object to compare it to
	 */
	@Override
	public int compareTo(Road o) {		
		return roadName.compareTo(o.roadName);
	}
	
	/**
	 * Return a String representation of the form "town A via road R to town B distance between the towns"
	 * @return form "town A via road R to town B distance between the towns"
	 */
	@Override
	public String toString() {
		return destination.getPreviousTown().getName() + " via " + getName() + " to " 
				+ destination.getName() + " " + getWeight() + " mi";	
	}

}
