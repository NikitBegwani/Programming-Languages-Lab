
(DEFUN numSum (num)
	(COND
		((= num 0) (return-from numSum 0))
		(t
			(setq count (+ count (REM num 10) (numSum (floor (/ num 10.0) ) ) ) )
			(return-from numSum count)
		)
	)
)


(defun is-prime (n d) 
    (if (= d 1) 
    	(return-from is-prime t) )
    (if (= (rem n d) 0) 
        (return-from is-prime nil)) 
    (is-prime n (- d 1) )
)

(defun checkPrime(n)
	(return-from checkprime (is-prime n (- n 1))))

(DEFUN getFunc (num)
	(setq count 0)
	(print (checkprime (numSum num)))
)


(setq inp
	(
		with-open-file (stream "input.txt")
		(
			loop for line = (read-line stream nil)
			while line
			collect line
		)
	)
)
(setq res nil)
(loop
	(when
		(null inp)
		(return)			
	)
	(setq
		line (car inp)
		inp (cdr inp)
		num (parse-integer line)
	)
	(if
		(getFunc num)
		(setq res (cons line res))
	)
)

(with-open-file (out "output.txt" :direction :output :if-does-not-exist :create)
	(dolist 
		(segment res)
       	(format out "~D~%" segment)
    )
)