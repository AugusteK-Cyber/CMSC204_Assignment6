import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface {

	// Variables
	private Graph graph = new Graph();

	/**
	 * Add a road to the graph
	 * @param town1 the source town
	 * @param town2 the destination town
	 * @param weight distance from the source town to the destination town
	 * @param roadName name of the road
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (town1 == null || town2 == null) {
			return false;
		}
		if (graph.addEdge(getTown(town1), getTown(town2), weight, roadName) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Return the name of the road that connects the two towns
	 * @param town1 the source town name
	 * @param town2 the destination town name
	 */
	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	/**
	 * Return true if the town was successfully added to the graph, false otherwise
	 * @param town town to be added to the graph
	 */
	@Override
	public boolean addTown(String town) {
		return graph.addVertex(new Town(town));
	}

	/**
	 * Return true if the town was already in the graph, false otherwise
	 * @param townName town that might be on the graph
	 */
	@Override
	public boolean containsTown(String townName) {
		return graph.containsVertex(new Town(townName));
	}

	/**
	 * Return true if a road connection exists between the two towns, false otherwise
	 * @param town1 the source town name
	 * @param town2 the destination town name
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(getTown(town1), getTown(town2));
	}

	/**
	 * Return an arraylist of all roads
	 */
	@Override
	public ArrayList<String> allRoads() {
		return graph.edgeSet().stream().map(Road::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Return true if deletion of road connection was successful, false otherwise
	 * @param town1 the source town name
	 * @param town2 the destination town name
	 * @param road the name of the road
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if deletion of the town was successful, false otherwise
	 * @param town the town to delete
	 */
	@Override
	public boolean deleteTown(String town) {
		return graph.removeVertex(getTown(town));
	}

	/**
	 * Return an arraylist of all towns
	 */
	@Override
	public ArrayList<String> allTowns() {
		return graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Return the shortest path from town1 to town2
	 * @param town1 the source town name
	 * @param town2 the destination town name
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(getTown(town1), getTown(town2));
	}

	/**
	 * Return the town if it exist
	 * @param townName name of the town
	 */
	@Override
	public Town getTown(String townName) {
		Set<Town> towns = graph.vertexSet();
		Town town = null;
		for (Town t: towns) {
			if (t.getName().equals(townName)) {
				town = t;
			}
		}
		return town;
	}

	/**
	 * Read from a file
	 * @param selectedFile file to select
	 * @throws FileNotFoundException exception thrown
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		// Variables
		InputStream stream = new FileInputStream(selectedFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		reader.lines().map(str -> str.split(";|\\,")).forEach(array -> 
		{addTown(array[2]); 
		addTown(array[3]); 
		addRoad(array[2], array[3], Integer.parseInt(array[1]),array[0]);});
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
