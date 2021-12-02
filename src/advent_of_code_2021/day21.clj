
(ns advent-of-code-2021.day21
  (:require [clojure.string :as str]))

(def test-file "data/day21/test.txt")
(def puzzle-input "data/day21/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day21a [input]
    "Function body")

(->> test-file
    (parse-input)
    day21a)

(->> puzzle-input
    (parse-input)
    day21a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day21b [input]
    "Function body")

(->> test-file
    (parse-input)
    day21b)

(->> puzzle-input
    (parse-input)
    day21b)


