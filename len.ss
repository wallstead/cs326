(define (len L)
		(if (null? L)
			0
			(+ 1 (len (cdr L)))))
