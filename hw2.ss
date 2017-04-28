
(define (subst x y L)
	(if (null? L)
		L
		(if (equal? (car L) x) ;; if first character equals x
      (cons y (subst x y (cdr L))) ;; then append the tail of L to the new list
      (cons (car L) (subst x y (cdr L))) ;; else return first character
    )
  )
)

(define (len L)
	(if (null? L)
		0
		(+ 1 (len (cdr L)))))


(define (member? x L)
	(cond
		((null? L) #f)
		((equal? x (car L)) #t)
		(else (member? x (cdr L)))
	)
)

;; in list L, take first element
(define (all-different? L)
	(if (null? L)
		#t ;; if empty list just return true to stop recursion
		(if (member? (car L) (cdr L)) ;; if first char is repeated in the tail
      #f ;; then the list has non-unique elements
      (all-different? (cdr L)) ;; else check the list without the first element
    )
  )
)

(define (left T)
	(if (= (length T) 1) ;; if 1 elem in list this is a leaf
		();; if so return empty list
		(car (cdr T)) ;; else return first sub-list which should be second element
  )
)

(define (right T)
	(if (= (length T) 1) ;; if 1 elem in list this is a leaf
		();; if so return empty list
		(car (cdr (cdr T))) ;; else return second sub-list which should be last element
  )
)

(define (val T)
	(car T)
)

;; (a) Write a recursive function (n-nodes T), which returns the number of nodes
;;     in the tree T.

(define (n-nodes T)
	(cond ((null? T) 0) ; empty
		(else (+ 1 (n-nodes (left  T)) (n-nodes (right T)))))
)

;; (b) Write a recursive function (n-leaves T), which returns the number of leaves
;;     in the tree T.

(define (n-leaves T)
	(cond
		((null? T) 0) ; empty
		((= (n-nodes T) 1) 1) ; is a leaf
		((> (n-nodes T) 1) (+ (n-leaves (left  T)) (n-leaves (right T)))) ; not a leaf so check right and left for leafage and add it if leaf
	)
)

;; (c) The height of a tree is defined as the maximum number of nodes on a path
;;     from the root to a leaf. Write a recursive function (height T), which
;;     returns the height of the tree T.

(define (height T)
	(cond
		((null? T) 0) ; empty
		((> (n-leaves (left T)) (n-leaves (right T)))
			(+ (height (left T)) 1)
		)
		((<= (n-leaves (left T)) (n-leaves (right T)))
			(+ (height (right T)) 1)
		)
	)
)

;; (d) Write a recursive function (postorder T), which returns the list of all elements
;;     in the tree T corresponding to a postorder traversal of the tree. The following example illustrates the use of this function

(define (postorder T)
	(if (not (null? T))
		(flatten (list (append (postorder (left T)) (postorder (right T))) (val T)))
		();; return empty list
	)
)

(define (flatten L)
  (cond
    ((null? L) ())
    ((list? L)
     (append (flatten (car L)) (flatten (cdr L))))
    (else (list L)))
)
