# Artificial-Intelligence
Artificial Intelligence Repository

Steps :
1.Enter City 1 - This city must be from the input and in the same case structure.
2.Enter City 2 - This city must be from the input and in the same case structure.
3.Enter the search method - Enter number 1 for DFS, 2 fro BFS, and 3 for IDS.
4.You see the output

Input wil be a text file of format 
Arad,Sibiu,149
Zerind,Arad,75
Oradea,Zerind,71   ----> Seperated by comma

Input for a  city can have two words seperated by space.

Output will be of format - Arad ---> Zerind - 75

These Serach techniques will return a path between the two cities if it exists.

Assumptions:

In DFS and IDS the search is affected by the way in which citiies are added to th input file, if a pair of cities comes first in the
input file then they will become the left most node and will be explored first in case of the Depth first Search. 
