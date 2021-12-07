
(ns advent-of-code-2021.day05
  (:require [clojure.string :as str])
  (:require [clojure.walk :as w])
  (:require [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day05/test.txt")
(def puzzle-input "data/day05/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       slurp
       (re-seq #"(\d+),(\d+) -> (\d+),(\d+)")
       (map (fn [[_ fromx fromy tox toy]] ; hacky but clear and quick
              {:fromx (Integer/parseInt fromx)
               :fromy (Integer/parseInt fromy)
               :tox (Integer/parseInt tox)
               :toy (Integer/parseInt toy)}))))

(->>
 (parse-input test-file)
 (w/walk vals flatten)
 (apply max))

; I'll use a dense matrix; could be a bit inefficient, but whatever

; Let's just create a matrix of zeros of the longest value provided
; Needs to be a vector rather than a sequence to easily index
(defn initial-state [input]
  (let [max-size (->> input
                      (w/walk vals flatten)
                      (apply max))]
  (into [] 
        (repeat (inc max-size)
                (into []
                      (repeat (inc max-size) 0)))))) ; inc s.t have 0-max range

(-> test-file
    parse-input
    initial-state)

; OK, now thinking numpy/pandas would be nice...

(defn increment-point [matrix {:keys [x y]}]
  (assoc matrix x
         (update (matrix x) y inc)))

; Just considering horizontal and vertical lines for
; now, so in other cases, return empty list
(defn line-to-points [{:keys [fromx fromy tox toy]}]
  (cond
    (= fromx tox) (for [y (aoc/inc-range fromy toy)]
                    {:x fromx :y y})
    (= fromy toy) (for [x (aoc/inc-range fromx tox)]
                    {:x x :y fromy})
    :else ()))

(-> test-file
    parse-input
    first
    line-to-points)

;;; Main task for part a

(defn day05a [input]
  (->> (reduce
        increment-point
        (initial-state input)
        (flatten (map line-to-points input)))

       flatten
       (filter #(< 1 %))
       count))

(->> test-file
    (parse-input)
    day05a)

(->> puzzle-input
    (parse-input)
    day05a)
;7674

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Extend the line-to-points function so that it covers
; the diagonals too. Not bothering to check that the
; diagonals work; that's implied by the question.

(defn line-to-points-b [{:keys [fromx fromy tox toy]}]
  (cond
    (= fromx tox) (for [y (aoc/inc-range fromy toy)]
                    {:x fromx :y y})
    (= fromy toy) (for [x (aoc/inc-range fromx tox)]
                    {:x x :y fromy})
    :else (map 
           (fn [x y] {:x x :y y})
           (aoc/inc-range fromx tox)
           (aoc/inc-range fromy toy))))


(defn day05b [input]
  (->> (reduce
        increment-point
        (initial-state input)
        (flatten (map line-to-points-b input)))

       flatten
       (filter #(< 1 %))
       count))

(->> test-file
     (parse-input)
     day05b)

(->> puzzle-input
     (parse-input)
     day05b)
;20898

