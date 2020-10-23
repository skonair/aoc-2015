(import java.security.MessageDigest)
(import java.io.ByteArrayOutputStream)

(ns aoc-2015.day04
  (require [clojure.string :as str]))

; your code here

(defn- md5
  [^String s]
  (->> s
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (BigInteger. 1)
       (format "%032x")))

(defn- mine [secret prefix]
  (loop [n 0]
    (if (str/starts-with? (md5 (str secret n)) prefix)
      n
      (recur (inc n)))))

(defn part1 [input] (mine input "00000"))

(defn part2 [input] (mine input "000000"))

(def input (trim (slurp "resources/day04_input.txt"))

(println "Day 04 - 1: " (part1 input)) ; 282749
(println "Day 04 - 2: " (part2 input)) ; 9962624
