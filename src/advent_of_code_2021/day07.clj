
(ns advent-of-code-2021.day07
  (:require [clojure.string :as str]))

(def test-file "data/day07/test.txt")
(def puzzle-input "data/day07/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day07a [input]
    "Function body")

(->> test-file
    (parse-input)
    day07a)

(->> puzzle-input
    (parse-input)
    day07a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day07b [input]
    "Function body")

(->> test-file
    (parse-input)
    day07b)

(->> puzzle-input
    (parse-input)
    day07b)


