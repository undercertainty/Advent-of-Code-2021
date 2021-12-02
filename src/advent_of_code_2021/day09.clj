
(ns advent-of-code-2021.day09
  (:require [clojure.string :as str]))

(def test-file "data/day09/test.txt")
(def puzzle-input "data/day09/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day09a [input]
    "Function body")

(->> test-file
    (parse-input)
    day09a)

(->> puzzle-input
    (parse-input)
    day09a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day09b [input]
    "Function body")

(->> test-file
    (parse-input)
    day09b)

(->> puzzle-input
    (parse-input)
    day09b)

