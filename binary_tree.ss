;; Consider an implementation of binary trees with Scheme lists,
;; as in the following example:

(define T
	'(13
		(5
			(1 () ())
	    (8 ()
				(9 () ())))
		(22
			(17 () ())
			(25 () ()))))

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
			(+ (n-leaves (left T)) 1) ;;
		)
		((>= (n-leaves (right T)) (n-leaves (left T)))
			(+ (n-leaves (right T)) 1)
		)
	)
)


;; I/O:
;;   > (all-different? `(d a b z e a q))
;;   Value: #f
