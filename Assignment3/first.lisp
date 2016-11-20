;return the sum of all the digits in a number
(DEFUN numSum (num)
	
		(if (= num 0) 
			(return-from numSum 0)
		)
		(return-from numSum (+ (REM num 10) (numSum (/ (- num (mod num 10)) 10))))
)

;returns true is n is not divisible by any number smaller than or eequal to d
(defun is-prime (n d) 
    (if (= d 1) 
    	(return-from is-prime t) )
    (if (= (rem n d) 0) 
        (return-from is-prime nil)) 
    (is-prime n (- d 1) )
)

;evaluates if a given number is prime
(defun checkPrime(n)
	(return-from checkprime (is-prime n (- n 1))))

(DEFUN getFunc (num)
	(setq val (checkprime (numSum num)))
	(print num)
	(print val)
	(return-from getFunc val)
)

;read input from input.txt file
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
;evaluate all the numbers one by one and store the numbers with prime sum in res
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
(setq res (reverse res))
;write the res to output.txt
(with-open-file (out "output.txt" :direction :output :if-does-not-exist :create)
	(dolist 
		(segment res)
       	(format out "~D~%" segment)
    )
)
