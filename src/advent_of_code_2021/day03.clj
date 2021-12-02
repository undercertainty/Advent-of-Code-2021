
(ns advent-of-code-2021.day03
  (:require [clojure.string :as str]))

(def test-file "data/day03/test.txt")
(def puzzle-input "data/day03/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day03a [input]
    "Function body")

(->> test-file
    (parse-input)
    day03a)

(->> puzzle-input
    (parse-input)
    day03a)

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


