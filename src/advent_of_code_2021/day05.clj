
(ns advent-of-code-2021.day05
  (:require [clojure.string :as str]))

(def test-file "data/day05/test.txt")
(def puzzle-input "data/day05/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day05a [input]
    "Function body")

(->> test-file
    (parse-input)
    day05a)

(->> puzzle-input
    (parse-input)
    day05a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day05b [input]
    "Function body")

(->> test-file
    (parse-input)
    day05b)

(->> puzzle-input
    (parse-input)
    day05b)


