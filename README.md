# Piece's Tour

### Assignment Statement:

The assignment is to write a program that finds at least one
tour for a chess piece on a 10-by-10 chessboard. A tour is
a path for a piece to visit all tiles on the board, following a
set of rules for movement. Assume the piece can start in
any tile.
The four rules of movement for the piece are:
a) The piece can move 3 spaces either North, East, South,
or West.
b) The piece can move 2 spaces diagonally: Northeast,
Southeast, Southwest, or Northwest.
c) Each space can only be visited once.

#### Warnsdorff's Rule: 
The solution to the challenge is the use of the Warnsdorff's Rule. The Warnsdorff's Rule is a heuristic algorithm that 
states a movement is done to the next cell with fewest onward moves, following the steps:

1. It starts with the initial cell, marked with number 1.
2. From the starting position, collect all its neighboring cells.
3. Select any cell with fewer unvisited neighbors.
5. Mark the selected cell as visited, then repeat the process until all the cells has been marked.


## Getting Started

Instructions to run a local copy for development and tests purposes. 
Deployment notes show how to run the application as a standalone.

### Prerequisites

All dependencies are mentioned in build.gradle

```
Install java 13
Install gradle 6.1
```

### Installing

To build the project

```
gradle clean build
```

And to Run the application. The arguments states the initial line and the initial column, respectively.

```
gradle run --args="INITIAL_LINE INITIAL_COLUMN"
```
Sample Output
```
ricardo@ricardo-Dell-System-XPS-L502X /opt/dev/projects/PieceTour (master) $ gradle run --args="3 2"

> Task :run
Feb 16, 2020 12:28:25 AM piecetour.board.ChessBoard printBoard
INFO: 
 59 05 16 58 06 17 45 07 18 46
 02 34 61 03 33 23 20 32 24 21
 15 73 66 29 86 67 28 87 44 08
 60 04 01 57 62 31 48 22 19 47
 99 35 81 74 65 82 85 68 25 88
 14 72 63 30 91 70 27 90 43 09
 39 75 100 56 84 95 49 83 94 52
 98 36 80 71 64 79 92 69 26 89
 13 55 40 12 54 41 11 53 42 10
 38 76 97 37 77 96 50 78 93 51


BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 executed

```
## Running the tests

To run the tests

```
gradle test
```

The test report will be available in *{projectRoot}/build/reports/tests/test/index.html*

## Development

Execute below command to generate a idea Intellij project:

```
gradle idea
```


## Deployment

Execute below command to get an executable jar file under project *{projectRoot}/build/libs* folder

```
gradle clean build
```

Execute below command to execute as java application without scala dependencies from */build/libs* location 

```
java -jar PieceTour.jar 3 2
```

Sample output

```
ricardo@ricardo-Dell-System-XPS-L502X /opt/dev/projects/PieceTour/build/libs (master) $ java -jar PieceTour.jar 3 2
fev 16, 2020 12:32:16 AM piecetour.board.ChessBoard printBoard
INFO: 
 44 13 24 43 14 25 67 15 26 68
 02 76 60 03 77 74 04 78 73 05
 23 42 45 92 66 46 95 69 47 16
 61 12 01 75 59 100 72 56 27 79
 33 91 65 41 88 93 48 81 94 06
 22 39 58 99 71 57 96 70 50 17
 62 11 34 90 54 35 89 55 28 80
 32 86 64 40 87 98 49 82 97 07
 21 38 53 20 37 52 19 36 51 18
 63 10 31 85 09 30 84 08 29 83

```
## References

* [Knight's Tour](https://en.wikipedia.org/wiki/Knight%27s_tour) - About the challenge

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Ricardo Pereira Ramalho** - *Initial work* - [RicardoPRamalho](https://github.com/RicardoPRamalho)
