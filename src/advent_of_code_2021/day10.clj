
(ns advent-of-code-2021.day10
  (:require [clojure.string :as str]))

(def test-file "data/day10/test.txt")
(def puzzle-input "data/day10/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day10a [input]
    "Function body")

(->> test-file
    (parse-input)
    day10a)

(->> puzzle-input
    (parse-input)
    day10a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day10b [input]
    "Function body")

(->> test-file
    (parse-input)
    day10b)

(->> puzzle-input
    (parse-input)
    day10b)


