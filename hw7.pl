% 1-a: Write the rules for a predicate isSet(S), which succeeds if the list S is a set.
isSet([]). % empty set is a set.
isSet([_]). % single element set is a set.
isSet([H|T]):- not(member(H,T)), isSet(T). % if H isn't a member of T, then it only occurs once. Continue through T.
% 1-b: Write the rules for a predicate subset(A,S), which succeeds if the set A is a subset of the set S.
subSet([],[]). % empty set is subset of itself.
subSet([],[_|_]). % empty set is subset of any set.
subSet([H|T],S):- member(H,S), subSet(T,S). % is subset if the head is a member of the other parent set. Repeat through tail.
% 1-c: Write the rules for a predicate union(A,B,C), which succeeds if the union of sets A and B is the set C.
union([],C,C). % set unioned with empty set is that set.
union([H|T],S,C):- member(H,S), !, union(T,S,C). % if H of first set is member of second set, and union of T of first set and S is C
union([H|T],S,[H|Ct]):- union(T,S,Ct).


% 1-d: Write the rules for a predicate intersection(A,B,C), which succeeds if the intersection of sets A and B is the set C.
intersection([],_,[]). % intersection of a set and an empty set is empty
intersection(_,[],[]). % intersection of a set and an empty set is empty
intersection(S,U,[H|T]):- member(H,S), member(H,U), intersection(S,U,T). /* NOT WORKING YETTTTTT */


% 2: Write the rules for a predicate tally(E,L,N), which succeeds if N is the number of occurrences of element E in list L.
tally(_,[],0). % empty list will not have occurrences of anything.
tally(E,[E|T],N):- tally(E,T,X), N is 1+X. % if E matches the head of the list, increment N and go on down the rest.
tally(E,[_|T],N):- tally(E,T,N). % if it doesn't match, just skip that one.
% 3: Write the rules for a predicate subst(X,Y,L,L1), which succeeds if list L1 is identical to list L except that all occurrences
%    of X in L are replaced with Y in L1.
subst(_,_,[],[]). % empty list is empty list with any substitution.
subst(X,X,Y,Y). % if substituting something with itself, the lists will be the same.
subst(X,Y,[H|T],[I|U]):- X = H, I = Y, subst(X,Y,T,U). % if X is the head of the first then Y is the head of the second and go deeper.
subst(X,Y,[H|T],[I|U]):- I is H, subst(X,Y,T,U).% set I to H
% 4: Write the rules for a predicate insert(X,L,L1), which succeeds if list L1 is identical to the sorted list L with X
%    inserted at the correct place. Assume that L is already sorted.
insert(X,[],[X]). % anything inserted into an empty list is a list of that one thing
insert(X,[H|T],[I|U]):- X =< H, I is H, insert(X, T, U). % else if X is less than or equal to H, load H into new list and move on (dont insert)
insert(X,[H|T],L2):- X > H, insert(X, T, [X|L2]).
