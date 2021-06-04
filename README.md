# Java Academy Technical Task

Note: this project is written with openjdk 16 2021-03-16

This is console version of this project. It is shared on GitHub, because GUI version has problems with running on macOS and Linux platforms. If You are using Windows, You may take a look at GUI version of this project.

The GUI version can be found here: https://github.com/Warus15/JavaAcademy_GUI

===========================================================

Table of Contents

1. Project overview
2. Assumptions
3. Used technologies
4. Project structure

---

1. Project overview

This project is technical task for Java Academy. Project contains algorithm to solve given problem. Data for algorithm are stored in text file.

The output has following structure:

[letters from key that appeared in group] [word length] [words in group] [rounded frequency] [exact result]

2. Assumptions

Algorithm uses text file to get data. Data.txt is located in src/task. The data.txt file has following structure:

1st line: Key that algorithm uses (e.g. "logic"). Note that key is meant to be a single word.
2nd...nth lines: Sentences that algorithm will analyze.

3. Used technologies

Project is written in Java 16 (openjdk)

4. Project structure

Project contains Main class and task package.

Package task has all classes that are responsible for solving given problem.

Main: class that creates instance of TechnicalTask class and calls task.execute(), to start algorithm.

task.TechnicalTask: Class that is responsible for loading data file and creating instance of Solution class, to solve problem for each sentence in file.

task.Solution: This class contains whole logic of project. It is responsible for dividing words into groups, gathering results and sending them back to main screen.

task.WordGroup: Class that represents group of words.

task.Record: Represents one record, that is later displayed in console. This class implements Comparable interface, so that the records can be sorted from the lowest frequency to the highest.
