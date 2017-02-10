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
		(cdr (cdr T)) ;; else return second sub-list which should be last element
  )
)

;; I/O:
;;   > (all-different? `(d a b z e a q))
;;   Value: #f
