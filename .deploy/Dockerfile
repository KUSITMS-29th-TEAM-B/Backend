FROM amazoncorretto:17-alpine-jdk

# JAR_FILE 변수에 값을 저장
ARG JAR_FILE=/Api-Module/build/libs/Api-Module-0.0.1-SNAPSHOT.jar
ARG KEY_STORE=/Api-Module/src/main/resources/keystore.p12

# 변수에 저장된 것을 컨테이너 실행시 이름을 app.jar파일로 변경하여 컨테이너에 저장
COPY ${JAR_FILE} app.jar
COPY ${KEY_STORE} keystore.p12
# 빌드된 이미지가 run될 때 실행할 명령어
ENTRYPOINT ["java","-jar","app.jar"]
