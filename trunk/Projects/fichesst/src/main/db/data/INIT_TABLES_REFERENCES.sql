-- Ref_DDE_Agrement
insert into dbo.Ref_DDE_Agrement values ('A Faire')
insert into dbo.Ref_DDE_Agrement values ('En cours')
insert into dbo.Ref_DDE_Agrement values ('Validée')

-- Ref_Decenale
insert into dbo.Ref_Decenale values ('Décennale nominative reçue')
insert into dbo.Ref_Decenale values ('Décennale nominative non reçue')
insert into dbo.Ref_Decenale values ('Décennale reçue')
insert into dbo.Ref_Decenale values ('Décennale non reçue')

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
insert into dbo.Ref_Transfert_PP values ('MOD')
insert into dbo.Ref_Transfert_PP values ('HA')
insert into dbo.Ref_Transfert_PP values ('TS')
insert into dbo.Ref_Transfert_PP values ('Béton')
insert into dbo.Ref_Transfert_PP values ('Préfa')
insert into dbo.Ref_Transfert_PP values ('Autre MTX')
insert into dbo.Ref_Transfert_PP values ('Maîtrise')
insert into dbo.Ref_Transfert_PP values ('Encadrement')
insert into dbo.Ref_Transfert_PP values ('Autre Fch')
insert into dbo.Ref_Transfert_PP values ('MOI')
insert into dbo.Ref_Transfert_PP values ('Location')
insert into dbo.Ref_Transfert_PP values ('Achat')
insert into dbo.Ref_Transfert_PP values ('Conso')

-- Ref_Type_Budj_Conf
insert into dbo.Ref_Type_Budj_Conf values ('Obj')
insert into dbo.Ref_Type_Budj_Conf values ('+/-')
insert into dbo.Ref_Type_Budj_Conf values ('RD')
insert into dbo.Ref_Type_Budj_Conf values ('TS')

-- Ref_Type_Lot
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('STGO', 1, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Clos Ouvert', 2, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('CET', 3, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('CEA', 4, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Autre', 5, 1)
insert into dbo.Ref_Type_Lot (nom, ordre, inclus_dans_total) values ('Honoraires', 6, 0)

-- Ref_Type_Mch_AV
insert into dbo.Ref_Type_Mch_AV values ('MCH')
insert into dbo.Ref_Type_Mch_AV values ('MCH1')
insert into dbo.Ref_Type_Mch_AV values ('MCH2')
insert into dbo.Ref_Type_Mch_AV values ('MCH3')
insert into dbo.Ref_Type_Mch_AV values ('MCH4')
insert into dbo.Ref_Type_Mch_AV values ('MCH5')
insert into dbo.Ref_Type_Mch_AV values ('AV1')
insert into dbo.Ref_Type_Mch_AV values ('AV2')
insert into dbo.Ref_Type_Mch_AV values ('AV3')
insert into dbo.Ref_Type_Mch_AV values ('AV4')
insert into dbo.Ref_Type_Mch_AV values ('AV5')
insert into dbo.Ref_Type_Mch_AV values ('AV6')
insert into dbo.Ref_Type_Mch_AV values ('AV7')
insert into dbo.Ref_Type_Mch_AV values ('AV8')
insert into dbo.Ref_Type_Mch_AV values ('AV9')
insert into dbo.Ref_Type_Mch_AV values ('AV10')
insert into dbo.Ref_Type_Mch_AV values ('RAT')
