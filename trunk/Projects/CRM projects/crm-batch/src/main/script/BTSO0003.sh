#!/bin/sh

# BTSO0003 BTSO0002.sh

# batch.sh への起動引数を記述する。
ACTION=
METHOD=
PROPERTIES=""

# バッチ起動
ABS_PATH=`readlink -f $0`
BINDIR=`dirname $ABS_PATH`
${BINDIR}/batch.sh $ACTION $METHOD $PROPERTIES

RESULT=$?

exit $RESULT
