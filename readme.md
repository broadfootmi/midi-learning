Check out the [latest release](https://github.com/broadfootmi/midi-learning/releases/latest)!

# About

This app is an interactive, musical demo of the Genetic Algorithm. While this use case is simple and fun, genetic algorithms are crucial for optimizing open-ended systems. One example is neural networks in the field of Machine Learning. 

Play around with it and see if you can recognize the tune! Then you might be interested in further reading; see recommendations at the bottom. 

## Definitions

> A **Genetic Algorithm** uses [Survival of the Fittest](https://simple.wikipedia.org/wiki/Natural_selection) to "evolve" solutions to computer problems.

> **MIDI** is a special music file which contains instructions for a computer to play a song. It was used for programming music dynamically.

# Guide

MIDI Creatures are short songs. They evolve from a random collection of notes into the desired tune. Does their icon resemble anything to you?

![screenshot](/docs/app_guide.png)

## 1) Play Tunes

**Click on each creature to hear its tune.**

The current generation of MIDI Creatures is shown on the left, sorted by fitness. Fitness is how closely the creature sounds like our desired tune. The more musical notes (shown beside the creature) which match, the more *fit* the creature is, and the more of its genetic bits will be passed down to the next generation!

The first generation is completely randomized. 

**Notice the colors.**

A creature's *genes* are musical notes in binary form (bits). Color is determined by those same genes, so you can identify similar creatures. Do you notice any patterns?

## 2) Simulate

**Choose the number of generations per step and then click Next.**

The maximum generations per step is 20. 

Tip: Step a few times in x1 then x20.

## 3) Finish

Fast-forward to the end. The end is when at least one creature matches the desired tune.

Tip: It's usually ~300 generations for a solution.

# Further Reading
* *AI Techniques for Game Programming* by Mat Buckland, André LaMothe
* [Genetic Algorithm](https://simple.wikipedia.org/wiki/Genetic_algorithm), Simple Wikipedia
* [MIDI](https://en.wikipedia.org/wiki/MIDI#Use_with_computers), Wikipedia
