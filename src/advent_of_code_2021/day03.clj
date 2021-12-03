
(ns advent-of-code-2021.day03
  (:require [clojure.string :as str]))

(def test-file "data/day03/test.txt")
(def puzzle-input "data/day03/puzzle.txt")

;;; Start with a function to parse the input:

; Get as a seq of colls of 1s and 0s

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       (re-seq #"[10]+")
       (map vec)))

(parse-input test-file)

; It'll be useful to have a function that orders the
; members of a coll by how often they appear:

(defn counter [coll]
  (->> coll
       frequencies
       vec
       (sort-by second)
       reverse))

(counter [1 2 2 1 1 1 2 2 1 1 1 1 1 2])
; ([1 9] [2 5])

; Now want to count the 0s and 1s in each column and find
; the most common bits

(defn get-gamma [input]
  (->> input
       (apply map vector)
       (map counter)
       (map first)  ; most common bit
       (map first)
       str/join
       (#(Integer/parseInt % 2))))

(->> test-file
     parse-input
     get-gamma)

(defn get-epsilon [input]
  (->> input
       (apply map vector)
       (map counter)
       (map second) ; least common bit
       (map first)
       str/join
       (#(Integer/parseInt % 2))))

(->> test-file
     parse-input
     get-epsilon)


(defn day03a [input]
  (*
   (get-gamma input)
   (get-epsilon input)))

(->> test-file
     (parse-input)
     day03a)

(->> puzzle-input
     (parse-input)
     day03a)
; 3242606


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b





(defn day03b [input]
  "Function body")

(->> test-file
     (parse-input)
     day03b)

(->> puzzle-input
     (parse-input)
     day03b)

(defn counter [coll]
  (->> coll
       frequencies
       vec
       (sort-by second)
       reverse))


(counter [1 2 2 1 1 2  22 2 2 3 2 2])


(let [d {:a 1 :b 2 :c 3}]
  (vec d))