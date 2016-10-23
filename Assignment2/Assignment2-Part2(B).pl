movement((X_dest,Y_dest), _, (X_dest,Y_dest)):-!.

movement((X_current, Y_current), Orientation, (X_dest,Y_dest)) :- 
    
    member(Orientation, [north]), Y_dest > Y_current ->
	Y_temp is Y_current + 1,
    write("move  "),movement((X_current,Y_temp), Orientation , (X_dest,Y_dest));
	
    member(Orientation, [south]), Y_dest< Y_current ->
    Y_temp is Y_current - 1,
    write("move  "),movement((X_current,Y_temp), Orientation , (X_dest,Y_dest));
	
    member(Orientation, [east]), X_dest > X_current ->
	X_temp is X_current + 1,
	write("move  "),movement((X_temp,Y_current), Orientation , (X_dest,Y_dest));
	
    member(Orientation, [west]), X_dest < X_current ->
    X_temp is X_current - 1,
    write("move  "),movement((X_temp,Y_current), Orientation , (X_dest,Y_dest));
	

    member(Orientation, [north]), Y_dest =< Y_current , X_dest =< X_current ->
    write("left  "),movement((X_current,Y_current), west , (X_dest,Y_dest));
	
    member(Orientation, [north]), Y_dest =< Y_current  ,  X_dest >= X_current ->
    write("right  "),movement((X_current,Y_current), east , (X_dest,Y_dest));
	
    member(Orientation, [south]), Y_dest >= Y_current , X_dest =< X_current->
    write("right  "),movement((X_current,Y_current), west , (X_dest,Y_dest));
	
    member(Orientation, [south]), Y_dest >= Y_current , X_dest >= X_current->
    write("left  "),movement((X_current,Y_current), east , (X_dest,Y_dest));
    

    member(Orientation, [east]), X_dest =< X_current , Y_dest =< Y_current ->
    write("right  "),movement((X_current,Y_current), south , (X_dest,Y_dest));
	
    member(Orientation, [east]), X_dest =< X_current , Y_dest >= Y_current ->
	write("left  "),movement((X_current,Y_current), north , (X_dest,Y_dest));
	
    member(Orientation, [west]), X_dest >= X_current , Y_dest =< Y_current->
    write("left  "),movement((X_current,Y_current), south , (X_dest,Y_dest));
	
    member(Orientation, [west]), X_dest >= X_current ,Y_dest >= Y_current->
    write("right  "),movement((X_current,Y_current), north , (X_dest,Y_dest)).