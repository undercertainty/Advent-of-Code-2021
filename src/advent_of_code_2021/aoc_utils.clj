(ns advent-of-code-2021.aoc-utils)

; Some useful functions for the AoC tasks

(defn counter [coll]
  (->> coll
       frequencies
       vec
       (sort-by second)
       reverse))

(defn transpose [grid]
  (apply map vector grid))

