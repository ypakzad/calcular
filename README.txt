Project: Calculator
Author: Y. Pakzad
Description:
This is a simple command line calculator program written in Java programming language.
It takes a string representing an arithmetic expression as shown in the examples below, 
computes and prints the result on the console.

Expression				Result
------------------------------ 
mult(2,3) 					5
mult(2,sub(7,4))			6
add(mult(3,5),div(9,3)) 	5
let(a,5,mult(a,6))			30

Building, Running and Testing the project:
You can build, run and test the program using gradle as shown below:

cd Calculator
To compile classes:     		./gradlew clean compileJava
To build Jar file:     			./gradlew clean build
To run all unit tests:     		./gradlew clean test
To run individual unit test:    ./gradlew  -Dtest.single=CalculatorTest test


To run from command line, build Jar file first using gradle then run the following command:
   
   java -cp build/libs/Calculator.jar ca.pakzad.calculator.Main "add(4,3)"
  




