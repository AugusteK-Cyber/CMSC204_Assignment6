import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {

	// Variables
	private Set<Town> towns;
	private Set<Road> roads;
	private Set<Town> checkedPathsList;
	private Set<Town> uncheckedPathsList;
	private ArrayList<String> paths = new ArrayList<>();

	/**
	 * Constructor
	 */
	public Graph() {
		towns = new HashSet<>();
		roads = new HashSet<>();
		checkedPathsList = new HashSet<>();
		uncheckedPathsList = new HashSet<>(towns);
	}

	/**
	 * Return true if the graph contains the specified town, false otherwise
	 * @param v the town to look for
	 */
	@Override
	public boolean containsVertex(Town v) {
		return towns.contains(v);
	}

	/**
	 * Return the Road from town A to town B
	 * @param sourceVertex town A
	 * @param destinationVertex town B
	 * @return theRoad road from town A to town B
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (containsVertex(sourceVertex) == false || containsVertex(destinationVertex) == false ) {
			throw new IllegalArgumentException("One or both vertices provided are not in the graph");
		}
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException("One or both vertices provided are null");
		}

		Road theRoad = null;
		for (Road r: roads) {
			if (r.contains(sourceVertex) == true && r.contains(destinationVertex) == true) {
				theRoad = r;
			}
		}
		return theRoad;
	}

	/**
	 * Return a set of the roads in the graph
	 * @return roads the set of roads
	 */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	/**
	 * Add and return the Road from a town A to a town B to the Graph
	 * @param sourceVertex town A
	 * @param destinationVertex town B
	 * @return theRoad road from town A to town B
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
			throws IllegalArgumentException, NullPointerException {

		if (containsVertex(sourceVertex) == false || containsVertex(destinationVertex) == false ) {
			throw new IllegalArgumentException("One or both vertices provided are not in the graph");
		}
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException("One or both vertices provided are null");
		}

		sourceVertex.getTowns().add(destinationVertex); // Town A to town B
		destinationVertex.getTowns().add(sourceVertex); // Town B to town A
		Road r = new Road(sourceVertex, destinationVertex, weight, description); // Initialize the road
		roads.add(r); // Add the road to the Road's hashSet		
		return r;
	}

	/**
	 * Return the road to remove
	 * @param sourceVertex town A
	 * @param destinationVertex town B
	 * @param weight distance of the road
	 * @param description name of the road
	 * @return theRoad the road to be removed
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road theRoad = null;
		for (Road r: roads) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex)
					&& weight > -1 && description != null) {
				theRoad = r;
			}
		}
		sourceVertex.getTowns().remove(destinationVertex); // Town A to town B
		destinationVertex.getTowns().remove(sourceVertex); // Town B to town A
		roads.remove(theRoad);
		return theRoad;
	}

	/**
	 * Return true if the road between town A and town B exist in the graph, false otherwise
	 * @param sourceVertex town A
	 * @param destinationVertex town B
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		return getEdge(sourceVertex, destinationVertex) != null? true: false;
	}

	/**
	 * Return the roads to the adjacent vertices
	 * @param vertex the town to which some roads are linked
	 * @return theRoads the corresponding roads (edges) linked the the specified town 
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null) {
			throw new NullPointerException("The town you are looking for does not exist (null)");
		}
		Set<Road> theRoads = new HashSet<>();
		for (Road r: roads) {
			if (r.contains(vertex)) {
				theRoads.add(r);
			}
		}
		return theRoads;
	}

	/**
	 * Add a vertex (town) to the graph
	 * @param v the vertex (town) to add to the graph
	 * @return true if the town was successfully added to the graph, false otherwise
	 */
	@Override
	public boolean addVertex(Town v) throws NullPointerException {
		if (v == null) {
			throw new NullPointerException("The town (vertex) you want to add is null");
		}
		if (towns.contains(v) == false) {
			return towns.add(v);
		}
		return false;
	}

	/**
	 * Return true if the town is removed, false otherwise
	 * @param v the town to be removed
	 */
	@Override
	public boolean removeVertex(Town v) {
		if (v == null) {
			return false;
		}
		return towns.remove(v);
	}

	/**
	 * Return a set of the towns in the graph
	 * @return towns the set of towns 
	 */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	/**
	 * Use the Dijkstra algorithm to find the shortest path from the specified town
	 * @param sourceVertex the town to reach
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Boolean isFound = false;
		while (isFound == false && uncheckedPathsList.isEmpty() == false) {
			isFound = true;
			Town nextTown = null;
			int w = Integer.MAX_VALUE;

			for (Town town: checkedPathsList) {
				Set<Town> checked = town.getTowns();
				Set<Town> unchecked = new HashSet<>();
				for (Town t: checked) {
					if (uncheckedPathsList.contains(t) == false) {
						continue;
					}
					unchecked.add(t);
				}
				for (Town t: unchecked) {
					int weight = t.equals(sourceVertex)? 0: town.getWeight() + getEdge(town, t).weight;
					if (weight < w) {
						w = weight;
						nextTown = t;
						t.setPreviousTown(town);
					}
				}
			}

			if (nextTown != null) {
				isFound = false;
				nextTown.setWeight(w);
				checkedPathsList.add(nextTown);
				paths.add(nextTown.getName());
				uncheckedPathsList.remove(nextTown);
			}
		}

	}

	/**
	 * Represent the paths as a String
	 * @param paths arraylist of paths
	 * @param sourceVertex town A where the path starts
	 * @param destinationVertex town B where the path ends
	 */
	private void pathsPattern(ArrayList<String> paths, Town sourceVertex, Town destinationVertex) {
		try {		
			StringBuilder pathsStr = new StringBuilder();
			Road r = getEdge(destinationVertex.getPreviousTown(), destinationVertex);
			pathsStr.append(r.toString());
			paths.add(pathsStr.toString());

			while (destinationVertex.getPreviousTown().equals(sourceVertex) == false) {
				pathsPattern(paths, sourceVertex, destinationVertex.getPreviousTown());
			}
		} 
		catch(Exception e) {
			System.out.println("Cleaning paths... ");
			paths.clear();
		}
	}

	/**
	 * Find the shortest path from town A to town B
	 * @param sourceVertex town A where the path starts
	 * @param destinationVertex town B where the path ends
	 * @return paths an arraylist of the shortest paths
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		checkedPathsList = new HashSet<Town>();
		uncheckedPathsList = new HashSet<Town>(towns);
		checkedPathsList.add(sourceVertex); // Add it to the checked towns list
		uncheckedPathsList.remove(sourceVertex); // Remove it from the unchecked towns list

		for (Town town: towns) {
			town.setWeight(Integer.MAX_VALUE);
			town.setPreviousTown(null);
		}

		sourceVertex.setWeight(0); // Set source vertex (town) to 0
		dijkstraShortestPath(sourceVertex);
		StringBuilder shortPaths = new StringBuilder();
		Road r = getEdge(destinationVertex.getPreviousTown(), destinationVertex);
		shortPaths.append(r.toString());
		paths.add(shortPaths.toString());
		if (destinationVertex.getPreviousTown().equals(sourceVertex) == false) {
			pathsPattern(paths, sourceVertex, destinationVertex.getPreviousTown());
		}
		Collections.reverse(paths);
		return paths;
	}

}