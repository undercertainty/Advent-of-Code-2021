
(ns advent-of-code-2021.day14
  (:require [clojure.string :as str]
            [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file "data/day14/test.txt")
(def puzzle-input "data/day14/puzzle.txt")

;;; Start with a function to parse the input:

(defn get-rules [text-in]
  (into {}
  (map
   (fn [c] [[(first c) (second c)] (last c)])
  (re-seq #"\w\w -> \w" text-in))))

(defn parse-input [file-in]
  (->> file-in
       slurp
       ((fn [f]
          {:template (first (str/split-lines f))
           :rules (get-rules f)}))))

(defn flatten-triples [triples]
  (->> triples
       (map rest)
       flatten
       (#(conj % (ffirst triples)))))

(defn apply-rules [{:keys [:template :rules]}]
  {:template (flatten-triples
              (map
               (fn [x z] (if-let [y (get rules [x z] false)]
                           [x y z]
                           [x z]))
               template
               (rest template)))
   :rules rules})


(apply-rules
 (parse-input test-file))

;;; Main task for part a

(defn day14a [input]
   (->> input 
        (nth (iterate apply-rules input) 10)
        :template
        aoc/counter
        ((fn [c] (- (second (first c))
                   (second (last c)))))))

(->> test-file
     parse-input
     day14a)

(->> puzzle-input
    (parse-input)
    day14a)
;2891

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Want to keep a count of the pairs. So let's update the
; representation so that we keep a list of pairs, as well as
; the first and last characters in the template

(parse-input test-file)

(defn parse-b [{:keys [template rules]}]
  {:rules rules
   :template template
   :counts (let [chars (set (flatten (vec rules)))]
             (into {}
                   (for [c1 chars
                         c2 chars]
                     [[c1 c2] 0])))
   :start (first template)
   :end (last template)})

(defn add-initial-pairs [init]
  (assoc
   init
   :counts
    (reduce 
   #(update %1 %2 inc)
   (init :counts) 
  (map (fn [c1 c2] [c1 c2]) 
       (init :template) 
       (rest (init :template))))))

(defn day14b [input]
    "Function body")

(->> test-file
     parse-input
     parse-b
     add-initial-pairs)

(->> test-file
    (parse-input)
    day14b)

(->> puzzle-input
    (parse-input)
    day14b)


