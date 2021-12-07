
(ns advent-of-code-2021.day06
  (:require [clojure.string :as str])
  (:require [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day06/test.txt")
(def puzzle-input "data/day06/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       slurp
       (re-seq #"\d")
       (map #(Integer/parseInt %))))

(parse-input test-file)

; Quick function to convert the input to a vector of counts
(defn convert-input [parsed-input]
  (apply
   assoc
   [0 0 0 0 0 0 0 0 0] ; Need to start with 9 0s
   (flatten
    (aoc/counter parsed-input))))
 
(->> test-file
     parse-input
     convert-input)
; [0 1 1 2 1 0 0 0 0]
     


;;; Main task for part a

; Cycle the members of a vector, and add the value
; of the the head to the 7th (ie. timer=6) member:
     
(defn next-day [state-in]
  (->
   (conj (vec (rest state-in)) (first state-in))
   (assoc 6 (+ (first state-in) (nth state-in 7)))))


(defn day06a [parsed-input day]
  (->
   (iterate next-day parsed-input)
   (nth day)
   (#(apply + %))))

(-> test-file
    parse-input
    convert-input
    (day06a 18))

(-> test-file
    parse-input
    convert-input
    (day06a 80))

(-> puzzle-input
     parse-input
     convert-input
     (day06a 80))
; 390923

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Fortunately, saw this one coming, hence the choice
; of representation in part a.

(-> puzzle-input
    parse-input
    convert-input
    (day06a 256))
; 1749945484935