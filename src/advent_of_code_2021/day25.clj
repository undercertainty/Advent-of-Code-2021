
(ns advent-of-code-2021.day25
  (:require [clojure.string :as str]))

(def test-file "data/day25/test.txt")
(def puzzle-input "data/day25/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day25a [input]
    "Function body")

(->> test-file
    (parse-input)
    day25a)

(->> puzzle-input
    (parse-input)
    day25a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day25b [input]
    "Function body")

(->> test-file
    (parse-input)
    day25b)

(->> puzzle-input
    (parse-input)
    day25b)


