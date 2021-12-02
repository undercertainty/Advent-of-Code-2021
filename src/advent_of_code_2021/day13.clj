
(ns advent-of-code-2021.day13
  (:require [clojure.string :as str]))

(def test-file "data/day13/test.txt")
(def puzzle-input "data/day13/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day13a [input]
    "Function body")

(->> test-file
    (parse-input)
    day13a)

(->> puzzle-input
    (parse-input)
    day13a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day13b [input]
    "Function body")

(->> test-file
    (parse-input)
    day13b)

(->> puzzle-input
    (parse-input)
    day13b)


