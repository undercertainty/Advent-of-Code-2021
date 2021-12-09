
(ns advent-of-code-2021.day09
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def test-file "data/day09/test.txt")
(def puzzle-input "data/day09/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-line [line-in]
  (->> line-in
       (map int)
       (mapv #(- % 48))))  ; hack to convert eg. \0 to 0


(defn parse-input [file-in]
  (->> file-in
       slurp
       str/split-lines
       (mapv parse-line)))


(parse-input test-file)

;;; Main task for part a

; Write a function to get a value from the grid
; by index. I'll set it to return 9 if the indices
; are out of bounds

(defn get-grid-val [grid row column]
  (if-let [r (get grid row)]
    (get r column 9)
    9))

(-> test-file
    parse-input
    (get-grid-val 0 0))

; And now just do a run over the dimensions:

(defn get-low-points [grid]
  (for [row (range (count grid))
        column (range (count (first grid)))]
    {:depth (get-grid-val grid row column)
     :point [row column]
     :neighbours [(get-grid-val grid (inc row) column)
                  (get-grid-val grid (dec row) column)
                  (get-grid-val grid row (inc column))
                  (get-grid-val grid row (dec column))]}))

(defn day09a [input]
  (->> input
       get-low-points
       (filter (fn [{:keys [depth point neighbours]}] 
                 (< depth (apply min neighbours))))
       (map :depth)
       (map inc)
       (apply +)))

(->> test-file
     (parse-input)
     day09a)

(->> puzzle-input
     (parse-input)
     day09a)
; 572

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Wahay! Flood fill!
;
; The description of the problem suggests that
; we're looking for areas bounded by 9s.

(defn find-basin-1 [{:keys [grid agenda in-basin out-basin]}]
  (let [[x y] (first agenda)]
    {:grid grid
     :in-basin (if (< (get-grid-val grid x y) 9)
                 (conj in-basin [x y])
                 in-basin)
     :out-basin (if (>= (get-grid-val grid x y) 9)
                  (conj out-basin [x y])
                  out-basin)
     :agenda (if (< (get-grid-val grid x y) 9)
               (concat
                (remove
                 #(contains? (set/union in-basin out-basin) %)
                 [[(inc x) y] [(dec x) y] [x (inc y)] [x (dec y)]])
                (rest agenda))
               (rest agenda))}))

(first
 (filter
  (comp empty? :agenda)
  (iterate find-basin-1 {:grid (parse-input test-file)
                         :agenda [[0 0]]
                         :in-basin #{}
                         :out-basin #{}})))


(defn find-basin [grid x y]
  (->>   (iterate find-basin-1 {:grid grid
                                :agenda [[x y]]
                                :in-basin #{}
                                :out-basin #{}})
         (filter (comp empty? :agenda))
         first))

(find-basin (parse-input test-file) 0 0)

(defn find-basin-size [grid x y]
  (->>  (find-basin grid x y)
        :in-basin
        count))


(defn day09b [input]
  (->> input
       get-low-points
       (filter (fn [{:keys [depth point neighbours]}] 
                 (< depth (apply min neighbours))))
       (map :point)
       (map #(apply find-basin-size input %))
       sort
       (take-last 3)
       (apply *)))

(->> test-file
     (parse-input)
     day09b)

(->> puzzle-input
     (parse-input)
     day09b)
; 847044


