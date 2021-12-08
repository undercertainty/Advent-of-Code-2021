
(ns advent-of-code-2021.day08
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.math.combinatorics :as combo]
            [advent-of-code-2021.aoc-utils :as aoc]))

; Bloody hell, this looks tedious...

(def test-file "data/day08/test.txt")
(def puzzle-input "data/day08/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-line [line-in]
  (let [nums (re-seq #"[a-g]+" line-in)]
    (assert (= 14 (count nums)))
    {:inputs (take 10 nums)
     :outputs (take-last 4 nums)}))

(defn parse-input [file-in]
  (->> file-in
       slurp
       str/split-lines
       (map parse-line)))

(parse-input test-file)

;;; Main task for part a

(defn day08a [input]
  (->> input
       (map :outputs)
       flatten
       (map count)
       (aoc/counter)
       (into {})
       (#(+ (% 2)
            (% 3)
            (% 4)
            (% 7)))))

(->> test-file
    (parse-input)
    day08a)

(->> puzzle-input
    (parse-input)
    day08a)
;440

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Actually, thinking about it, there's only 7! (~6000) combinations.
; So we can probably get the mapping by brute force...

; Let's say input here is the set of sets of characters
; Let's say correct-mapping? is true if each of the mapped
; inputs is one of the characters 0-9

(def *number-encodings*
  {#{\a \b \c \e \f \g} 0
   #{\c \f} 1
   #{\a \c \d \e \g} 2
   #{\a \c \d \f \g} 3
   #{\b \c \d \f} 4
   #{\a \b \d \f \g} 5
   #{\a \b \d \e \f \g} 6
   #{\a \c \f} 7
   #{\a \b \c \d \e \f \g} 8
   #{\a \b \c \d \f \g} 9})

(defn correct-mapping? [m {inputs :inputs}]
  (->> inputs
       (map #(replace m %))
       (map set)
       set
       (=
        (set (keys *number-encodings*)))))

(let [i (->> test-file
             parse-input
             first)]
  (filter
   #(test-mapping % i)
   (map
    #(zipmap "abcdefg" %)
    (combo/permutations "abcdefg"))))

; So then we can, for a given input, find the correct
; mapping:

(defn find-correct-mapping [input]
  (first
   (filter
    #(test-mapping % input)
    (map
     #(zipmap "abcdefg" %)
     (combo/permutations "abcdefg")))))

(defn calculate-output [{outputs :outputs} mapping]
  (->> outputs
       (map #(replace mapping %))
       (map set)
       (map *number-encodings*)
       str/join
       (#(Integer/parseInt %))))

(defn day08b [input]
  (->> (for [parsed-line input]
    (calculate-output
     parsed-line
     (find-correct-mapping
      parsed-line)))
       (apply +)))

(->> test-file
     parse-input
     day08b)

(->> puzzle-input
    parse-input
    day08b)
; 1046281