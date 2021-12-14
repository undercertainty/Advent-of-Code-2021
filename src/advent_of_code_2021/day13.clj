
(ns advent-of-code-2021.day13
  (:require [clojure.string :as str]
            [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day13/test.txt")
(def puzzle-input "data/day13/puzzle.txt")

;;; Start with a function to parse the input:

(defn make-sheet [dots]
  (let [size-x (apply max (map first dots))
        size-y (apply max (map second dots))]
    (reduce
     (fn [sheet [col row]]
         (assoc sheet
                row
                (assoc (nth sheet row) col \#)))
     (vec (repeat
           (inc size-y)
           (vec (repeat (inc size-x) \.))))
     dots)))


(defn parse-input [file-in]
  (make-sheet
   (map
    (fn [[_ x y]] [(Integer/parseInt x)
                   (Integer/parseInt y)])
    (re-seq #"(\d+),(\d+)" (slurp file-in)))))


(parse-input test-file)


;;; Main task for part a

(defn fold-row [fold row]
  (let [left (take fold row)
        right (drop (inc fold) row)
        width (max (count left) (count right))]
    (take width
          (map (fn [l r] (if (some #{\#} [l r])
                           \#
                           \.))
               (concat (reverse left) (repeat \.))
               (concat right (repeat \.))))))
              
(defn fold-sheet-vertical [fold sheet]
  (map
   #(fold-row fold %)
   sheet))

(defn fold-sheet-horizontal [fold sheet]
  (->> sheet
       aoc/transpose
       (map #(fold-row fold %))
       aoc/transpose
       reverse))

(defn show-sheet [sheet]
  (doseq [nl sheet]
    (prn (apply str nl))))

(->> test-file
     parse-input
     (fold-sheet-horizontal 7)
     (fold-sheet-vertical 5)
     show-sheet)


; I really can't be bothered to parse the folding 
; instructions as there aren't very many for either
; case. So I'm just going to type them in manually

(->> puzzle-input
    parse-input
     (fold-sheet-vertical 655)
     flatten
     aoc/counter)
; 810    

(->> puzzle-input
     parse-input
     (fold-sheet-vertical 655)
     (fold-sheet-horizontal 447)
     (fold-sheet-vertical 327)
     (fold-sheet-horizontal 223)
     (fold-sheet-vertical 163)
     (fold-sheet-horizontal 111)
     (fold-sheet-vertical 81)
     (fold-sheet-horizontal 55)
     (fold-sheet-vertical 40)
     (fold-sheet-horizontal 27)
     (fold-sheet-horizontal 13)
     (fold-sheet-horizontal 6)
     show-sheet)

"#..#.#....###..#..#.###...##..####.###.."
"#..#.#....#..#.#..#.#..#.#..#.#....#..#."
"####.#....###..#..#.###..#....###..#..#."
"#..#.#....#..#.#..#.#..#.#.##.#....###.."
"#..#.#....#..#.#..#.#..#.#..#.#....#.#.."
"#..#.####.###...##..###...###.#....#..#."

; HLBUBGFR if the font's not the best

