
(ns advent-of-code-2021.day04
  (:require [clojure.string :as str])
  (:require [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day04/test.txt")
(def puzzle-input "data/day04/puzzle.txt")

;;; Start with a function to parse the input:

(defn parse-board [board-in]
  (map
   #(map (fn [x] {:value (Integer/parseInt x)
                  :marked false}) 
         (re-seq #"\d+" %))
   (str/split board-in #"\n")))

(defn parse-input [file-in]
  (let [ls (-> file-in
               slurp
               (str/split #"\n\n"))]
    {:calls (map
             #(Integer/parseInt %)
             (re-seq #"\d+" (first ls)))
     :boards (map parse-board (rest ls))}))

(parse-input test-file)


;;; Some operations on the boards

(defn mark-board [board value]
  (map
   #(map
     (fn [square]
       (if (= (:value square) value)
         (assoc square :marked true)
         square))
     %)
   board))   

(defn contains-complete-row [board]
  (some
   identity
   (map
    #(every? :marked %)
    board)))

(defn contains-complete-column [board]
  (contains-complete-row
   (aoc/transpose board)))

(defn complete? [board]
  (if (or
       (contains-complete-row board)
       (contains-complete-column board))
    board
    nil))


(defn call-numbers [[[call & calls] boards last-call]]
  [calls
   (map
    #(mark-board % call)
    boards)
   call])


; create an iterator of the boards:

(defn it []
  (let [p (parse-input test-file)]
    (iterate call-numbers [(p :calls) (p :boards) nil])))

; Score for a completed state

(defn calculate-score [board multiplier]
  (->> board
       flatten
       (remove :marked)
       (map :value)
       (apply +)
       (* multiplier)))


(defn complete-state [[_ boards last-call]]
  (if-let [board (some
                  complete?
                  boards)]
    (calculate-score board last-call)
    nil))

(take 20
      (map complete-state (it)))


(defn day04a [input]
  (let [p (parse-input input)
        it (fn [] (iterate call-numbers
                           [(p :calls) (p :boards) nil]))]
    (filter identity (map complete-state (it)))))

(->> test-file
    day04a
     first)

(->> puzzle-input
    day04a
     first)
; 41668

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Go with the iterator it again:

; It's getting late, so this is going to be a horrible hack.
; For future reference: never use this as a demonstration
; of nice code

; Let's try removing the completed boards from call-numbers

(defn call-numbers-b [[[call & calls] boards last-call]]
  [calls
   (remove
    complete?
    (map
     #(mark-board % call)
     boards))
   call])

(defn it-b []
  (let [p (parse-input test-file)]
    (iterate call-numbers-b [(p :calls) (p :boards) nil])))

(nth (it-b) 14)

; OK, now we can run through until we get to the final state where
; there's still an incomplete board:

(take-while
 (comp not empty? second)
 (it-b))

; And the last one will be the state before the losing card
; is completed:

(last (take-while
       (comp not empty? second)
       (it-b)))

; Now just wrap that into a function:

(defn day04b [{:keys [calls boards]}]
  (let [bingo-it (fn []
                   (iterate
                    call-numbers-b
                    [calls boards nil]))
        last-state (last
                    (take-while
                     (comp not empty? second)
                     (bingo-it)))]
    (calculate-score
     (mark-board
      (first (second last-state))
      (ffirst last-state))
     (ffirst last-state))))


(->> test-file
    parse-input
    day04b)

(->> puzzle-input
    (parse-input)
    day04b)
; 10478