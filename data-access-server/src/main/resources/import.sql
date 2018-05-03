INSERT INTO pf_client (pseudo, encrypted_pass, first_name, last_name) VALUES ('ncna', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2', 'Thibault', 'BOURGEOIS');
INSERT INTO card (card_num, card_pass) VALUES ('1234567890', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2');

INSERT INTO status (id_status,label) VALUES (1, 'Agriculteurs exploitants'), (2, 'Artisans, commerçants et chefs d entreprise'),(3, 'Cadres et professions intellectuelles supérieures'),(4, 'Professions Intermédiaires'),(5, 'Employés'),(6, 'Ouvriers'),(7, 'Retraités'),(8,'Autres personnes sans activité professionnelle');

INSERT INTO type_card (id_cardtype,label) VALUES (1, 'Master Card'), (2, 'Visa'),(3, 'Visa Electron');

-- DO NOT TOUCH, IS FOR THIBAULT. KISS KISS BANG BANG...
INSERT INTO customer (id_customer,id_status,last_name,first_name,address,phone) VALUES (111001,1,"GIRAUD","Gilles","4 villa Caroline, 78960","01 23 45 67 89"),(111002,2,"BRENNER","Alexandre","26 rue Chateaubriand, 78180","01 23 45 67 89"),(111003,3,"MICHEL","Olivier","71 rue Saint-Simon, 94000","01 23 45 67 89");
INSERT INTO account (id_account,id_customer,id_cardtype,sold,card_id_fk) VALUES (111001,111001,1,"1111.11",1),(111002,111002,1,"111.22",1),(111003,111003,1,"11.33",1);
INSERT INTO card (card_num, card_pass) VALUES ('1234567890', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2');
INSERT INTO card (card_id, card_num, card_pass) VALUES (111001, '1111111111', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2');
INSERT INTO card (card_id, card_num, card_pass) VALUES (111002, '2222222222', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2');
INSERT INTO card (card_id, card_num, card_pass) VALUES (111003, '3333333333', '1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2');
INSERT INTO link_card_account (card_id, id_account) VALUES (111001,111001),(111002,111002),(111003,111003);
-- YOU CAN NOW TOUCH, IS NOT FOR THIBAULT. KISS KISS BANG BANG...

INSERT INTO type_loan (id_loantype,type, amount_min,amount_max,duration_max,duration_min,rate_max,rate_min,assurance) VALUES (1,'personnel',20000,75000,3,60,1,20,"0.015");
INSERT INTO type_loan (id_loantype,type, amount_min,amount_max,duration_max,duration_min,rate_max,rate_min,assurance) VALUES (2,'immobilier',200000,1000000,60,480,1,40,"0.015");

INSERT INTO region (id_region, name_region) VALUES (1, 'Auvergne-Rhone-Alpes'),(2, 'Bourgogne-Franche-Comté'),(3, 'Bretagne'),(4, 'Centre-Val de Loire');
INSERT INTO region (id_region, name_region) VALUES (5, 'Corse'),(6, 'Grand Est'),(7, 'Hauts-de-France'),(8, 'Île-de-France'),(9, 'Normandie'),(10, 'Nouvelle-Aquitaine');
INSERT INTO region (id_region, name_region) VALUES (11, 'Occitanie'),(12, 'Pays de la Loire'),(13, 'Provence-Alpes-Côte d Azur');
