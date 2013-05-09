begin tran

/* Mise à jour des type de marchés avenants */
/* ---------------------------------------- */
IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH', 1)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH1')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH1', 2)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH2')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH2', 3)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH3')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH3', 4)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH4')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH4', 5)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='MCH5')
begin
	INSERT INTO Ref_Type_Mch_AV values('MCH5', 6)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV1')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV1', 7)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV2')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV2', 8)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV3')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV3', 9)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV4')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV4', 10)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV5')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV5', 11)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV6')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV6', 12)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV7')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV7', 13)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV8')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV8', 14)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV9')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV9', 15)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV10')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV10', 16)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV11')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV11', 17)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV12')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV12', 18)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV13')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV13', 19)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV14')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV14', 20)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV15')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV15', 21)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV16')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV16', 22)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV17')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV17', 23)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV18')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV18', 24)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV19')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV19', 25)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='AV20')
begin
	INSERT INTO Ref_Type_Mch_AV values('AV20', 26)
end 

IF NOT EXISTS(SELECT * FROM Ref_Type_Mch_AV WHERE UPPER(LIBELLE)='RAT')
begin
	INSERT INTO Ref_Type_Mch_AV values('RAT', 27)
end 

UPDATE Ref_Type_Mch_AV SET ORDRE=1 WHERE UPPER(LIBELLE)='MCH';
UPDATE Ref_Type_Mch_AV SET ORDRE=2 WHERE UPPER(LIBELLE)='MCH1';
UPDATE Ref_Type_Mch_AV SET ORDRE=3 WHERE UPPER(LIBELLE)='MCH2';
UPDATE Ref_Type_Mch_AV SET ORDRE=4 WHERE UPPER(LIBELLE)='MCH3';
UPDATE Ref_Type_Mch_AV SET ORDRE=5 WHERE UPPER(LIBELLE)='MCH4';
UPDATE Ref_Type_Mch_AV SET ORDRE=6 WHERE UPPER(LIBELLE)='MCH5';
UPDATE Ref_Type_Mch_AV SET ORDRE=7 WHERE UPPER(LIBELLE)='AV1';
UPDATE Ref_Type_Mch_AV SET ORDRE=8 WHERE UPPER(LIBELLE)='AV2';
UPDATE Ref_Type_Mch_AV SET ORDRE=9 WHERE UPPER(LIBELLE)='AV3';
UPDATE Ref_Type_Mch_AV SET ORDRE=10 WHERE UPPER(LIBELLE)='AV4';
UPDATE Ref_Type_Mch_AV SET ORDRE=11 WHERE UPPER(LIBELLE)='AV5';
UPDATE Ref_Type_Mch_AV SET ORDRE=12 WHERE UPPER(LIBELLE)='AV6';
UPDATE Ref_Type_Mch_AV SET ORDRE=13 WHERE UPPER(LIBELLE)='AV7';
UPDATE Ref_Type_Mch_AV SET ORDRE=14 WHERE UPPER(LIBELLE)='AV8';
UPDATE Ref_Type_Mch_AV SET ORDRE=15 WHERE UPPER(LIBELLE)='AV9';
UPDATE Ref_Type_Mch_AV SET ORDRE=16 WHERE UPPER(LIBELLE)='AV10';
UPDATE Ref_Type_Mch_AV SET ORDRE=17 WHERE UPPER(LIBELLE)='AV11';
UPDATE Ref_Type_Mch_AV SET ORDRE=18 WHERE UPPER(LIBELLE)='AV12';
UPDATE Ref_Type_Mch_AV SET ORDRE=19 WHERE UPPER(LIBELLE)='AV13';
UPDATE Ref_Type_Mch_AV SET ORDRE=20 WHERE UPPER(LIBELLE)='AV14';
UPDATE Ref_Type_Mch_AV SET ORDRE=21 WHERE UPPER(LIBELLE)='AV15';
UPDATE Ref_Type_Mch_AV SET ORDRE=22 WHERE UPPER(LIBELLE)='AV16';
UPDATE Ref_Type_Mch_AV SET ORDRE=23 WHERE UPPER(LIBELLE)='AV17';
UPDATE Ref_Type_Mch_AV SET ORDRE=24 WHERE UPPER(LIBELLE)='AV18';
UPDATE Ref_Type_Mch_AV SET ORDRE=25 WHERE UPPER(LIBELLE)='AV19';
UPDATE Ref_Type_Mch_AV SET ORDRE=26 WHERE UPPER(LIBELLE)='AV20';
UPDATE Ref_Type_Mch_AV SET ORDRE=27 WHERE UPPER(LIBELLE)='RAT';

/* Mise à jour du type de lot "Clos Ouvert" en "Clos Couvert" */
/* ---------------------------------------------------------- */
UPDATE  Ref_Type_Lot set nom = 'Clos Couvert' where nom = 'Clos Ouvert'


/* Ajout d'une décennale "Non concerné" et d'un agrément "Non concerné" */
/* --------------------------------------------------------------------- */
IF NOT EXISTS(SELECT * FROM Ref_DDE_Agrement WHERE LIBELLE='Non concerné')
begin
	INSERT INTO dbo.Ref_DDE_Agrement values('Non concerné')
end 
IF NOT EXISTS(SELECT * FROM Ref_Decenale WHERE LIBELLE='Non concerné')
begin
	INSERT INTO dbo.Ref_Decenale values ('Non concerné')
end

commit tran
