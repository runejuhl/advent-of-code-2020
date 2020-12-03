(ns advent-of-code-2020.day3)
(comment
  "--- Day 3: Toboggan Trajectory ---

With the toboggan login problems resolved, you set off toward the airport. While
travel by toboggan might be easy, it's certainly not safe: there's very minimal
steering and the area is covered in trees. You'll need to see which angles will
take you near the fewest trees.

Due to the local geology, trees in this area only grow on exact integer
coordinates in a grid. You make a map (your puzzle input) of the open
squares (.) and trees (#) you can see. For example:

..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#

These aren't the only trees, though; due to something you read about once
involving arboreal genetics and biome stability, the same pattern repeats to the
right many times:

..##.........##.........##.........##.........##.........##.......  --->
#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........#.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...##....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->

You start on the open square (.) in the top-left corner and need to reach the
bottom (below the bottom-most row on your map).

The toboggan can only follow a few specific slopes (you opted for a cheaper
model that prefers rational numbers); start by counting all the trees you would
encounter for the slope right 3, down 1:

From your starting position at the top-left, check the position that is right 3
and down 1. Then, check the position that is right 3 and down 1 from there, and
so on until you go past the bottom of the map.

The locations you'd check in the above example are marked here with O where
there was an open square and X where there was a tree:

..##.........##.........##.........##.........##.........##.......  --->
#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
.#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........X.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...#X....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->

In this example, traversing the map using this slope would cause you to
encounter 7 trees.

Starting at the top-left corner of your map and following a slope of right 3 and
down 1, how many trees would you encounter?")

(defn parse-input
  [s]
  (let [grid (clojure.string/split-lines s)]
    {:grid    grid
     :rows    (count grid)
     :columns (count (first grid))}))

;; (defn pick-path
;;   [{:keys [grid columns]} {:keys [down right]}]
;;   (let [step (+ right (* down columns))]
;;     (take-nth step grid)))

;; (defn pick-path
;;   [{:keys [grid columns rows]} {:keys [down right]}]
;;   (let [step (+ right (* down columns))]
;;     (prn :start)
;;     (loop [position step
;;            acc      nil]
;;       (if (= (count acc) rows)
;;         acc
;;         (do
;;           (prn position)
;;           (recur
;;             (if (>= position columns)
;;               (rem (+ position step) columns)
;;               position)
;;             (conj acc (nth grid position))))))))

(defn pick-path
  [{:keys [grid columns rows]} {:keys [down right]}]
  (loop [row    down
         column right
         acc    nil]
    (if (>= row rows)
      acc
      (recur
       (+ row down)
       (rem (+ column right) columns)
       (conj acc (nth (nth grid row) column))))))

(defn count-trees
  [s]
  (count (filter (partial = \#) s)))

(defn problem1
  ([input]
   (problem1 input 3 1))
  ([input right down]
   (-> (parse-input input)
       (pick-path {:down down :right right})
       (count-trees))))

(defn problem2
  [input routes]
  (apply *
         (map #(apply (partial problem1 input) %)
              routes)))

(def input
  (slurp (clojure.java.io/resource "day3.txt")))

(comment
  (problem1 input)
  ;; => 289

  (problem2 input [[1 1]
                   [3 1]
                   [5 1]
                   [7 1]
                   [1 2]])
  ;; => 5522401584
)
