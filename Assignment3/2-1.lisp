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
(defun simpson(f a b n)
    (
      LET ((incr (/ (- b a) n)))
      (
        let ((res (+ (funcall f a) (funcall f b))) )
        (loop for x from 1 to (- n 1)
         if(oddp x)
            do ( setq res (+ res (* 4 (funcall f (+ a (* x incr))))) )
         if(evenp x)
            do ( setq res (+ res (* 2 (funcall f (+ a (* x incr))))) )
        )
        (format t "Area is: ~5f " (* res (/ incr 3)))
        (return-from simpson (* res (/ incr 3)))
      )
    )
)
;calculate simpson integral for first function 
()
(setq min (simpson 'design1 2 4 100)
  des 1)
(terpri)
;calculate simpson integral for second function
(setq temp (simpson 'design2 2 4 100))
(if(> min temp)
(setq min temp
  des 2))
(terpri)
;calculate simpson integral for third function
(setq temp (simpson 'design3 2 4 100))
(if(> min temp)
(setq min temp
  des 3))
(terpri)
(format t "Minimum Area is: ~5f using design~d" min des)