
(ns advent-of-code-2021.day24
  (:require [clojure.string :as str]))

(def test-file "data/day24/test.txt")
(def puzzle-input "data/day24/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day24a [input]
    "Function body")

(->> test-file
    (parse-input)
    day24a)

(->> puzzle-input
    (parse-input)
    day24a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day24b [input]
    "Function body")

(->> test-file
    (parse-input)
    day24b)

(->> puzzle-input
    (parse-input)
    day24b)


