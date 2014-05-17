USE [CLIENTS]
GO

/****** Object:  UserDefinedFunction [dbo].[getClientLevelFromParentClient]    Script Date: 03/10/2014 15:57:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER FUNCTION [dbo].[getClientLevelFromParentClient] (@childrentClientId VARCHAR(7))
RETURNS numeric(7) 
AS
BEGIN
  DECLARE @level numeric(7) ;

  WITH ALL_LEVEL(childrenId, parentsId, clientLevel) AS
	(
	SELECT  o.client_id AS childrenId, po.client_id AS parentsId, 2 As clientLevel
	FROM         dbo.offices AS o
	  INNER JOIN Landscape.dbo.LBC_MASTER AS lm ON o.lbc_code = lm.OFFICE_ID
					  AND o.delete_flag = 0 AND o.sort_order = 1
					  AND lm.OFFICE_ID <> lm.TOP_HEAD_OFFICE_ID
	  INNER JOIN dbo.offices AS po ON po.lbc_code = lm.TOP_HEAD_OFFICE_ID
					  AND po.sort_order = 1 AND po.delete_flag = 0 
	  INNER JOIN  dbo.clients AS c ON c.id = po.client_id
	WHERE
	  lm.TOP_HEAD_OFFICE_ID != '' 
	  AND o.client_id = @childrentClientId --'2008748'--'2008748'

	UNION ALL 

	SELECT   o.client_id AS childrenId, po.client_id AS parentsId, (al.clientLevel + 1) As clientLevel
	FROM         dbo.offices AS o 
	  INNER JOIN Landscape.dbo.LBC_MASTER AS lm ON o.lbc_code = lm.OFFICE_ID
					  AND o.delete_flag = 0 AND o.sort_order = 1
					  AND lm.OFFICE_ID <> lm.TOP_HEAD_OFFICE_ID
	  INNER JOIN dbo.offices AS po ON po.lbc_code = lm.TOP_HEAD_OFFICE_ID
					  AND po.sort_order = 1 AND po.delete_flag = 0 
	  INNER JOIN  dbo.clients AS c ON c.id = po.client_id

	  INNER JOIN ALL_LEVEL AS al ON o.client_id = al.parentsId
	WHERE
	  lm.TOP_HEAD_OFFICE_ID != '' 
	) 
				
  SELECT @level = MAX(AL.clientLevel)   
  FROM ALL_LEVEL AL
  OPTION ( OPTIMIZE FOR (@childrentClientId UNKNOWN, @level UNKNOWN) )
  ;
  RETURN @level
END;
GO
