(ns advent-of-code-2020.day2-test
  (:require [advent-of-code-2020.day2 :refer :all]
            [clojure.test :refer :all]))

(def test-input
  ["1-3 a: abcde"
   "1-3 b: cdefg"
   "2-9 c: ccccccccc"])

(deftest test-day2-1
  (testing "valid passwords"
    (is (valid-password? "1-3 a: abcde"))
    (is (not (valid-password? "2-3 a: abcde")))
    (is (count-valid-passwords test-input))))

(deftest test-day2-2
  (testing "valid passwords"
    (is (real-valid-password? (first test-input)))
    (is (not (real-valid-password? (second test-input))))
    (is (not (real-valid-password? (get test-input 2))))))
