insert into user values
    (null, "Alice", "aaaa"),
    (null, "Bob", "bbbb"),
    (null, "Camille", "cccc")
;

insert into category values
    (null, "Livre"),
    (null, "Musique"),
    (null, "Bricolage")
;

insert into article values
    (null, 1, 11.11, "Astronomie illustrée", 1, "À la conquète de l'univers !"),
    (null, 2, 22.22, "Arctic Monkeys - Humburg", 2, "Un des plus grands succès du groupe."),
    (null, 3, 33.33, "Marteau", 3, "Conçu pour planter."),
    (null, 1, 44.44, "La psychologie pour les nuls", 1, "Apprenez l'art de la manipulation."),
    (null, 2, 11.11, "Queen - Radio Gaga", 2, null)
;

insert into cart values
    (null, 1, true, null),
    (null, 1, false, null)
;

insert into cart_contains_article values
    (1, 1, 1),
    (1, 4, 2),
    (2, 3, 3),
    (2, 2, 4)
;
