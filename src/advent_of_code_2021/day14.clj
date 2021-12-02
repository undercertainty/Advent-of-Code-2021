
(ns advent-of-code-2021.day14
  (:require [clojure.string :as str]))

(def test-file "data/day14/test.txt")
(def puzzle-input "data/day14/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day14a [input]
    "Function body")

(->> test-file
     (parse-input)
     day14a)

(->> puzzle-input
    (parse-input)
    day14a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day14b [input]
    "Function body")

(->> test-file
    (parse-input)
    day14b)

(->> puzzle-input
    (parse-input)
    day14b)


