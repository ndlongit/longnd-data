--Change DB Owner
USE VIP_15052013
exec sp_changedbowner 'sa'

-- Insert new User
INSERT [USR_USER] 
([usr_username], [ent_id], [col_id], [usr_lastname], [usr_firstname], [dmn_id], [usr_password], [per_id]) VALUES 
(N'long.nguyen', N'327', NULL, N'Long', N'Nguyen', 7, NULL, NULL);

select perimetret0_.pty_id as pty1_26_2_, perimetret0_.ent_id as ent4_26_2_, perimetret0_.pty_isSubdelegable as pty2_26_2_, perimetret0_.pty_name as pty3_26_2_, entite1_.ent_id as ent1_28_0_, entite1_.ent_default_lang as ent3_28_0_, entite1_.ent_name as ent2_28_0_, language2_.lag_id as lag1_32_1_, language2_.lag_code as lag2_32_1_, language2_.lag_isDefault as lag3_32_1_, language2_.lag_name as lag4_32_1_ 
from PTY_PERIMETRE_TYPE perimetret0_ inner join ENT_ENTITE entite1_ 
on perimetret0_.ent_id=entite1_.ent_id left 
outer join LAG_LANGUAGE language2_ on entite1_.ent_default_lang=language2_.lag_id 
where perimetret0_.pty_id='0000000056';

-- Select Delegant
select distinct collaborat2_.col_delegantId, collaborat2_.* 
from DTP_DELEGANT_PERIMETRE delegantpe0_ 
inner join COL_COLLABORATEUR collaborat1_ on delegantpe0_.col_id=collaborat1_.col_id 
inner join COL_COLLABORATEUR collaborat2_ on delegantpe0_.col_id=collaborat2_.col_id cross join COL_COLLABORATEUR collaborat3_ 
where delegantpe0_.col_id=collaborat3_.col_id and delegantpe0_.per_id='00002000000' and collaborat3_.col_isDelegant=1 and collaborat3_.ent_id='016' 

select * from DEM_DOM d where d.dem_group = 57;

SELECT TOP 1 [del_id]
      ,[del_delegantNom1]
      ,[del_delegantPrenom1]
      ,[del_delegataire_niveauHierarchique]
      ,[del_delegataireStatut]
      ,[del_delegantStatut]
      ,[del_delegataireQualite]
      ,[del_delegantQualite]
      ,[del_delegantQualite1]
      ,[del_demandeurFirstname]
      ,[del_demandeurLastname]
      ,[del_delegantFirstname]
      ,[del_delegantLastname]
      ,[del_delegantDateEffet]
  FROM [DEL_DELEGATION]
  order by del_id desc;

SELECT TOP 100 *
  FROM [FIE_FIELD]
  where 1=1
  and ent_id = '327'
  --and fie_form_field_id like 'lbl%Statut'
  and fie_db_field in ('delegantPreNom1', '', '', '')
  
insert into FIE_FIELD (fie_form_field_id, fie_label, lag_id, ent_id, fie_db_field, fie_group, fie_order) values ('lblDelegantStatut','Statut du délégant',1,'016','delegantStatut','Délégant', 4);               
               
insert into FIE_FIELD (fie_form_field_id, fie_label, lag_id, ent_id, fie_db_field, fie_group, fie_order) values ('lblDelegataireStatut','Statut du délégataire',1,'016','delegataireStatut','Délégataire', 5);
              
select distinct collaborat2_.col_qualiteDelegant, collaborat2_.* 
from DTP_DELEGANT_PERIMETRE delegantpe0_ 
inner join COL_COLLABORATEUR collaborat1_ on delegantpe0_.col_id=collaborat1_.col_id 
inner join COL_COLLABORATEUR collaborat2_ on delegantpe0_.col_id=collaborat2_.col_id 
cross join COL_COLLABORATEUR collaborat3_ 
where delegantpe0_.col_id=collaborat3_.col_id and delegantpe0_.per_id='00002000000' and collaborat3_.col_isDelegant=1 and collaborat3_.ent_id='016'

