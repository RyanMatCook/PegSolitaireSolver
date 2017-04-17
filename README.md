# PegSolitaireSolver

An AI Java program that determines whether a given board state of peg solitaire can be solved. 

The program implements the <a href="https://en.wikipedia.org/wiki/Backtracking">Backtracking Algorithm</a> which is an iterative algorithm that performs a depth-first search on a solution space and upon hitting and unsolveable state, backtracks up the depth-first search tree to try alternative routes to a viable solution.

The program outputs "Solved!" or "Unsolvable!" and keeps track of the path to solution. 

The program does not apply any heuristics to optimise performance ad neither does it apply parallelism for speedup.

----------------------------------------------------------------------------------------------------------------------
Running the program:

To run the program, start with running the PegSolitaireSolver.java class i.e. "java /path/to/PegSolitaireSolver.java". 

You can then use the sample input in the text file "Input Data Experiment 1.txt" or "Input Data Experiment 2.txt". Please refer to the "Backtracking Experiment Report.pdf" to get some context on the input data.
