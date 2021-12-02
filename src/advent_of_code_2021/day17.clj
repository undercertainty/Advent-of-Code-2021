
(ns advent-of-code-2021.day17
  (:require [clojure.string :as str]))

(def test-file "data/day17/test.txt")
(def puzzle-input "data/day17/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day17a [input]
    "Function body")

(->> test-file
    (parse-input)
    day17a)

(->> puzzle-input
    (parse-input)
    day17a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day17b [input]
    "Function body")

(->> test-file
    (parse-input)
    day17b)

(->> puzzle-input
    (parse-input)
    day17b)


