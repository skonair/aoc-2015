(ns aoc-2015.day02
  (require [clojure.string :as str]))

(defn wrapped-surface [[x y z]]
  (let [[a b c] (sort [(* x y) (* x z) (* y z)])]
    (+ (* 3 a) (* 2 b) (* 2 c))))

(defn ribbon [[x y z]]
  (let [[a b c] (sort [x y z])]
    ( + (* 2 (+ a b)) (* a b c)))) 

(defn- parse-line [l] (map #(Integer. %) (str/split l #"x")))
(defn parse-file [filename] (map parse-line (str/split-lines (slurp filename))))

(def input (parse-file "resources/day02_input.txt"))

(def total-wrapping (apply + (map wrapped-surface input)))
(def total-ribbon (apply + (map ribbon input)))

(println "Day 02 - 1: " total-wrapping) ; 1598415
(println "Day 02 - 2: " total-ribbon) ; 3812909
