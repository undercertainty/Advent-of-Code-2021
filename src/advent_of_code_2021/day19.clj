
(ns advent-of-code-2021.day19
  (:require [clojure.string :as str]))

(def test-file "data/day19/test.txt")
(def puzzle-input "data/day19/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day19a [input]
    "Function body")

(->> test-file
    (parse-input)
    day19a)

(->> puzzle-input
    (parse-input)
    day19a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day19b [input]
    "Function body")

(->> test-file
    (parse-input)
    day19b)

(->> puzzle-input
    (parse-input)
    day19b)


