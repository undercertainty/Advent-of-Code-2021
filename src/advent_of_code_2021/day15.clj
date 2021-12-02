
(ns advent-of-code-2021.day15
  (:require [clojure.string :as str]))

(def test-file "data/day15/test.txt")
(def puzzle-input "data/day15/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day15a [input]
    "Function body")

(->> test-file
    (parse-input)
    day15a)

(->> puzzle-input
    (parse-input)
    day15a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day15b [input]
    "Function body")

(->> test-file
    (parse-input)
    day15b)

(->> puzzle-input
    (parse-input)
    day15b)


