
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

(counter (first (parse-input test-file)))
; ([\0 4] [\1 1])

; Now want to count the 0s and 1s in each column and find
; the most common bits

(defn get-gamma [input]
  (->> input
       (apply map vector) ; transpose
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
       (map last) ; least common bit
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

; Want a function to remove the rows with only the
; most common value in the nth column

; Because of the fiddliness around ties, need a tweak
; to the counter function, so that if the number of \0
; and \1 are equal, then it's sorted s.t \1 comes first
; sort is stable, so we can simply sort and reverse:

(defn counter-b [coll]
  (->> coll
       frequencies
       vec
       (sort-by first)
       (sort-by second)
       reverse))

; Remove all rows whose nth value is not the column's most
; common value
(defn filter-by-most-common [input n]
  (let [most-common (->> input
                         (apply map vector) ; transpose
                         (map counter-b)
                         (#(nth % n))
                         first
                         first)]
    (filter #(=
              (nth % n)
              most-common)
            input)))

; Do the same for least common. Obviously I could munge these
; into the same function, but I'm not planning to spend longer
; on this than necessary

(defn filter-by-least-common [input n]
  (let [least-common (->> input
                          (apply map vector) ; transpose
                          (map counter-b)
                          (#(nth % n))
                          last
                          first)]
    (filter #(=
              (nth % n)
              least-common)
            input)))

(defn get-ogr [input]
  (->> input
       (#(reduce
          filter-by-most-common
          %
          (range (count (first input)))))
       first
       str/join
       (#(Integer/parseInt % 2))))

(->> test-file
     parse-input
     get-ogr)

(defn get-c02 [input]
  (->> input
       (#(reduce
          filter-by-least-common
          %
          (range (count (first input)))))
       first
       str/join
       (#(Integer/parseInt % 2))))

(->> test-file
     parse-input
     get-c02)

     
(defn day03b [input]
  (*
   (get-ogr input)
   (get-c02 input)))

(->> test-file
     (parse-input)
     day03b)

(->> puzzle-input
     (parse-input)
     day03b)
; 4856080