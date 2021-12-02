
(ns advent-of-code-2021.day08
  (:require [clojure.string :as str]))

(def test-file "data/day08/test.txt")
(def puzzle-input "data/day08/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day08a [input]
    "Function body")

(->> test-file
    (parse-input)
    day08a)

(->> puzzle-input
    (parse-input)
    day08a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day08b [input]
    "Function body")

(->> test-file
    (parse-input)
    day08b)

(->> puzzle-input
    (parse-input)
    day08b)


