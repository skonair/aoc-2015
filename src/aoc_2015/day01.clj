(ns aoc-2015.day01)

(defn parenthesis [text]
  (let [opening (count (filter #{\(} text))
        closing (count (filter #{\)} text))]
    (- opening closing)))

(defn basement [text]
  (loop [[ht & rt] text
         cnt 0 
         lvl 0]
    (if (= -1 lvl)
      cnt
      (recur rt (inc cnt) (if (= ht \() (inc lvl) (dec lvl))))))

(def input (slurp "resources/day01_input.txt"))

(println "Day 01 - 1: " (parenthesis input))
(println "Day 01 - 2: " (basement input))

