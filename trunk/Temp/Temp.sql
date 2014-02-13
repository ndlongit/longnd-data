CREATE TABLE [Hierarchy](
[ParentCategoryId] CHAR(1) NULL,
[ChildCategoryId] CHAR(1) NOT NULL
);


INSERT INTO Hierarchy
SELECT NULL, 'A' UNION ALL
SELECT 'A', 'B' UNION ALL
SELECT 'A', 'D' UNION ALL
SELECT 'B', 'C' UNION ALL
SELECT 'D', 'C';

WITH CTE AS (
    SELECT 
        ParentCategoryId, ChildCategoryId, 
        CAST(ISNULL(ParentCategoryId,'') + ChildCategoryId AS VARCHAR(255)) [Path] 
    FROM Hierarchy
    WHERE ParentCategoryId IS NULL

    UNION ALL

    SELECT 
        H.ParentCategoryId, H.ChildCategoryId, 
        CAST(C.[Path] + ' > ' + H.ChildCategoryId AS VARCHAR(255)) [Path] 
    FROM Hierarchy H
    INNER JOIN CTE C ON C.ChildCategoryId = H.ParentCategoryId
) SELECT * FROM CTE;