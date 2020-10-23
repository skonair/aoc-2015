(ns aoc-2015.day03
  (require [clojure.string :as str]))

; your code here

(defn- new-coord [d [x y]]
  (case d
    \< [(dec x) y]
    \> [(inc x) y]
    \^ [x (dec y)]
    \v [x (inc y)]
    :otherwise [x y]))

(defn- part0 [directions]
  (loop [[d & ds] directions
         pos [0 0]
         houses #{pos}]
    (if (nil? d)
      houses
      (let [nc (new-coord d pos)]
        (recur ds nc (conj houses nc))))))

(defn part1 [directions] (count (part0 directions)))

(defn part2 [directions]
  (let [[santa robo-santa] (apply map str (partition 2 directions))
        santa-houses (part0 santa)
        robo-santa-houses (part0 robo-santa)]
    (count (apply merge santa-houses robo-santa-houses))))

(def input (str/trim (slurp "resources/day03_input.txt")))

(println "Day 03 - 1: " (part1 input)) ; 2565
(println "Day 03 - 2: " (part2 input)) ; 2639

