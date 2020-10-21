(ns aoc-2015.day01-test
  (:require [clojure.test :refer :all]
            [aoc-2015.day01 :refer :all]))

(deftest parenthesis-test
  (testing "parenthesis works correct"
    (is (= 0 (parenthesis "(())")))
    (is (= 0 (parenthesis "()()")))
    (is (= 3 (parenthesis "(((")))
    (is (= 3 (parenthesis "(()(()(")))
    (is (= 3 (parenthesis "))(((((")))
    (is (= -1 (parenthesis "())")))
    (is (= -1 (parenthesis "))(")))
    (is (= -3 (parenthesis ")))")))
    (is (= -3 (parenthesis ")())())")))))

(deftest basement-test
  (testing "basement works correct"
    (is (= 1 (basement ")")))
    (is (= 5 (basement "()())")))))
