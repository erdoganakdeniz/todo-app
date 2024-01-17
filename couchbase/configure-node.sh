set -m

 /entrypoint.sh couchbase-server &

 sleep 15

 # Setup initial cluster/ Initialize Node
 couchbase-cli cluster-init -c 127.0.0.1 --cluster-name "todo" --cluster-username "Administrator" \
 --cluster-password "123456" --services data,index,query,fts --cluster-ramsize 256 --cluster-index-ramsize 256 \
 --cluster-fts-ramsize 256 --index-storage-setting default \

 # Setup Administrator username and password
 curl -v http://127.0.0.1:8091/settings/web -d port=8091 -d username="Administrator" -d password="123456"


 sleep 15

 # Setup Bucket
 couchbase-cli bucket-create -c 127.0.0.1:8091 --username "Administrator" \
 --password "123456" --bucket "todo-app" --bucket-type couchbase \
 --bucket-ramsize 256

 fg 1