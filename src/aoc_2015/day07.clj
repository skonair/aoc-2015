(ns aoc-2015.day07
  (require [clojure.string :as str]))

(defn- get-value
  [p rs]
  (if (nil? p)
    0
    (if (int? (read-string p))
      (Integer. p)
      (rs p))))

(defn- computible?
  [cmd rs]
  (let [v1 (get-value (:p1 cmd) rs)
        v2 (get-value (:p2 cmd) rs)]
    (not (or (nil? v1) (nil? v2)))))

(defn- compute
  [cmd rs]
  (let [v1 (get-value (:p1 cmd) rs)
        v2 (get-value (:p2 cmd) rs)]
    (case (:op cmd)
      nil v1
      "NOT" (bit-not v1)
      "AND" (bit-and v1 v2)
      "OR" (bit-or v1 v2)
      "LSHIFT" (bit-shift-left v1 v2)
      "RSHIFT" (bit-shift-right v1 v2))))

(defn- parse 
  [r op p1 p2]
  {:r r :op op :p1 p1 :p2 p2})

(defn- parse-line 
  [l]
  (let [splits (str/split l #" ")
        f (first splits)
        r (last splits)]
     (case (count splits)
       3 (parse r nil f nil)
       4 (parse r f (nth splits 1) nil)  
       5 (parse r (nth splits 1) f (nth splits 2)))))  

(def input (str/split-lines (slurp "resources/day07_input.txt")))
(def cmds (map parse-line input))

(def input-b (str/split-lines (slurp "resources/day07b_input.txt")))
(def cmds-b (map parse-line input-b))

(defn- run
  [cmds]
  (loop [[c & cs] (into [] cmds)
         rs {}]
    (println "size of cmds " (count cs) " and c is " c)
    (if (nil? c)
      rs
      (let [cmp? (computible? c rs)
            new-cmds (if cmp? cs (conj (into [] cs) c))
            new-rs (if cmp? (assoc rs (:r c) (compute c rs)) rs)]
        (recur new-cmds new-rs)))))

(defn part1 [] ((run cmds) "a"))

(defn part2 [] ((run cmds-b) "a"))

(println "Day 07 - 1: " (part1))
(println "Day 07 - 2: " (part2))
