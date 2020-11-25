docker run -i -t -p 50070:50070 -p 7777:9000 -p 9875:8088 -p 8040:8040 -p 8042:8042  -p 49707:49707  -p 50010:50010  -p 50075:50075  -p 50090:50090 sequenceiq/hadoop-docker:2.6.0 /etc/bootstrap.sh -bash


使用镜像sequenceiq/hadoop-docker:2.6.0以交互模式启动一个容器,在容器内执行/bin/bash命令。