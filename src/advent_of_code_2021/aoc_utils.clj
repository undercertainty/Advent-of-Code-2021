(ns advent-of-code-2021.aoc-utils)

; Some useful functions for the AoC tasks

(defn counter [coll]
  (->> coll
       frequencies
       vec
       (sort-by second)
       reverse))

; Grid functions
;
; No error checking

(defn transpose [grid]
  (apply map vector grid))

(defn grid-to-map [grid]
  (zipmap
  (for [row (range (count grid))
        col (range (count (first grid)))]
    [row col])
   (flatten grid)))


; a range function to count in either direction, including the 
; end boundaries. Will only run on integers, and increments of 
; +1 or -1.

(defn inc-range
  "Range from start to end inclusive in either direction. Single increments only."
  ([end]
   (assert (integer? end) "end argument to inc-range must be integer")
   (inc-range 0 end))
  ([start end]
  (assert (integer? start) "start argument to inc-range must be integer")
  (assert (integer? end) "end argument to inc-range must be integer")
  (cond
    (<= start end) (range start (inc end))
    (> start end) (range start (dec end) -1))))

