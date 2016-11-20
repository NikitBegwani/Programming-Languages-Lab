;evaluate value of function 1
(defun design1(x) 
  (* 4 x x)
)

;evaluate value of function 2
(defun design2(x) 
  (* 4 (EXP x))
)

;evaluate value of function 3
(defun design3(x) 
    (- (* 4 x x x x) (* 4 x x x))
)

;evaluate the integral of input function for the given limits
(defun trapezoidal(f a b n)
    (
      LET ((incr (/ (- b a) n)))
      (
        let ((res (+ (funcall f a) (funcall f b))) )
        (setq st a)
        (loop for x from 1 to (- n 1)
          do ( setq res (+ res (* 2 (funcall f (+ a (* x incr))))) )
        )
        (format t "Area is: ~D: " (float (* res (/ incr 2))))
        (return-from trapezoidal (float (* res (/ incr 2))))
      )
    )
)

;calculate trapezoidal integral for first function 
(setq min (trapezoidal 'design1 2 4 100)
  des 1)
(terpri)
;calculate trapezoidal integral for second function
(setq temp (trapezoidal 'design2 2 4 100))
(if(> min temp)
(setq min temp
  des 2))
(terpri)
;calculate trapezoidal integral for third function
(setq temp (trapezoidal 'design3 2 4 100))
(if(> min temp)
(setq min temp
  des 3))
(terpri)
(format t "Minimum Area is: ~5D using design~d" min des)