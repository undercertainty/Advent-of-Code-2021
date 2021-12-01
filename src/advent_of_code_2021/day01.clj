(ns advent-of-code-2021.day01
  (:require [clojure.string :as str]))

(def test-file "data/day01/test.txt")
(def puzzle-input "data/day01/puzzle.txt")

;;; Should be fairly straightforward. For most of these tasks,
;;; we'll be wanting a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       (str/split-lines)
       (map #(Integer/parseInt %))))

(parse-input test-file)

;;; And for the main task, just need to use a map to find the
;;; difference between each member of the sequence and the next

; In part b, we'll need to find the number of increases in a
; sequence as well, so let's define a function here for that:

(defn count-increases [seq-in]
  (->> (map < seq-in (rest seq-in))
       (filter true?)
       count))

(count-increases (parse-input test-file))

(defn day01a [file-in]
  (count-increases (parse-input file-in)))

(day01a test-file)

(day01a puzzle-input)
; 1446

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; For part 2, need a sliding window. Can also use
; a map.

(defn day01b [file-in]
  (let [p (parse-input file-in)]
    (->> (map vector p (rest p) (rest (rest p)))
         (map #(apply + %)) ; add up the contents
         count-increases))) ; and count the increases

(day01b test-file)

(day01b puzzle-input)
; 1486
