# Binary Tree Bin Packaging algorithm

## Assumptions

1. Customer is satisfied if sits in the same row as the all other members of the group and its window preference is fulfilled (if has one)
2. Group is satisfied if all members are placed in the same row but one of them can be unsatisfied if its window preference is not fulfilled
3. Each group member is not satisfied if one of group member is placed in different row than the other members

## Implementation

1. Implementation is based on binary tree bin packaging algorithm
2. Groups are placed in the plane starting from the largest one
3. Each group in the queue is attempt to fit to the row starting from the front left corner of the plane
4. If group does not fit in any row it is divided into single member groups and placed on the end of the group queue

## Potential improvements

1. Parallelize seats assignment
2. Tests using random generator of group arrangements

## Build & Run

1. Requirements
       
    * sbt 0.13.8
    * java 1.8
    * scala 2.11
    
2. Running app
    
    * Source code can be found in directory `./airplane-bin-packaging`
    * Input file should be placed in directory`./airplane-bin-packaging/src/main/resources/`
    * Input file name should be set in class AirplaneBoardApp in `Source.fromResource("{filename}").getLines().toList`
    * Run `sbt run` in directory `./airplane-bin-packaging` to get the algorithm output
                    