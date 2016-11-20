

(DEFUN addToGraph (from to w)
		(progn(vector-push from source)
		(vector-push to dest  )
		(vector-push w weight)))


(defun minDist (v e graph1 graph2 graph3 from to)

  (setq distances (make-array v :initial-element 9999))
  (setq parent (make-array v :initial-element -1))

  (setf (aref distances from) 0)

  (dotimes (i (- v 1))
    (dotimes (j e)
      (IF (> (aref distances (aref graph2 j)) (+ (aref distances (aref graph1 j)) (aref graph3 j)))
       (progn
          (setf (aref distances ( aref graph2 j)) (+ (aref distances (aref graph1 j)) (aref graph3 j)))
          (setf (aref parent (aref graph2 j)) (aref graph1 j))
       )
      )
    )
  )
  (setq result (aref distances to)) 
)


(DEFUN nodeMapping (node)
		(case node
		(0 'BARAK_KAMENG_BACKGATE)	;0
		(1 'BARAK_KAMENG_BACKGATE_INTERSECTION);95
		(2 'T_POINT);180
		(3 'IITG_LAKE);500
		(4 'SUBANSIRI);250
		(5 'SIANG);210
		(6 'LOHIT);150
		((7 8) 'KAPILI_DIBANG);260
		(9 'BRAHMAPUTRA);350
		(10 'DIHING); 2
		(11 'FACULTY_GATE);65
		(12 'BARAK_UMIAM_MANAS_BRIDGE);140
		(13 'MANAS);57
		(14 'BUS_STOP);173
		(15 'BARAK_UMIAM_INTERSECTION);73
		(16 'UMIAM)));140

(DEFUN generateGraph (filename)
	(setq source (make-array 21 :fill-pointer 0))
	(setq dest (make-array 21 :fill-pointer 0))
	(setq weight (make-array 21 :fill-pointer 0))
	(let ((in (open filename :if-does-not-exist nil)))
	   (when in
	   	  ;;for 0->1
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '0 '1 '95))
	      	(progn (addToGraph '1 '0 '95))
	      	)
	      ;;for 1->2
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '1 '2 '180))
	      	(progn (addToGraph '2 '1 '180))
	      	)
	      ;;for 2->3
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '2 '3 '500))
	      	(progn (addToGraph '3 '2 '500))
	      	)
	      ;;for 3->4
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '3 '4 '250))
	      	(progn (addToGraph '4 '3 '250))
	      	)
	      ;;for 3->5
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '3 '5 '210))
	      	(progn (addToGraph '5 '3 '210))
	      	)
	      ;;for 5->6
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '5 '6 '150))
	      	(progn (addToGraph '6 '5 '150))
	      	)
	      ;;for 5->(7,8)
	      (if (= 1 (parse-integer (read-line in nil)))
			(progn (addToGraph '5 '7 '260)
				(addToGraph '5 '8 '260))
	      	(progn (addToGraph '7 '5 '260)
				(addToGraph '8 '5 '260))	      	
	      	)
	      ;;for (7,8)->9
	      (if (= 1 (parse-integer (read-line in nil)))
			(progn (addToGraph '7 '9 '350)
				(addToGraph '8 '9 '350))
	      	(progn (addToGraph '9 '7 '350)
				(addToGraph '9 '8 '350))	      	
	      	)
	      	
	      ;;for 9->10
	      (if (= 1 (parse-integer (read-line in nil)))
			(progn (addToGraph '9 '10 '2))
			(progn (addToGraph '10 '9 '2))
			)
	      ;;for 10->11
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '10 '11 '65))
			(progn (addToGraph '11 '10 '65))
			)
	      ;;for 11->12
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '11 '12 '140))
			(progn (addToGraph '12 '11 '140))
			)
	      ;;for 12->13
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '12 '13 '57))
			(progn (addToGraph '13 '12 '57))
			)
	      ;;for 13->14
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '13 '14 '173))
			(progn (addToGraph '14 '13 '173))
			)
	      ;;for 12->15
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '12 '15 '73))
			(progn (addToGraph '15 '12 '73))
			)
	      ;;for 15->16
	      (if (= 1 (parse-integer (read-line in nil)))
	      	(progn (addToGraph '15 '16 '140))
			(progn (addToGraph '16 '15 '140))
			)
	      ;;corner-cases
	     (progn (addToGraph '7 '8 '1))
		 (progn (addToGraph '8 '7 '1))
			
      	  ;;corner-cases
      	  (progn (addToGraph '2 '14 '2))
		  (progn (addToGraph '14 '2 '2))
      	  
	      (close in)
	   )))


(setq filename "live-streamdata.txt")
(setq strt 12)
(setq end 0)

(generateGraph filename)
(setq dist (minDist 17 21 source dest weight strt end))
(when (/= dist 9999)
    (setq path NIL)
	(setq n end)
	(do ((x 0 (+ 1 x)))
	   ((= strt n) path)
	   (setq n (aref parent n))
	   (setq path (cons n path))
	)
	(format t "Path:")
	; (reverse path)
	(loop for x in path
		do (prin1 (nodeMapping x))
		   (prin1 '->))
	(prin1 (nodeMapping end))
	(terpri)
	(format t "Distance to be travelled ~f m" dist)
)
(if (= dist 9999)
(format t "Destination is not reachable."))