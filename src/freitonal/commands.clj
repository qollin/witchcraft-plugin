(ns freitonal.commands
  (:require [lambdaisland.witchcraft :as wc]
            [lambdaisland.witchcraft.events :as e]))

(defn command [name]
  (let [inv (wc/make-inventory {:size 9})
        sword (wc/item-stack :diamond-sword)
        axe (wc/item-stack :diamond-axe)]
    (wc/set-display-name sword "MEGASWORD")
    (wc/set-display-name axe "GIGAAXE")
    (wc/set-lore sword ["Als der Hauke sich beschwerte" "hab ich das dann geaendert"])
    (wc/set-lore axe ["Bla" "Blub"])
    (wc/add-inventory inv sword)
    (wc/add-inventory inv axe)

    (e/listen! 
     :inventory-click
     ::xxx
     (fn [x]
       (let [clicked-item (:currentItem x)]
         (when (= clicked-item sword)
           (wc/open-inventory (wc/player) (wc/make-inventory {:size 54}))) 
         (e/cancel! x))))
    
    (wc/open-inventory (wc/player) inv)))

