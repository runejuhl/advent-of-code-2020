(ns advent-of-code-2020.day5-test
  (:require [advent-of-code-2020.day5 :refer :all]
            [clojure.test :refer :all]))

(deftest test-day5-1
  (is (= (find-seat "BFFFBBFRRR")
         {:row    70
          :column 7
          :id     567}))
  (is (= (find-seat "FFFBBBFRRR")
         {:row    14
          :column 7
          :id     119}))
  (is (= (find-seat "BBFFBBFRLL")
         {:row    102
          :column 4
          :id 820})))
