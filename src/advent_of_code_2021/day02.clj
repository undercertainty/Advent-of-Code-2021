(ns advent-of-code-2021.day02
  (:require [clojure.string :as str]))

(def test-file "data/day02/test.txt")
(def puzzle-input "data/day02/puzzle.txt")

;;; Should be fairly straightforward. For most of these tasks,
;;; we'll be wanting a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       (slurp)
       (re-seq #"(\w+)\s+(\d+)")
       ; (map rest)
       (map (fn [[_ dir val]] [dir (Integer/parseInt val)]))))

(parse-input test-file)

;;; For the main task, a function that moves the sub

(defn move-sub [[x y]
                [dir dist]]
  (cond
    (= dir "up") [x (- y dist)]
    (= dir "down") [x (+ y dist)]
    (= dir "forward") [(+ x dist) y]))

; And apply to the input sequence

(defn day02a [file-in]
  (->> file-in
       (parse-input)
       (reduce move-sub [0 0])
       ((fn [[x y]] (* x y)))))

(day02a test-file)

(day02a puzzle-input)
; 2036120


; In part b, we'll need to find the number of increases in a
; sequence as well, so let's define a function here for that:

(defn move-sub-b [[x y aim]
                  [dir dist]]
  (cond
    (= dir "up") [x y (- aim dist)]
    (= dir "down") [x y (+ aim dist)]
    (= dir "forward") [(+ x dist) (+ y (* dist aim)) aim]))

(defn day02b [file-in]
  (->> file-in
       (parse-input)
       (reduce move-sub-b [0 0 0])
       ((fn [[x y _]] (* x y)))))

(day02b test-file)

(day02b puzzle-input)
; 2015547716