
(DEFUN func (num)
	(COND
		((= num 0) (return-from func 0))
		(t
			(setq count (+ count (REM num 10) (func (floor (/ num 10.0) ) ) ) )
			(return-from func count)
		)
	)
)


(defun is-prime (n d) 
  (if (= d 1) 
      (return-from is-prime "t") )
  (if (= (rem n d) 0) 
          (return-from is-prime "nil")) 
   (is-prime n (- d 1) ))
(defun checkPrime(n)
	(return-from checkprime (is-prime n (- n 1))))

(DEFUN final (num)
	(setq count 0)
	(print (checkprime (func num)))
)

(final 122345)