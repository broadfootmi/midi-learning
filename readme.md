Check out the [latest release](https://github.com/broadfootmi/midi-learning/releases/latest)!

# About

This app is an interactive, musical demo of the Genetic Algorithm. While this use case is simple and fun, you will find genetic algorithms to be crucial for optimizing open-ended systems. One example is neural networks in the exciting field of Machine Learning. 

Play around with it and see if you can recognize the tune! Then you might be interested in further reading; see recommendations at the bottom. 

## What is a Genetic Algorithm?

It is a way to use [Survival of the Fittest](https://simple.wikipedia.org/wiki/Natural_selection) to solve computer problems.

## What is MIDI?

It is a special music file which is not an actual song, but instructions for a *MIDI Synthesizer* to play a song. Pretty useful for programming the music on the fly!

# Guide

MIDI Creatures are little songs. They evolve from a random collection of notes into the desired tune.

![screenshot](/docs/app_guide.png)

## 1) Play Tunes

**Click on each creature to hear its tune.**

The current generation of MIDI Creatures is shown on the left, sorted by fitness. Fitness is how closely the creature sounds like our desired tune. The more musical notes (shown beside the creature) which match, the more *fit* the creature is, and the more of its genetic bits will be passed down to the next generation!

The first generation is completely randomized. 

**Notice the colors.**

A creature's *genes* are musical notes in binary form (bits). Color is determined by those same genes, so you can tell similar creatures at a glance. Do you notice any patterns?

## 2) Step Simulation

**Choose the number of generations per step and then hit Next.**

Max per step is 20. 

Tip: Step a few times in x1 then x20.

## 3) Step Until Finished

Fast-forward to the end. The end is when at least one creature matches the desired tune.

Tip: It's usually ~300 generations for a solution.

# Further Reading
* *AI Techniques for Game Programming* by Mat Buckland, André LaMothe
* [Genetic Algorithm](https://simple.wikipedia.org/wiki/Genetic_algorithm), Simple Wikipedia
* [MIDI](https://en.wikipedia.org/wiki/MIDI#Use_with_computers), Wikipedia
