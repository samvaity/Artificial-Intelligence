# Artificial-Intelligence
Artificial Intelligence Repository

Steps :
1.Enter City 1 - This city must be from the input and in the same case structure.
2.Enter City 2 - This city must be from the input and in the same case structure.
3.Enter the search method - Enter number 1 for DFS, 2 fro BFS, and 3 for IDS.
4.Output will be of format - Arad ---> Zerind - 75

Name of a single city can have two words seperated by space.

Input wil be a text file of format 
Arad,Sibiu,149
Zerind,Arad,75
Oradea,Zerind,71  
Rimnicu Vilcea, Sibiu 80 ----> Seperated by comma

Assumptions:

These Serach techniques will return a path between the two cities if it exists.

In DFS and IDS the search is affected by the way in which citiies are added to th input file, if a pair of cities comes first in the
input file then they will become the left most node and will be explored first in case of the Depth first Search. 
