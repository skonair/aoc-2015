(ns aoc-2015.day06
  (require [clojure.string :as str]))

(defn- span-sq 
  [[x1 y1 x2 y2]]
    (for [x (range x1 (inc x2))
          y (range y1 (inc y2))]
      [x y]))

(defn- update-each 
  [m ks fn]
  (reduce #(update-in %1 [%2] fn) m ks))

(defn- parse-line 
  [l] 
  (let [[cmd x1 y1 x2 y2] (into [] (str/split (str/replace l #"turn |through " "") #"[, ]"))]
    [cmd [(Integer. x1) (Integer. y1) (Integer. x2) (Integer. y2)]]))

(defn- execute-line 
  [grid l on-fkt off-fkt tgl-fkt]
  (let [[cmd s] (parse-line l)
        span (span-sq s)]
    (println cmd " on " s)
    (case cmd
      "on" (update-each grid span on-fkt)
      "off" (update-each grid span off-fkt)
      "toggle" (update-each grid span tgl-fkt))))

(defn- brightness 
  [grid] 
  (apply + (vals grid)))

(defn- run-commands 
  [lines egrid on-fkt off-fkt tgl-fkt]
  (loop [grid egrid
         [l & ls] lines]
    (if (nil? l)
      (brightness grid)
      (let [new-grid (execute-line grid l on-fkt off-fkt tgl-fkt)]
        (recur new-grid ls)))))

(defn part1 
  [lines egrid]
  (run-commands 
    lines 
    egrid
    (fn [n] 1)
    (fn [n] 0)
    (fn [n] (if (zero? n) 1 0))))

(defn part2 
  [lines egrid]
  (run-commands
    lines
    egrid
    inc
    (fn [n] (if (zero? n) 0 (dec n)))
    #(+ 2 %)))

(def world (into {} (map #(hash-map % 0) (span-sq [0 0 999 999]))))
(def input (str/split-lines (slurp "resources/day06_input.txt")))

(println "Day 06 - 1: " (part1 input world)) ; 400410
(println "Day 06 - 2: " (part2 input world)) ; 15343601
