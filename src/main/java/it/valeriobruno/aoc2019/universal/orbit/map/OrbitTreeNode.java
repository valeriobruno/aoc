package it.valeriobruno.aoc2019.universal.orbit.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class OrbitTreeNode {

	OrbitTreeNode parent;
	Set<OrbitTreeNode> orbitees;
	final String name;

	public OrbitTreeNode(String name) {
		this.name = name;
		orbitees = new HashSet<>();
	}

	public boolean orbits(OrbitTreeNode spaceElement) {
		return orbitees.contains(spaceElement);
	}

	public boolean addOrbitee(OrbitTreeNode e) {
		if (orbitees.add(e)) {
			e.parent = this;
			return true;
		} else
			return false;
	}

	public int calculateTotalOrbits(int parentValue) {
		int sum = 0;

		for (OrbitTreeNode node : orbitees)
			sum += node.calculateTotalOrbits(parentValue + 1);

		return sum + parentValue;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrbitTreeNode other = (OrbitTreeNode) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
