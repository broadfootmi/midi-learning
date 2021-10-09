# About
This app is an interactive, musical demo of a genetic algorithm. In machine learning, genetic algorithms can find the best neural network for a given problem.

## Definitions
> **Genetic Algorithms** use [Survival of the Fittest](https://simple.wikipedia.org/wiki/Natural_selection) to "evolve" solutions to open-ended computer problems.

> **MIDI** is a music file containing instructions for a computer to play a song.

# Guide
**MIDI creatures** are songs. Their binary DNA describes musical notes. In this genetic algorithm, MIDI creatures evolve from a random collection of notes into a desired tune.

![screenshot](/docs/app_guide.png)
*Screenshot of the Swing user interface*

## 1) Play Tunes
**Click a creature to hear its tune.**

On the left, the current generation of MIDI creatures is sorted by fitness. Fitness is how closely our creature sounds like the desired tune. Fit creatures pass on more genetic bits to the next generation.

Midi creatures' DNA determines their sprite color and string of musical notes.

The first generation's DNA is randomized.

## 2) Simulate
**Choose the number of generations per step and then click Next.**

The maximum generations per step is 20. 

**Tip:** Step a few times in x1 then x20.

## 3) Finish
**Click Finish to fast-forward to the end.**

The end is when at least one creature matches the desired tune.

**Tip:** It's usually ~300 generations for a solution.

# Further Reading
* *AI Techniques for Game Programming* by Mat Buckland, André LaMothe
* [Genetic Algorithm](https://simple.wikipedia.org/wiki/Genetic_algorithm), Simple Wikipedia
* [MIDI](https://en.wikipedia.org/wiki/MIDI#Use_with_computers), Wikipedia

Try the [latest release](https://github.com/broadfootmi/midi-learning/releases/latest).
