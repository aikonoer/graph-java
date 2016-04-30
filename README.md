# graph-java



1. Distance problem:

Iterate the neighbours to find the next vertex. Do this until the last vertex then accumulate the edges.

2. Shortest path problem:

Implemented the Djikstra Shortest Path 

3. Possible routes problem:

Scanned vertex in a Depth First Search traversal method.

Solutions made in such a way that it follows the functional paradigm. However, collections are mutable to reduce complexity.

------------

Instructions for compiling and running the program.

1. Extract *.zip file and open a terminal to run the program.
2. Navigate to the extracted file and run command "mvn compile" to compile.
3. Once build is successful, type command with your args:
mvn exec:java -Dexec.mainClass="com.project.App" -Dexec.args="~/input.txt"
4. The program will output a file called "output.txt" to where the input.txt is located
