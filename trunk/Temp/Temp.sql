select
        distinct collaborat1_.* 
    from
        DTP_DELEGANT_PERIMETRE delegantpe0_ 
    inner join
        COL_COLLABORATEUR collaborat1_ 
            on delegantpe0_.col_id=collaborat1_.col_id 
    where
        (
            collaborat1_.cot_id in (
                select
                    delegation2_.dem_col_type 
                from
                    DEM_DELEGATION_MODEL delegation2_,
                    COT_COLLABORATEUR_TYPE collaborat3_ 
                where
                    delegation2_.dem_col_type=collaborat3_.cot_id 
                    and delegation2_.dna_id=47 
                    and delegation2_.ent_id='015'
            )
        ) 
        --and (delegantpe0_.per_id in ('BYEFE_UO___________0000000002')  or delegantpe0_.per_id='BYEFE') 
        and collaborat1_.col_isDelegant=1 
        and collaborat1_.ent_id='015' 
    order by
        collaborat1_.col_lastname,
        collaborat1_.col_firstname
            
WITH TreeData (per_id, per_Name, Level, per_parent_id) AS
(
	SELECT per_Id, per_Name, 0, per_parent_id
    FROM PER_PERIMETRE
    WHERE per_parent_id in (select per_id FROM PER_PERIMETRE where upper(per_name) like upper('DV CONSTRUCTION'))
    --and ent_id='015'
    
    UNION ALL
    SELECT child.per_Id, child.per_Name, Level + 1, child.per_parent_id
    FROM PER_PERIMETRE child INNER JOIN TreeData ON child.per_parent_id = TreeData.per_Id
)
--update DEL_DELEGATION set del_endDate = '2015-12-25' where del_id in (
select del_id
,*
    from
        DEL_DELEGATION delegation0_ cross 
    join
        PER_PERIMETRE perimetre1_ 
    where
        delegation0_.per_id=perimetre1_.per_id 
        and perimetre1_.ent_id='015' 
        and delegation0_.per_id in (select per_id from TreeData)
--)

--Use variable
declare @dem_group int
select @dem_group = 59
select * from FIR_FIELD_RULE where dem_group = @dem_group

select distinct fie_id from FIE_FIELD where ent_id='015'
--if not exit
select * from FIR_FIELD_RULE where dem_group=@dem_group and fie_id=78 and fir_displayed=1
insert into FIR_FIELD_RULE (dem_group, fir_displayed, fie_id) values (@dem_group, 1, 78) --(78,107,76,106,110,104,80,102)

  
            