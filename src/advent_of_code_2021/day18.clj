
(ns advent-of-code-2021.day18
  (:require [clojure.string :as str]))

(def test-file "data/day18/test.txt")
(def puzzle-input "data/day18/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day18a [input]
    "Function body")

(->> test-file
    (parse-input)
    day18a)

(->> puzzle-input
    (parse-input)
    day18a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day18b [input]
    "Function body")

(->> test-file
    (parse-input)
    day18b)

(->> puzzle-input
    (parse-input)
    day18b)


