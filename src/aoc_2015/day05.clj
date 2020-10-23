(ns aoc-2015.day05
  (require [clojure.string :as str]))

(def vowel? (set "aeiou"))

(defn- vowel-condition [s]
  (> (count (filter vowel? s)) 2))

(defn- double-letter-condition [s]
  (not
    (empty?
      (filter #(= (first %) (second %)) (partition 2 1 s)))))

(defn- bad-pairs-condition [s]
  (let [pairs (into #{} (map #(str (first %) (second %)) (partition 2 1 s)))]
    (not
      (or
        (contains? pairs "ab")
        (contains? pairs "cd")
        (contains? pairs "pq")
        (contains? pairs "xy")))))

(defn- nice-string? [s]
  (and
    (vowel-condition s)
    (double-letter-condition s)
    (bad-pairs-condition s)))

(defn- scan-word [s]
   (loop [[a & rs] s
          ll nil
          cnt 0
          ack []]
     (if (nil? a)
       ack
       (let [ncnt (if (= ll a) (inc cnt) 0)
             nack (if (or (nil? ll) (and (> ncnt 1) (even? ncnt))) ack (conj ack [ll a]))]
         (recur rs a ncnt nack)))))

(defn- overlaps-condition [s]
  (let [pairs (scan-word s)]
    (> (count pairs) (count (into #{} pairs)))))

(defn- repeat-letter-condition [s]
  (not
    (empty?
      (filter #(= (first %) (nth % 2)) (partition 3 1 s)))))

(defn- nice-string-part2? [s]
  (and
    (overlaps-condition s)
    (repeat-letter-condition s)))

(defn part1 [input] (count (filter nice-string? input)))

(defn part2 [input] (count (filter nice-string-part2? input)))

(def input (str/split-lines (slurp "resources/day05_input.txt")))

(println "Day 05 - 1: " (part1 input)) ; 238
(println "Day 05 - 2: " (part2 input)) ; 69
