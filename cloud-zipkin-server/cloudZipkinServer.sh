#openzipkin/zipkin 镜像 下载，端口映射，后台运行
# http://localhost:9411/zipkin/
docker pull openzipkin/zipkin
docker run -d -p 9411:9411 openzipkin/zipkin