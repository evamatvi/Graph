# Overview

This project implements a Java program that computes all possible paths in an undirected graph with positive edge weights.

Given:

-A source node

-A maximum path length k

Optionally, a maximum total cost c

-The program lists all paths:

-With length ≤ k

-And, if specified, with total cost ≤ c

-The graph is read from a file in DIMACS .gr format.

# Graph Representation

The graph:

-Contains nodes labeled from 1 to n

-Has weighted edges with positive integer costs

-Is undirected

-An adjacency structure is used to store the graph.

-When an edge (u, v) with cost w is read, it is stored in both directions:

u → v
v → u

-This ensures the graph behaves as an undirected graph.

# Path Computation Algorithm

The program uses Depth-First Search (DFS) with backtracking to generate all valid paths.

# Why DFS

DFS is suitable because it:

Explores paths incrementally

Naturally supports recursive path construction

Allows easy control of path length and accumulated cost

Systematically explores all possible path combinations

# How It Works

The algorithm starts from the source node.

It recursively visits each neighboring node.

At each recursive call:

The current path length is checked (≤ k)

The accumulated cost is checked (≤ c, if provided)

If the path satisfies the constraints, it is printed.

The algorithm continues exploring deeper paths until the maximum length is reached.

Backtracking removes the last node before exploring another branch.

Node repetition is allowed, meaning cycles are permitted, as required by the problem statement.

# Project Structure

## Graph.java

Implements the undirected weighted graph and provides methods to:

Add edges

Retrieve neighbors

## Paths.java

Abstract class that implements the DFS-based path generation logic.

## Main.java

Handles:

Command-line argument validation

Graph file parsing

Program execution

Output formatting
