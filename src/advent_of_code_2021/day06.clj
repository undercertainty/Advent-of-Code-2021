
(ns advent-of-code-2021.day06
  (:require [clojure.string :as str]))

(def test-file "data/day06/test.txt")
(def puzzle-input "data/day06/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day06a [input]
    "Function body")

(->> test-file
    (parse-input)
    day06a)

(->> puzzle-input
    (parse-input)
    day06a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day06b [input]
    "Function body")

(->> test-file
    (parse-input)
    day06b)

(->> puzzle-input
    (parse-input)
    day06b)


