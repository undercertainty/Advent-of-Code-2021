
(ns advent-of-code-2021.day10
  (:require [clojure.string :as str]))

(def test-file "data/day10/test.txt")
(def puzzle-input "data/day10/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       slurp
       str/split-lines))
       ; Do something here))

(parse-input test-file)

;;; Main task for part a

(defn find-mismatch-1 [{:keys [stack remaining mismatch]}]
  (let [pairs (zipmap "]}>)" "[{<(")]
    (cond
      (contains? (set "[{<(") (first remaining))
      {:stack (cons (first remaining) stack)
       :remaining (rest remaining)
       :mismatch mismatch}

      (= (first stack) (pairs (first remaining)))
      {:stack (rest stack)
       :remaining (rest remaining)
       :mismatch mismatch}

      :else
      {:stack (rest stack)
       :remaining (rest remaining)
       :mismatch (cons (first remaining) mismatch)})))

(defn find-mismatch [line-in]
  (first
   (filter
    (comp empty? :remaining)
    (iterate find-mismatch-1 {:stack (cons (first line-in) ())
                              :remaining (rest line-in)
                              :mismatch ()}))))

(defn day10a [input]
    (->> input
         (map find-mismatch)
         (map (comp last :mismatch))
         (remove nil?)
         (map (zipmap ")]}>" [3 57 1197 25137]))
         (apply +)))

(->> test-file
     parse-input
     day10a)

(->> puzzle-input
    (parse-input)
    day10a)
; 392421

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Function to count the score of the required
; closing parens

(defn score-close-parens [parens]
  (let [score (zipmap "([{<" [1 2 3 4])]
    (reduce
     (fn [sofar paren] (+
                        (* sofar 5)
                        (score paren)))
     0
    parens)))

(score-close-parens [\{ \{ \[ \[ \( \{ \( \[])

; Incomplete cases are those with no mismatches
(defn day10b [input]
  (->> input
       (map find-mismatch)
       (filter (comp empty? :mismatch))
       (map (comp score-close-parens :stack))
       sort
       ((fn [coll]
         (nth coll (int (/ (count coll) 2)))))))


(->> test-file
    (parse-input)
    day10b)

(->> puzzle-input
    (parse-input)
    day10b)
; 2769449099