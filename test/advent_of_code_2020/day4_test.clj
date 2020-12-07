(ns advent-of-code-2020.day4-test
  (:require [advent-of-code-2020.day4 :refer :all]
            [clojure.test :refer :all]))

(def test-input
  "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")

(deftest test-day4-1
  (is (= (count-valid-passports test-input)
         2)))

(deftest test-day4-attribute-validation
  (testing "byr"
    (is (not (password-attribute-validation ["byr" 1919])))
    (is (password-attribute-validation ["byr" 1920]))
    (is (password-attribute-validation ["byr" 1962]))
    (is (password-attribute-validation ["byr" 2002]))
    (is (not (password-attribute-validation ["byr" 2003]))))

  (testing "iyr"
    (is (not (password-attribute-validation ["iyr" 1919])))
    (is (not (password-attribute-validation ["iyr" 1920])))
    (is (not (password-attribute-validation ["iyr" 1962])))
    (is (password-attribute-validation ["iyr" 2010]))
    (is (password-attribute-validation ["iyr" 2020]))
    (is (not (password-attribute-validation ["iyr" 2021]))))

