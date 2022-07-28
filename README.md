# cmpt276Ass1

This project shows lens focal length, aperture, and distance data through UI.

## Description

The model package is the part of the application that manages the data and most of the logic of the application. user interface (UI) classes in the UI package will call classes from this package, but the model package classes will not call UI classes.

## Environment

* Java SE JDK
* IntelliJ Comminity Edition

##  Testing

* Write a JUnit 5 test class for your depth of field calculator class which tests all of its methods.  Test at least three different lens and camera setting combinations.

## Expected output

Hyperfocal distance 

* With a given lens and camera settings, the hyperfocal distance is the distance from the camera  beyond which all objects will seem in focus. Hyper focal distance:

* ```java
   [mm] = (lens focal length [mm])^2 /  (selected aperture * camera’s circle of confusion [mm])
       
  ```

Near focal point

* Near focal point is distance from the camera to the nearest point which will seem in focus.

* ```java
  Near Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm]) 
   / (hyper focal point [mm] + (distance to subject [mm] – lens focal length [mm]))
  ```

Far focal point

* Far focal point is distance from the camera to the farthest point which will seem in focus.

* ``` java
  Far Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm]) 
   / (hyper focal point [mm] – (distance to subject [mm] – lens focal length [mm]))
  ```

## Author

@ Zla182