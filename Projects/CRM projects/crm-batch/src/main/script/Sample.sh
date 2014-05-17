#!/bin/sh

# バッチ起動シェル

# batch.sh への起動引数を記述する。
ACTION=SampleAction
METHOD=index
PROPERTIES="inboxSubDirName=if interfaceFileName=sample.csv jobId=AaBb001"

# バッチ起動
./batch.sh $ACTION $METHOD $PROPERTIES

RESULT=$?

exit $RESULT
