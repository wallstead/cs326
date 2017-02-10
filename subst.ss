;; Write a recursive Scheme function (subst x y L)
;; which returns a list identical to L except that
;; every occurrence of x has been replaced with y.
;; The following example illustrates the use of this function

;; IF supplied L is not empty
;;  Get the first element of L
;;  If first element is the char to replace,
;;    Replace and add
;;  ELSE
;;    Don't replace and add
;; Else return L

(define (subst x y L)
	(if (null? L)
		L
		(if (equal? (car L) x) ;; if first character equals x
      (cons y (subst x y (cdr L))) ;; then append the tail of L to the new list
      (cons (car L) (subst x y (cdr L))) ;; else return first character
    )
  )
)

; I/O:
;   > (subst `a `b `(a b c a d))
;   Value 24: (b b c b d)
