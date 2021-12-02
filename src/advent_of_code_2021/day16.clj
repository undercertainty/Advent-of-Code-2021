
(ns advent-of-code-2021.day16
  (:require [clojure.string :as str]))

(def test-file "data/day16/test.txt")
(def puzzle-input "data/day16/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day16a [input]
    "Function body")

(->> test-file
    (parse-input)
    day16a)

(->> puzzle-input
    (parse-input)
    day16a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day16b [input]
    "Function body")

(->> test-file
    (parse-input)
    day16b)

(->> puzzle-input
    (parse-input)
    day16b)


