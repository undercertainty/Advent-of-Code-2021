
(ns advent-of-code-2021.day12
  (:require [clojure.string :as str]
            [advent-of-code-2021.aoc-utils :as aoc]))

(def test-file-1 "data/day12/test1.txt")
(def test-file-2 "data/day12/test2.txt")
(def test-file-3 "data/day12/test3.txt")
(def puzzle-input "data/day12/puzzle.txt")

;;; Start with a function to parse the input:
;;;
;;; Slightly tweaked so that we don't have "start" as 
;;; an endpoint

(defn create-graph
  "map pair onto a graph structure"
  [graph-sofar [_ start end]]
  (assoc graph-sofar start (conj
                            (get graph-sofar start #{}) end)
         end (conj
              (get graph-sofar end #{})
              start)))


(defn remove-start-as-endpoint [graph]
  (reduce
   (fn [m k] (update m k #(disj % "start")))
   graph
   (keys graph)))


(defn parse-input [file-in]
  (->> file-in
       slurp
       (re-seq #"(\w+)-(\w+)")
       (reduce create-graph {})
       remove-start-as-endpoint))
        

; Let's represent the current state of the search as
; a map {:loc :path}. 

(defn small-cave? [c]
  (= c (str/lower-case c)))

(defn revisiting-small-cave
 "true if loc is a small cave already in path"
  [{:keys [loc path]}]
  (and
     (small-cave? loc)
     (some #{loc} path)))

(defn next-states [graph {:keys [loc path]}]
  (->> loc
       graph
       (map
        (fn [x] {:loc x
                 :path (conj path loc)}))
       (remove revisiting-small-cave)))

(next-states (parse-input test-file-1) {:loc "start"
                                        :path []})

(defn dfs-search [graph
                  {[head & tail] :agenda
                   complete :complete}]
  (if (= (:loc head) "end")
    {:agenda tail
     :complete (conj complete (conj (:path head) "end"))}
    {:agenda (concat (next-states graph head) tail)
     :complete complete}))


(dfs-search
 (parse-input test-file-1)
 {:agenda [{:loc "start" :path []}]
  :complete #{}})


;;; Main task for part a

(defn day12a [input]
  (count
   (:complete
    (first
     (filter
      (comp empty? :agenda)
      (iterate #(dfs-search input %)
               {:agenda [{:loc "start" :path []}]
                :complete #{}}))))))


(->> test-file-1
     (parse-input)
     day12a)

(->> test-file-2
     (parse-input)
     day12a)

(->> test-file-3
     (parse-input)
     day12a)

(->> puzzle-input
    (parse-input)
    day12a)
; 3563

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Main task for part b

; Now we can allow a single repeated case
;
; Aha, I think we can add the case if if's not 
; already present, or if it is present, but all 
; the members of the path are distinct.

; Let's have a test for a valid path that is true if
; the path contains no more than 1 repetition

(defn valid-path? [path-in]
  (let [small-caves (filter small-cave? path-in)
        count-caves (reverse
                     (sort-by second
                             (aoc/counter small-caves)))]
    (cond
      ; false if anything appears more than twice
      (> (second (first count-caves)) 2) false

      ; false if there's a count >1 in rest
      (some #(< 1 %) (map second (rest count-caves))) false

      ; otherwise true
      :else true)))                    


(defn next-states-b [graph {:keys [loc path]}]
  (->> loc
       graph
       (map
        (fn [x] {:loc x
                 :path (conj path loc)}))
       (filter (comp valid-path? :path))
  ))


(next-states (parse-input test-file-1) {:loc "start"
                                        :path []})


(defn dfs-search-b [graph
                    {[head & tail] :agenda
                     complete :complete}]
  (if (= (:loc head) "end")
    {:agenda tail
     :complete (conj complete head)}
    {:agenda (concat (next-states-b graph head) tail)
     :complete complete}))

(defn day12b [input]
  (count
   (:complete
    (first
     (filter
      (comp empty? :agenda)
      (iterate #(dfs-search-b input %)
               {:agenda [{:loc "start" :path []}]
                :complete #{}}))))))


(->> test-file-1
     (parse-input)
     day12b)

(->> test-file-2
     (parse-input)
     day12b)

(->> test-file-3
     (parse-input)
     day12b)

(->> puzzle-input
    (parse-input)
    day12b)
; 105453


