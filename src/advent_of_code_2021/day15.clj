
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



(-> test-file
    parse-input
    initial-state
    (update-cost [0 0] [0 1])
    (update-cost [0 0] [1 0])
    (update-cost [0 1] [1 1])
    (update-cost [1 0] [1 1])
    :prev
    (get [1 1]))

(update-cost
 (initial-state (parse-input test-file))
 [0 0]
 [0 1]
 )


(defn next-state [{:keys [unvisited cave cost prev]}]
  (let [[row col] (first (sort-by cost unvisited))]
    (update-costs)
    
    (filter (into #{} unvisited) (for [neighbour [[(inc row) col] [(dec row) col]
                 [row (inc col)] [row (dec col)]]]
      neighbour))))
    

;;; Main task for part a

(defn day15a [input]
    (initial-state input))

(->> test-file
    (parse-input)
    day15a
     next-state)

(->> puzzle-input
    (parse-input)
    day15a)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

(defn day15b [input]
    "Function body")

(->> test-file
    (parse-input)
    day15b)

(->> puzzle-input
    (parse-input)
    day15b)


