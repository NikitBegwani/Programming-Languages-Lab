(defun design1(x) 
  (* 4 x x)
)

(defun design2(x) 
  (* 4 (EXP x))
)

(defun design3(x) 
    (- (* 4 x x x x) (* 4 x x x))
)


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
        (format t "Area is: ~5f: " (* res (/ incr 3)))
      )
    )
)

(simpson 'design1 2 4 100)
(terpri)
(simpson 'design2 2 4 100)
(terpri)
(simpson 'design3 2 4 100)
