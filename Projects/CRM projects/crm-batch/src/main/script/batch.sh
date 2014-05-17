#!/bin/sh

# バッチ起動シェル(共通)

# 設定ファイル読み込み
. batch.conf

# クラスパス
CLASSPATH=${BATCH_LIBRARY_PATH}
export CLASSPATH

# 引数1: アクションクラス名
$ACTION = shift
# 引数2: 実行するメソッド名
$METHOD = shift
# 引数3以降はプロパティ指定
for p in $@
do
    ${PARAMETERS}=${PARAMETERS}' -p '$p
done

# バッチ実行
${JAVA_HOME}/bin/java {JVM_OPT} -cp ${CLASSPATH} -cl ${BATCH_MAIN_CLASS} -c ${BATCH_ACTION_ROOT}.${ACTION} -m ${METHOD} $PARAMETERS

RESULT=$?

exit $RESULT
