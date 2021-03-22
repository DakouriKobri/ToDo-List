# ToDo-List

## What is it?
ToDo-List is a command line application with a text-based user interface that allows the end user to create and manage their todo (activities) list.

## What does it do?
It allows the user to
* create a task and assign it a
    * title
    * due date
    * status as done or not
    * project
* display a collection of tasks by
    * due date
    * project  
* edit tasks
* remove tasks
* load and save task list to data file
* quit and restart the application with former state restored

## Getting started
This application can run via jar file. In the root folder, start the application with 
Gradle's gradlew command.

```bash
./gradlew jar
```
This will generate a jar file in *build/libs*, which can then be run in a Java environment.

```bash
java -jar build/libs/ToDo-List-1.0-SNAPSHOT.jar
```

## Usage
From the homepage, as seen in this following screenshot, you can start navigating through the ToDo-List app.

![Homepage image](Images/HomePage.png)

## UML Class Diagram
![ToDo_List UML class diagram](Images/ToDo-List UML class diagram.png)


## Author and Acknowledgement

Dakouri M. Kobri, with the gracious assistance of Nour Alhuda Almajni. This a project carried out as part of my training as software developer on the program 
Software Development Academy co-organized by KTH and Novare Potential in Stockhom, Sweden (Feb 1 - May 14, 2021).

## Project Status

There is room for improvement.