-- update DEL_DELEGATION set del_delegantQualite1 = 'Test' where del_id=1094;

-- Sellect Collaboratuer
SELECT TOP 1000 [col_id]
      ,[col_delegantId]
      ,[col_qualiteColDelegant]
      ,[col_qualiteDelegant]
      ,[col_civilite]
      ,[col_firstname]
      ,[col_lastname]
      ,[col_nationality]
      ,[col_dateEntree]
      ,[col_dateNaissance]
      ,[col_operations]
  FROM [COL_COLLABORATEUR]
  where col_lastname like '%MOULIN%';
  
  select documentmd0_.* 
  from DOM_DOCUMENT_MODEL documentmd0_ 
  left outer join ENT_ENTITE entite1_ on documentmd0_.ent_id=entite1_.ent_id 
  left outer join LAG_LANGUAGE language2_ on entite1_.ent_default_lang=language2_.lag_id 
  inner join LAG_LANGUAGE language3_ on documentmd0_.lag_id=language3_.lag_id where documentmd0_.dom_id=120;
  
SELECT distinct fie_db_field FROM [FIE_FIELD];

SELECT TOP 100 *
  FROM [FIE_FIELD]
  where 1=1
  and ent_id='015'
  order by [ent_id],[fie_group],[fie_order];

update COL_COLLABORATEUR set col_dateMajRubis = null;
  
 update PER_PERIMETRE set per_name='DV CONSTRUCTION (2)' where per_id='BYEFE_UO___________0000000008';
  
SELECT TOP 100 col_firstname, col_lastname, col_idBycn, cot_id,col_civilite, col_dateMajRubis,col_dateEntree,col_dateNaissance,col_dateSortie,col_dateConseil,col_dateEffet,col_dateDelegation
  FROM [COL_COLLABORATEUR]
  where 1=1
  --and cot_id=1
  --and col_civilite is not null
  --and col_firstname = 'Long'
  order by col_id desc;
  
  insert into COT_COLLABORATEUR_TYPE (cot_name, cot_description, ent_id, cot_group) values ('COL_BYEFE', 'COLLABORATEUR of BYEFE', '015', 2);
  
  update [COL_COLLABORATEUR] set cot_id = 12 where cot_id=1;

insert into [DST_DELEGATION_STATUS] (dst_name, dst_description) values ('H_T','Hemera Statut T')
insert into [DST_DELEGATION_STATUS] (dst_name, dst_description) values ('H_V','Hemera Statut V')

--Duplication Perimeter name
select * from PER_PERIMETRE where per_name in (select per_name from PER_PERIMETRE
group by per_name
having count(per_name) > 1)
order by per_name;
   
WITH TreeData (per_Id, per_Name, Level, per_parent_id) AS
(
	SELECT per_Id, per_Name, 0, per_parent_id
    FROM PER_PERIMETRE
    WHERE per_parent_id IS NULL
    and ent_id='015'
    
    UNION ALL
    SELECT child.per_Id, child.per_Name, Level + 1, child.per_parent_id
    FROM PER_PERIMETRE child INNER JOIN TreeData ON child.per_parent_id = TreeData.per_Id
)

--Select all Delegation of an Entite
select perimetre1_.per_name, delegation0_.* from
        DEL_DELEGATION delegation0_ cross join
        PER_PERIMETRE perimetre1_ 
where
    delegation0_.per_id=perimetre1_.per_id
    and delegation0_.per_id in (SELECT per_Id FROM TreeData)
order by perimetre1_.per_name  

SELECT TOP 1000 [dom_del_id]
      ,[dom_id]
      ,[del_id]
      ,[dom_del_comment]
      ,[dom_del_signed_date]
      ,[dom_signed_filename]
      ,[dom_hemera_lien]
  FROM [DOM_DEL]
  where 1=1
  --and dom_id <> 119
  and dom_signed_filename like 'http://gedeon.bouygues-construction.com%'
  order by dom_del_id desc
  
 SELECT TOP 100 * 
  FROM [DEM_DELEGATION_MODEL]
  where 1=1
  and dem_group=59
  and dem_has_multiple_delegataire = 1