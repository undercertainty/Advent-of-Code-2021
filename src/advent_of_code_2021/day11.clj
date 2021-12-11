
(ns advent-of-code-2021.day11
  (:require [clojure.string :as str]
            [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day11/test.txt")
(def puzzle-input "data/day11/puzzle.txt")

;;; Start with a function to parse the input.
;;; This is very much not the most efficient
;;; input structure, but probably easiest to 
;;; work with in this context.

(defn parse-input [file-in]
  (->> file-in
       slurp
       str/split-lines
       (map (fn [line-in]
              (map (comp #(- % 48) int) line-in)))
       aoc/grid-to-map))


(parse-input test-file)

;;; Main task for part a

(defn get-neighbours [gridmap row col]
  (->> (for [x [(dec row) row (inc row)]
             y [(dec col) col (inc col)]]
         [x y])
       (remove #(= [row col] %))
       (filter #(contains? gridmap %))))

(get-neighbours (parse-input test-file) 0 5)

(def testlet (aoc/grid-to-map [[1 1 1 1 1]
                               [1 9 9 9 1]
                               [1 9 1 9 1]
                               [1 9 9 9 1]
                               [1 1 1 1 1]]))


(defn stage-1
  "Increase all members of the grid by 1"
  [gridmap]
  (reduce
   (fn [gm k] (update gm k inc))
   gridmap
   (keys gridmap)))

(stage-1 testlet)

; Stage 2 

(defn stage-2-b
  "Given a grid member, increase neighbours and set
   value of provided member to nil. Need to check
   for nil in increment"
  [gridmap row col]
  (let [safe-inc (fn [i]
                   (if (nil? i) nil (inc i)))]
    (-> (reduce
         (fn [gm k] (update gm k safe-inc))
         gridmap
         (get-neighbours gridmap row col))
        (assoc [row col] nil))))


(defn stage-2-a
  "Given a grid, find a flashing squid and update. Return
 nil if no such squids."
  [gridmap]
  
  (if-let [[row col] (some (fn [[k v]] (if (> v 9) k false))
                           (remove (comp nil? second) gridmap))]
    (stage-2-b gridmap row col)))

(defn stage-2
  "Call the next part of stage 2 until returns nil. Then return
   the last value"
  [gridmap]
  (last
  (take-while
   #(not (nil? %))
   (iterate stage-2-a gridmap))))

; Tweak next-state so that it takes a map containing :state

(defn next-state [{gridmap :state}]
  (let [next (-> gridmap
                 stage-1
                 stage-2)
        nil-keys (keys
                  (filter (comp nil? second) next))]
    {:state (reduce #(assoc %1 %2 0) next nil-keys)
     :flashes (count nil-keys)}))

(defn day11a [input]
  (->> (iterate
        next-state
        {:state input :flashes 0})
       (take 101) ; need to include initial state
       (map :flashes)
       (apply +)))

(->> test-file
     parse-input
     day11a
     )

(->> puzzle-input
     (parse-input)
     day11a)
;1705

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Depending on how many steps are needed, this could be
; fairly straightforward...

(defn day11b [input]
  (->> (iterate
        next-state
        {:state input :flashes 0})
       (take-while   ; take while there's a non-zero value in the vals
        (comp (fn [coll] (some #(not= 0 %) coll))
              vals
              :state))
       count))

(->> test-file
     parse-input
     day11b)

(->> puzzle-input
     (parse-input)
     day11b)
;265

; OK. Part 2 is usually the hard bit at the weekend.

