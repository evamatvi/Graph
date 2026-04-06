# Graph Path Finder

This project implements a Java program that reads an undirected weighted graph in DIMACS `.gr` format and lists all paths starting from a given source node.

Each path must satisfy:

- a maximum number of steps
- optionally, a maximum total cost

Cycles are allowed, so nodes may appear more than once in the same path.

## Features

- Reads graphs from DIMACS `.gr` files
- Stores each edge in both directions to model an undirected graph
- Uses depth-first search (DFS) with backtracking
- Lists every valid path from a chosen source node
- Supports an optional maximum accumulated cost

## Requirements

- Java installed
- A graph file in DIMACS `.gr` format

## Compile

```bash
javac Main.java Graph.java Paths.java
```

## Run

```bash
java Main <file> <source> <length> [<cost>]
```

### Parameters

- `<file>`: graph file in DIMACS `.gr` format
- `<source>`: source node
- `<length>`: maximum path length in number of edges
- `[<cost>]`: optional maximum total path cost

### Example

```bash
java Main graph.gr 1 3 10
```

This command prints all paths:

- starting at node `1`
- with length between `1` and `3`
- with total cost less than or equal to `10`

## Input Format

The program expects a DIMACS `.gr` file. It ignores:

- empty lines
- lines starting with `c`
- lines starting with `p`

Edges are read from lines like:

```text
a 1 2 5
```

This means there is an edge between nodes `1` and `2` with cost `5`.

Since the graph is treated as undirected, the program stores the edge in both directions:

- `1 -> 2`
- `2 -> 1`

## Output

The program prints a header followed by each valid path as a list of node identifiers.

Example output:

```text
Paths from 1 of length between 1 and 3, and cost smaller or equal to 10:
[1, 2]
[1, 2, 3]
[1, 4, 2]
```

## How It Works

The path generation is implemented with DFS and backtracking:

1. Start from the source node.
2. Explore each outgoing edge recursively.
3. Keep track of the current number of steps and accumulated cost.
4. Save the path when it satisfies the constraints.
5. Backtrack to continue exploring other possibilities.

This approach is a good fit because it builds paths incrementally and explores all valid combinations in a natural way.

## Project Structure

- `Main.java`: parses arguments, reads the graph file, validates input, and prints the results
- `Graph.java`: stores the graph and provides access to nodes and outgoing edges
- `Paths.java`: implements the DFS-based path enumeration logic

## Notes

- The source node must exist in the graph
- The maximum length must be greater than `0`
- If the input is invalid, the program prints usage instructions
