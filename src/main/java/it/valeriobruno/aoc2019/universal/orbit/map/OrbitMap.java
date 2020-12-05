package it.valeriobruno.aoc2019.universal.orbit.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrbitMap {

	OrbitTreeNode comElement;
	HashMap<String, OrbitTreeNode> spaceElements;

	public OrbitMap() {
		comElement = new OrbitTreeNode("COM");
		spaceElements = new HashMap<>();
		spaceElements.put("COM", comElement);
	}

	public void addOrbit(String center, String orbitee) {
		OrbitTreeNode centerElement = addElementIfNotExist(center);
		OrbitTreeNode orbiteeElement = addElementIfNotExist(orbitee);

		centerElement.addOrbitee(orbiteeElement);
	}

	private OrbitTreeNode addElementIfNotExist(String center) {

		OrbitTreeNode centerElement;
		if (spaceElements.containsKey(center)) {
			centerElement = spaceElements.get(center);
		} else {
			centerElement = new OrbitTreeNode(center);
			spaceElements.put(center, centerElement);
		}
		return centerElement;
	}

	public int calculateTotalObits() {
		return comElement.calculateTotalOrbits(0);
	}

	public int minTransfers(String src, String dest) {
		OrbitTreeNode srcNode = spaceElements.get(src);
		OrbitTreeNode destNode = spaceElements.get(dest);

		HashMap<OrbitTreeNode, Integer> stepsRequired = new HashMap<>();
//		LinkedList<OrbitTreeNode> nodesToVisit = new LinkedList<OrbitTreeNode>();

		stepsRequired.put(srcNode, 0);

		while (!stepsRequired.containsKey(destNode)) {
			
			HashMap<OrbitTreeNode, Integer> visitedNodes = new HashMap<>(stepsRequired);
			
			for (OrbitTreeNode node : visitedNodes.keySet()) {
				for (OrbitTreeNode child : node.orbitees) {
					if(!visitedNodes.containsKey(child))
					{
						stepsRequired.put(child, visitedNodes.get(node)+1);
					}
					
				}
				if(node.parent != null && !visitedNodes.containsKey(node.parent))
				{
					stepsRequired.put(node.parent, visitedNodes.get(node)+1);
				}
				
			}
		}
		

		return stepsRequired.get(destNode)-2;
	}

	public static void main(String[] args) throws IOException {

		OrbitMap map = new OrbitMap();

		List<String> lines = Files.readAllLines(Paths.get("./input-orbit.txt"));

		for (String line : lines) {
			String[] mapElement = line.split("\\)");
			map.addOrbit(mapElement[0], mapElement[1]);
		}

		System.out.println(map.calculateTotalObits());
		
		System.out.println(map.minTransfers("YOU", "SAN"));
	}

}
