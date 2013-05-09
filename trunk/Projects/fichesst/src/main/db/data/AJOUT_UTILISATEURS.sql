begin tran

insert into [dbo].[UTILISATEUR_GRP]([ID_UTILISATEUR_GRP], [B_ADMIN] ,[IDENTIFIANT])
  values (1, 1, 'bycn\sa.clement')
  
  commit tran