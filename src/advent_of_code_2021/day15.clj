
(ns advent-of-code-2021.day15
  (:require [clojure.string :as str]
            [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day15/test.txt")
(def puzzle-input "data/day15/puzzle.txt")

; Yay! Dijkstra's algorithm!
;
; Again, let's represent the cave as a map, with coords
; as keys.


;;; Start with a function to parse the input:

(defn parse-input [file-in]
  (->> file-in
       slurp
       str/split-lines
       (map vec)
       aoc/grid-to-map
       ((fn [m] (aoc/update-map-values m (comp #(- % 48) int))))))


(parse-input test-file)

(defn initial-state [gridmap]
  (let [loc [0 0]]
    {:loc loc
     :unvisited (keys gridmap)
     :cave gridmap
     :cost (assoc
            (zipmap (keys gridmap) (repeat ##Inf))
            loc
            (gridmap loc))
     :prev (zipmap (keys gridmap) (repeat nil))}))


(defn update-cost
  "Update the cost of neighbour if moving from
  source in the cave"
  [state-in source dest]
  (let [alt (+
             ((state-in :cost) source)
             ((state-in :cave) dest))]
    (if (< alt ((state-in :cost) dest))
      (-> state-in
          (assoc-in [:cost dest] alt)
          (assoc-in [:prev dest] source))
      state-in)))


(defn next-state [state-in]
  (let [[row col] (first (sort-by (state-in :cost)
                                  (state-in :unvisited)))]

    (assoc
     (reduce
      (fn [m d] (update-cost m [row col] d))
      state-in
      (filter (into #{} (state-in :unvisited))
              (for [neighbour [[(inc row) col] [(dec row) col]
                               [row (inc col)] [row (dec col)]]]
                neighbour)))
     :unvisited
     (rest (sort-by (state-in :cost)
                    (state-in :unvisited))))))

;;; Main task for part a

(defn day15a [input]
  (let [init (initial-state input)]
    (-> (first
         (filter
          (comp empty? :unvisited)
          (iterate
           next-state
           init)))
        :cost
        (get (last (sort (init :unvisited))))
        (- ((init :cost) [0 0])))))

(-> test-file
    parse-input
    day15a)

(-> puzzle-input
    parse-input
    day15a)

; 589

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; We're not going to improve on the search algorithm, but do
; need to go through the scruffy task of creating the new 500x500 grid.

(defn incloop [x]
  (if (= x 9)
    1
    (inc x)))

(defn extend-row [row]
  (concat row 
          (map incloop row)
          (map (comp incloop incloop) row)
          (map (comp incloop incloop incloop) row)
          (map (comp incloop incloop incloop incloop) row)))

(defn parse-input-b [f]
  (->> f
       slurp
       str/split-lines
       (map
        (fn [r] (map (comp #(- % 48) int) r)))))


(defn day15b [input]
  (->> input
       (map extend-row)
       aoc/transpose
       (map extend-row)
       aoc/transpose
       aoc/grid-to-map
       day15a))

(->> test-file
     parse-input-b
     day15b)

(->> puzzle-input
     parse-input-b
     day15b)
; 2885


