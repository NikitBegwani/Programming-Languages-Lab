ground_truth(north, left, west).			/**Ground truth cases**/
ground_truth(north, right, east).
ground_truth(south, left, east).
ground_truth(south, right, west).
ground_truth(east, left, north).
ground_truth(east, right, south).
ground_truth(west, left, south).
ground_truth(west, right, north).

/**All possible movements available**/

movement(Start_X, Start_Y, north, Start_X, Y_Current) :- Y_Current is Start_Y + 1.	
movement(Start_X, Start_Y, south, Start_X, Y_Current) :- Y_Current is Start_Y - 1.
movement(Start_X, Start_Y, east, X_Current, Start_Y) :- X_Current is Start_X + 1.
movement(Start_X, Start_Y, west, X_Current, Start_Y) :- X_Current is Start_X - 1.

status_util(Start_X, Start_Y, Direction, [], (Start_X,Start_Y), Direction).	/**Utility functions base case when list becomes empty**/

status_util(Start_X, Start_Y, Direction, [move|Tail], Position,  Orientation) :-	/**Utility function when move is present in head of list**/ 
    movement(Start_X, Start_Y, Direction, Current_X, Current_Y), status_util(Current_X, Current_Y, Direction, Tail, Position,  Orientation).

status_util(Start_X, Start_Y, Direction, [H|Tail], Position, Orientation) :- /**Utility function when left 'or' right is present in head of list**/
    ground_truth(Direction, H,  Current_Direction), status_util(Start_X, Start_Y,  Current_Direction, Tail, Position, Orientation).

status(List, Position, Orientation) :- 	/**BEGIN**/
    status_util(0, 0, east, List, Position, Orientation).
