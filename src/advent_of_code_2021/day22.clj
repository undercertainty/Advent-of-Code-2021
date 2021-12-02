
(ns advent-of-code-2021.day22
  (:require [clojure.string :as str]))

(def test-file "data/day22/test.txt")
(def puzzle-input "data/day22/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day22a [input]
    "Function body")

(->> test-file
    (parse-input)
    day22a)

(->> puzzle-input
    (parse-input)
    day22a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day22b [input]
    "Function body")

(->> test-file
    (parse-input)
    day22b)

(->> puzzle-input
    (parse-input)
    day22b)


