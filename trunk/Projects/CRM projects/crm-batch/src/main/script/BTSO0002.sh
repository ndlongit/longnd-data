#!/bin/sh

# BTSO0002 SolrIndex更新バッチ(Optimize)

# batch.sh への起動引数を記述する。
ACTION=DemoItemUpdateAction
METHOD=optimizeMasterServer
PROPERTIES=""

# バッチ起動
ABS_PATH=`readlink -f $0`
BINDIR=`dirname $ABS_PATH`
${BINDIR}/batch.sh $ACTION $METHOD $PROPERTIES

RESULT=$?

exit $RESULT
