(defun design1(x) 
  (* 4 x x)
)

(defun design2(x) 
  (* 4 (EXP x))
)

(defun design3(x) 
    (- (* 4 x x x x) (* 4 x x x))
)


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
      )
    )
)

(trapezoidal 'design1 2 4 100)
(terpri)
(trapezoidal 'design2 2 4 100)
(terpri)
(trapezoidal 'design3 2 4 100)
