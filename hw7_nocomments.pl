
isSet([]).
isSet([_]).
isSet([H|T]):- not(member(H,T)), isSet(T).

subSet([],[]).
subSet([],[_|_]).
subSet([H|T],S):- member(H,S), subSet(T,S)..

union([],C,C).
union([H|T],S,C):- member(H,S), !, union(T,S,C).
union([H|T],S,[H|Ct]):- union(T,S,Ct).

intersection([],_,[]).
intersection([H|T],S,[H|Ct]) :- member(H,S), intersection(T,S,Ct).
intersection([_|T],S,C) :- intersection(T,S,C).

tally(_,[],0).
tally(E,[E|T],N):- tally(E,T,X), N is 1+X.
tally(E,[_|T],N):- tally(E,T,N).

subst(_,_,[],[]).
subst(X,X,Y,Y).
subst(X,Y,[H|T],[I|U]):- X = H, I = Y, subst(X,Y,T,U).
subst(X,Y,[H|T],[I|U]):- I is H, subst(X,Y,T,U).

insert(X,[],[X]). 
insert(X,[H|T],[H|U]):- X >= H, insert(X, T, U).
insert(X,[H|T],[X|[H|T]]):- X < H.

flatten([], []).
flatten([[]|Ls], R) :- flatten(Ls, R).
flatten([[El|L1s]|Ls], [El|R]) :- flatten([L1s|Ls], R).
