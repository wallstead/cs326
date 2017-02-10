;; Write a recursive Scheme function (all-different? L), which determines
;; whether all elements of list L are distinct (that is, not equal?).

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

;; I/O:
;;   > (all-different? `(d a b z e a q))
;;   Value: #f
