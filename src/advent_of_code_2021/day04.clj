
(ns advent-of-code-2021.day04
  (:require [clojure.string :as str]))

(def test-file "data/day04/test.txt")
(def puzzle-input "data/day04/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day04a [input]
    "Function body")

(->> test-file
    (parse-input)
    day04a)

(->> puzzle-input
    (parse-input)
    day04a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day04b [input]
    "Function body")

(->> test-file
    (parse-input)
    day04b)

(->> puzzle-input
    (parse-input)
    day04b)


