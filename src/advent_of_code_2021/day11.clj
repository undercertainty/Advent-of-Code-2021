
(ns advent-of-code-2021.day11
  (:require [clojure.string :as str]))

(def test-file "data/day11/test.txt")
(def puzzle-input "data/day11/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day11a [input]
    "Function body")

(->> test-file
    (parse-input)
    day11a)

(->> puzzle-input
    (parse-input)
    day11a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day11b [input]
    "Function body")

(->> test-file
    (parse-input)
    day11b)

(->> puzzle-input
    (parse-input)
    day11b)


