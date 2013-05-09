-- Ref_DDE_Agrement
insert into dbo.Ref_DDE_Agrement values ('A Faire')
insert into dbo.Ref_DDE_Agrement values ('En cours')
insert into dbo.Ref_DDE_Agrement values ('Validée')
insert into dbo.Ref_DDE_Agrement values ('Non concerné')

-- Ref_Decenale
insert into dbo.Ref_Decenale values ('Décennale nominative reçue')
insert into dbo.Ref_Decenale values ('Décennale nominative non reçue')
insert into dbo.Ref_Decenale values ('Décennale reçue')
insert into dbo.Ref_Decenale values ('Décennale non reçue')
insert into dbo.Ref_Decenale values ('Non concerné')

-- [Ref_DGD_Presente]
insert into dbo.Ref_DGD_Presente values ('Oui')
insert into dbo.Ref_DGD_Presente values ('Non')

-- Ref_Mode_Paiement
insert into dbo.Ref_Mode_Paiement values ('30 jours fin de mois')
insert into dbo.Ref_Mode_Paiement values ('45 jours fin de mois')

-- Ref_Statut
insert into dbo.Ref_Statut (libelle, info_libelle) values ('VR', 'Validé régularisé')
insert into dbo.Ref_Statut (libelle, info_libelle) values ('VN', 'Validé non régularisé')
insert into dbo.Ref_Statut (libelle, info_libelle) values ('EC', 'En cours')
insert into dbo.Ref_Statut (libelle, info_libelle) values ('AL', 'A l’étude')

-- Ref_Transfert_PP
insert into dbo.Ref_Transfert_PP values ('MOD',1)
insert into dbo.Ref_Transfert_PP values ('HA',2)
insert into dbo.Ref_Transfert_PP values ('TS',3)
insert into dbo.Ref_Transfert_PP values ('Béton',4)
insert into dbo.Ref_Transfert_PP values ('Préfa',5)
insert into dbo.Ref_Transfert_PP values ('Autre MTX',6)
insert into dbo.Ref_Transfert_PP values ('Maîtrise',7)
insert into dbo.Ref_Transfert_PP values ('Encadrement',8)
insert into dbo.Ref_Transfert_PP values ('Autre Fch',9)
insert into dbo.Ref_Transfert_PP values ('MOI',10)
insert into dbo.Ref_Transfert_PP values ('Location',11)
insert into dbo.Ref_Transfert_PP values ('Achat',12)
insert into dbo.Ref_Transfert_PP values ('Conso',13)

-- Ref_Type_Budj_Conf
insert into dbo.Ref_Type_Budj_Conf values ('Obj')
insert into dbo.Ref_Type_Budj_Conf values ('+/-')
insert into dbo.Ref_Type_Budj_Conf values ('RD')
insert into dbo.Ref_Type_Budj_Conf values ('TS')

-- Ref_Type_Lot
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('STGO', 1, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Clos Couvert', 2, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('CET', 3, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('CEA', 4, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Autres', 5, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Honoraires', 6, 0)

-- Ref_Type_Mch_AV
insert into dbo.Ref_Type_Mch_AV values ('MCH',1)
insert into dbo.Ref_Type_Mch_AV values ('MCH1',2)
insert into dbo.Ref_Type_Mch_AV values ('MCH2',3)
insert into dbo.Ref_Type_Mch_AV values ('MCH3',4)
insert into dbo.Ref_Type_Mch_AV values ('MCH4',5)
insert into dbo.Ref_Type_Mch_AV values ('MCH5',6)
insert into dbo.Ref_Type_Mch_AV values ('AV1',7)
insert into dbo.Ref_Type_Mch_AV values ('AV2',8)
insert into dbo.Ref_Type_Mch_AV values ('AV3',9)
insert into dbo.Ref_Type_Mch_AV values ('AV4',10)
insert into dbo.Ref_Type_Mch_AV values ('AV5',11)
insert into dbo.Ref_Type_Mch_AV values ('AV6',12)
insert into dbo.Ref_Type_Mch_AV values ('AV7',13)
insert into dbo.Ref_Type_Mch_AV values ('AV8',14)
insert into dbo.Ref_Type_Mch_AV values ('AV9',15)
insert into dbo.Ref_Type_Mch_AV values ('AV10',16)
insert into dbo.Ref_Type_Mch_AV values ('AV11',17)
insert into dbo.Ref_Type_Mch_AV values ('AV12',18)
insert into dbo.Ref_Type_Mch_AV values ('AV13',19)
insert into dbo.Ref_Type_Mch_AV values ('AV14',20)
insert into dbo.Ref_Type_Mch_AV values ('AV15',21)
insert into dbo.Ref_Type_Mch_AV values ('AV16',22)
insert into dbo.Ref_Type_Mch_AV values ('AV17',23)
insert into dbo.Ref_Type_Mch_AV values ('AV18',24)
insert into dbo.Ref_Type_Mch_AV values ('AV19',25)
insert into dbo.Ref_Type_Mch_AV values ('AV20',26)
insert into dbo.Ref_Type_Mch_AV values ('RAT',27)
