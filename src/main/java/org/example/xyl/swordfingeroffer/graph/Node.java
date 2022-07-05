package org.example.xyl.swordfingeroffer.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
	public int value;
	public int in;
	public int out;
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Node)) {
			return false;
		}
		Node node = (Node) o;
		return value == node.value && in == node.in && out == node.out && Objects.equals(nexts, node.nexts) && Objects.equals(edges, node.edges);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, in, out, nexts, edges);
	}
}
