/*******************************************************************************
 * Copyright (c) 2016 German Federal Institute for Risk Assessment (BfR)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Department Biological Safety - BfR
 *******************************************************************************/
package flink_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.shaded.com.google.common.collect.HashMultimap;
import org.apache.flink.shaded.com.google.common.collect.SetMultimap;

public class FlinkTest {

	private static final SetMultimap<String, String> incidentNodes = HashMultimap.create();
	private static final SetMultimap<String, String> outgoingEdges = HashMultimap.create();
	private static final List<String> nodes;
	private static final List<String> edges;

	static {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				FlinkTest.class.getResourceAsStream("/flink_test/graph.csv"), StandardCharsets.UTF_8));
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				String[] edgeDef = line.split(",");

				incidentNodes.put(line, edgeDef[0]);
				incidentNodes.put(line, edgeDef[1]);
				outgoingEdges.put(edgeDef[0], line);
				outgoingEdges.put(edgeDef[1], line);
			}
		} catch (IOException e) {
		}

		nodes = new ArrayList<>(outgoingEdges.keySet());
		edges = new ArrayList<>(incidentNodes.keySet());
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) throws Exception {
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		List<Double> result = env.generateSequence(0, nodes.size() - 1).map(new MapFunction<Long, Double>() {

			@Override
			public Double map(Long index) throws Exception {
				String nodeId = nodes.get(index.intValue());
				Deque<String> nodeQueue = new LinkedList<>();
				Map<String, Integer> visitedNodes = new HashMap<>(nodes.size(), 1.0f);
				Set<String> visitedEdges = new HashSet<>(edges.size(), 1.0f);
				int distanceSum = 0;

				visitedNodes.put(nodeId, 0);
				nodeQueue.addLast(nodeId);

				while (!nodeQueue.isEmpty()) {
					String currentNodeId = nodeQueue.removeFirst();
					int targetNodeDistance = visitedNodes.get(currentNodeId) + 1;

					for (String edgeId : outgoingEdges.get(currentNodeId)) {
						if (visitedEdges.add(edgeId)) {
							for (String targetNodeId : incidentNodes.get(edgeId)) {
								if (!currentNodeId.equals(targetNodeId) && !visitedNodes.containsKey(targetNodeId)) {
									visitedNodes.put(targetNodeId, targetNodeDistance);
									nodeQueue.addLast(targetNodeId);
									distanceSum += targetNodeDistance;
								}
							}
						}
					}
				}

				return 1.0 / (distanceSum + (nodes.size() - visitedNodes.size()) * nodes.size());
			}
		}).collect();

		for (int i = 0; i < nodes.size(); i++) {
			System.out.println(nodes.get(i) + "\t" + result.get(i));
		}
	}
}
