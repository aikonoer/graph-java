# graph-java

##Distance problem:

Iterate the neighbours to find the next vertex. Do this until the last vertex then accumulate the edges.

##Shortest path problem:

Implemented the Djikstra Shortest Path 

##Possible routes problem:

Scanned vertex in a Depth First Search traversal method.

Solutions made in such a way that it follows the functional paradigm although collections are mutable to reduce complexity.

Assumptions:

1. Only positive distance is considered.
2. Input data format must be as mentioned.


Instructions for compiling and running the program.

1. Extract *.zip file and open a terminal to run the program.
2. Navigate to the extracted file and run command "mvn compile" to compile.
3. Once build is successful, type command with your args:
mvn exec:java -Dexec.mainClass="com.project.App" -Dexec.args="~/input.txt"
4. The program will output a file called "output.txt" to where the input.txt is located
