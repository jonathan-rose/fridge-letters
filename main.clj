(ns main
  (:require [clojure.string :as str]))

(def fridge "aaaaaabbcccddddeeeeeeffgghhhiiiiijjkkllllmmmmmnnnoooopppqqrrrrssssttttuuuuvvwxxyyzz")

(def test-letters "All big cats do eat fresh gazelle.")

(defn leftover-letters
  "Takes two strings.
   Subtracts the frequency of letters in the second string from the frequency of letters in the first. 
   Prints the second string and what letters remain of the first."
  [letters sentence]
  (let [frequency (let [letters-freq   (-> letters
                                           (str/lower-case)
                                           (str/replace #"[^a-z]" "")
                                           (vec)
                                           (frequencies))
                        sentence-freq  (-> sentence
                                           (str/lower-case)
                                           (str/replace #"[^a-z]" "")
                                           (vec)
                                           (frequencies))
                        remaining-freq (merge-with - letters-freq sentence-freq)] remaining-freq)]
    (if (some #(neg? %) (vals frequency))
      (println "Insufficient letters on the fridge.")
      (do (println "Your sentence is: " sentence)
          (println "Remaining letters: " (apply str (for [[char count] frequency]
                                                      (apply str (repeat count char)))))))))
