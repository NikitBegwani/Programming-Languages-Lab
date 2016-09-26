successor(M,R):- append(M,[x],R).	/**find the next number**/

plus(M,N,R):- append(M,N,R).	/**find the sum by appending both the list**/
	
minus(M,N,R):- length(N,X),		/**find the differnece between both the list, with greater list as first input**/
			   length(P,X),
			   append(P,R,M).

first(L, A) :-				/**Used by multiplication to halve the list**/
    append(A, B, L),
    length(A, N),
    length(B, N).
	
multiply(M,N,A):- findall([X,Y],					/**Cartesian Product**/
				  (member(X,M),member(Y,N)),R),
				  flatten(R,X),						/**flatten the list of list to single list**/
				  first(X,A).						/**take halve the elements because cartesian product gives double number of elements**/

part([],_).				/**base case for splitting**/
part(L,N):-											/**Recursive call for splitting list in chunks of length N**/
					length(DL,N),					/**Initialising DL with length N**/
					append(DL,LTAIL,L),				/**Splitting list into N and length(list)-N**/
					part(LTAIL,N).					/**Recursively splitting the remaining list**/

divide(M,N):-length(N,R),							/**Divide Function**/
			part(M,R).
