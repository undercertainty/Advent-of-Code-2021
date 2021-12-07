
(ns advent-of-code-2021.day07
  (:require [clojure.string :as str]))

(def test-file "data/day07/test.txt")
(def puzzle-input "data/day07/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
      slurp
      (#(str/split % #","))
      (map #(Integer/parseInt %))))

(parse-input test-file)

(let [t (parse-input test-file)]
  (range (apply min t)
         (apply max t)))

(defn cost-to-align [crab-positions target]
  (apply +
  (map
   (comp #(Math/abs %) #(- target %))
   crab-positions)))

(-> test-file
     parse-input
     (cost-to-align 12))


;;; Main task for part a
;
; No problem using brute force for the first part,
; but I don't see it working for part b...

(defn day07a [input]
  (apply
   min
  (map
   #(cost-to-align input %)
   (range (apply min input)
          (apply max input)))))

(->> test-file
    parse-input
    day07a)

(->> puzzle-input
    parse-input
    day07a)
; 336131

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Shouldn't be too much of a problem? We just
; need to use n/2(n+1) instead of n?

(defn cost-to-move [destination target]
  (let [n (Math/abs (-
                     destination
                     target))]
     (/
      (* n (+ n 1))
      2)))

(defn cost-to-align-b [crab-positions target]
  (apply +
         (map
          #(cost-to-move target %)
          crab-positions)))

(defn day07b [input]
  (apply
   min
   (map
    #(cost-to-align-b input %)
    (range (apply min input)
           (apply max input)))))

(->> test-file
    (parse-input)
    day07b)

(->> puzzle-input
    (parse-input)
    day07b)
; 92676646

