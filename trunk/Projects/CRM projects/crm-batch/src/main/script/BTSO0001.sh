#!/bin/sh

# BTSO0001 SolrIndex更新バッチ

# batch.sh への起動引数を記述する。
ACTION=DemoItemUpdateAction
METHOD=updateMasterServer
PROPERTIES=""

# バッチ起動
ABS_PATH=`readlink -f $0`
BINDIR=`dirname $ABS_PATH`
${BINDIR}/batch.sh $ACTION $METHOD $PROPERTIES

RESULT=$?

exit $RESULT