(ns advent-of-code-2020.day1-test
  (:require [advent-of-code-2020.day1 :refer :all]
            [clojure.test :refer :all]))

(def input
  [1721
   979
   366
   299
   675
   1456])

(deftest test1
  (testing "FIXME, I fail."
    (is (= (some->> (day1-1 input)
                    (first)
                    (apply *))
           514579))))
