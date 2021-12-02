
(ns advent-of-code-2021.day20
  (:require [clojure.string :as str]))

(def test-file "data/day20/test.txt")
(def puzzle-input "data/day20/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn day20a [input]
    "Function body")

(->> test-file
    (parse-input)
    day20a)

(->> puzzle-input
    (parse-input)
    day20a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day20b [input]
    "Function body")

(->> test-file
    (parse-input)
    day20b)

(->> puzzle-input
    (parse-input)
    day20b)


