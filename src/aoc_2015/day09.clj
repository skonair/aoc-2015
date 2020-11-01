(ns aoc-2015.day09
  (require [clojure.string :as str]))

(defn permutations [s]
  (lazy-seq
   (if (seq (rest s))
     (apply concat (for [x s]
                     (map #(cons x %) (permutations (remove #{x} s)))))
     [s])))

(defn dist [l1 l2 input]
  (first
    (map 
      #(Integer. ( last %)) 
      (filter 
        (fn [[a b c]] (or (and (= a l1) (= b l2)) (and (= a l2) (= b l1)))) 
        input))))

(defn all-locations [ds]
 (into #{} (apply concat (map #(list (first %) (second %)) ds))))

(defn distance [trail ds penalty]
  (loop [[c & cs] (partition 2 1 trail)
         cnt 0]
    (if (nil? c)
      cnt
      (let [d (dist (first c) (second c) ds)
            n (if (nil? d) penalty d)]
          (recur cs (+ cnt n))))))

(defn find-shortest [ds]
  (let [ps (permutations (all-locations ds))]
    (apply min (map #(distance % ds 10000000) ps))))

(defn find-longest [ds]
  (let [ps (permutations (all-locations ds))]
    (apply max (map #(distance % ds -10000000) ps))))

(defn parse-line [line]
  (let [[from _ to _ dist] (str/split line #" ")]
    [from to dist]))

(def input (str/split-lines (slurp "resources/day09_input.txt")))
(def lines (map parse-line input))

(defn part1 [] (find-shortest lines))
(defn part2 [] (find-longest lines))

(println "Day 09 - 1: " (part1 input)) ; 117
(println "Day 09 - 2: " (part2 input)) ; 909

