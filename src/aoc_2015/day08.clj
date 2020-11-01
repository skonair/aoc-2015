(ns aoc-2015.day08
  (require [clojure.string :as str]))

(defn count-decoded
  [line]
  (loop [[l & ls] line
         escaped? false
         cnt 0]
    (if (nil? l)
      cnt
      (let [[new-ls new-escaped? new-cnt] (if escaped?
        (if (= l \x) 
          [(drop 2 ls) false (inc cnt)]
          [ls false (inc cnt)])
        (cond 
          (= l \\) [ls true cnt]
          (= l \") [ls false cnt]
          :otherwise [ls false (inc cnt)]))]
        (recur new-ls new-escaped? new-cnt)))))

(defn count-encoded
  [line]
  (loop [[l & ls] line
         cnt 2]
    (if (nil? l)
      cnt
      (recur ls (+ cnt (cond 
                         (= l \\) 2 
                         (= l \") 2 
                         :otherwise 1))))))

(defn all-counts
  [f1 f2 coll]
  (map #(- (f1 %) (f2 %)) coll))

(def input (str/split-lines (slurp "resources/day08_input.txt")))

(defn part1 [input] (apply + (all-counts count count-decoded input)))
(defn part2 [input] (apply + (all-counts count-encoded count input)))

(println "Day 08 - 1: " (part1 input)) ; 1371
(println "Day 08 - 2: " (part2 input)) ; 2117

