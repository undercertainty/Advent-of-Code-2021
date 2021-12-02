
(ns advent-of-code-2021.day12
  (:require [clojure.string :as str]))

(def test-file "data/day12/test.txt")
(def puzzle-input "data/day12/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day12a [input]
    "Function body")

(->> test-file
    (parse-input)
    day12a)

(->> puzzle-input
    (parse-input)
    day12a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day12b [input]
    "Function body")

(->> test-file
    (parse-input)
    day12b)

(->> puzzle-input
    (parse-input)
    day12b)


