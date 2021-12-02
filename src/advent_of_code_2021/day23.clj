
(ns advent-of-code-2021.day23
  (:require [clojure.string :as str]))

(def test-file "data/day23/test.txt")
(def puzzle-input "data/day23/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day23a [input]
    "Function body")

(->> test-file
    (parse-input)
    day23a)

(->> puzzle-input
    (parse-input)
    day23a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day23b [input]
    "Function body")

(->> test-file
    (parse-input)
    day23b)

(->> puzzle-input
    (parse-input)
    day23b)


