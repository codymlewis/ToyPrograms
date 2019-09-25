import functools

import igraph

'''
Graph related classes and functions

Author: Cody Lewis
Date: 2019-08-30
'''

class Node:
    '''A node within to be contained in a graph.'''
    def __init__(self, id_no):
        self.__edges = []
        self.__edge_names = dict()
        self.__id = id_no
        self.name = f"{id_no}"

    def connect(self, other, distance):
        '''
        Connect this node to another by an edge with the specified distance
        '''
        self.add_edge(Edge(self, other, distance))
        other.add_edge(Edge(other, self, distance))

    def add_edge(self, edge):
        '''Add an edge to this.'''
        if edge.name not in self.__edge_names:
            self.__edges.append(edge)
            self.__edge_names[edge.name] = len(self.__edges) - 1

    # Query functions

    def get_id(self):
        return self.__id

    def get_edge(self, i):
        return self.__edges[i]

    def find_edge(self, name):
        return self.__edge_names.get(name)

    def get_edges(self):
        return self.__edges

    def edge_cardinality(self):
        return len(self.__edges)

    def get_connections(self):
        connections = []
        for edge in self.__edges:
            connections.append(
                edge.from_node if edge.from_node != self else edge.to_node
            )
        return set(connections)

    # Mutator functions

    def disconnect(self, node_id):
        edge_index = self.find_edge(f"{self.__id}{node_id}")
        if not edge_index:
            edge_index = self.find_edge(f"{node_id}{self.__id}")
        if edge_index:
            self.rm_edge(edge_index)

    def rm_edge(self, i):
        edge = self.get_edge(i)
        other = edge.to_node
        other.del_edge(other.find_edge(edge.name))
        self.del_edge(i)

    def del_edge(self, i):
        del self.__edge_names[self.__edges[i].name]
        del self.__edges[i]


@functools.total_ordering
class Edge:
    '''Edges that connect to nodes, and have a weight/distance.'''
    def __init__(self, from_node, to_node, distance):
        self.from_node = from_node
        self.to_node = to_node
        self.distance = distance
        # Give nodes names such that both directions are the same
        if from_node.get_id() < to_node.get_id():
            self.name = f"{from_node.get_id()}{to_node.get_id()}{distance}"
        else:
            self.name = f"{to_node.get_id()}{from_node.get_id()}{distance}"

    # Equality/inequality overloads

    def __gt__(self, other):
        return self.distance > other.distance

    def __lt__(self, other):
        return self.distance < other.distance

    def __eq__(self, other):
        return self.distance == other.distance


class Graph:
    '''A graph, composed of a list of Nodes.'''
    def __init__(self, cardinality=0):
        self.__vertices = [Node(i) for i in range(cardinality)]

    def get_vertex(self, i):
        '''Get the ith Node in this graph.'''
        return self.__vertices[i]

    def connect(self, i, j, distance):
        '''Connect node i to node j with distance between eachother.'''
        self.__vertices[i].connect(self.__vertices[j], distance)

    def add_vertex(self, vertex):
        '''Add a Node to this graph.'''
        self.__vertices.append(vertex)

    def set_vertex(self, i, vertex):
        '''Replace the ith vertex with a new one.'''
        self.__vertices[i] = vertex

    def cardinality(self):
        '''Get the number of Nodes contained in this.'''
        return len(self.__vertices)

    def create_diagram(self, name, verbose=False, node_size=1):
        '''Create the graphml diagram.'''
        graph = igraph.Graph()
        colors = ["red", "blue", "green", "pink", "purple", "yellow", "magenta", "aqua"]
        color_index = 0
        for vertex in self.__vertices:
            graph.add_vertex(
                f"{vertex.name}",
                label=f"{vertex.name}",
                color=colors[color_index],
                size=node_size * vertex.edge_cardinality() + 1
            )
            color_index = (color_index + 1) % len(colors)
        added_edges = set()

        for vertex in self.__vertices:
            for edge in vertex.get_edges():
                if edge.name not in added_edges:
                    other_vertex = edge.from_node if edge.from_node != vertex else edge.to_node
                    graph.add_edge(
                        f"{vertex.name}",
                        f"{other_vertex.name}",
                        width=50**(1 / (1 + edge.distance))
                    )
                    added_edges.add(edge.name)
        igraph.plot(graph, layout=graph.layout("fr"), bbox=(2048, 4096)).save(f"{name}.png")
        if verbose:
            print(f"Graph written to {name}.png")


def create_graph(dist_matrix, ids):
    '''Create a graph given the matrix of distances between nodes.'''
    result = Graph(len(dist_matrix))
    for i in range(0, len(dist_matrix)):
        result.get_vertex(i).name = ids[i]
        for j in range(0, len(dist_matrix[i])):
            if i != j:
                result.get_vertex(i).connect(
                    result.get_vertex(j), dist_matrix[i][j]
                )
    return result
