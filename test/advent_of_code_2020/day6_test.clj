(ns advent-of-code-2020.day6-test
  (:require [advent-of-code-2020.day6 :refer :all]
            [clojure.test :refer :all]))

(def test-input
  "abc

a
b
c

ab
ac

a
a
a
a

b
")

(deftest day6-1
  (is (=
       (count-answers test-input)
       11)))

(deftest day6-2
  (is (=
       (count-answers-2 test-input)
       6)))
