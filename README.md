
<h1>Srping Maven Sample Server</h1>

## Introduction
- spring maven sample
- logging
- swagger
- exception handler

## Project spec
- java 11
- Spring boot 2.7.10
- maven

## Requirements
- java JDK 11+
- Git

## Develop tool
- Visual Studio Code 1.73.1
- plugins: Extension Pack for Java
- plugins: Spring Boot Extension Pack
- plugins: Gradle for Java
- plugins: Git History

## Install(for local)
- 환경변수 set(.vscode/launch.json). 디폴트는 local. 변경하고 싶다면 args를 수정
```json
{
    "configurations": [
        {
            "type": "java",
            "name": "Spring Boot-SpringMavenSampleApplication<spring-maven-sample>",
            "request": "launch",
            "cwd": "${workspaceFolder}",
            "mainClass": "com.sehoon.springmavensample.SpringMavenSampleApplication",
            "projectName": "spring-maven-sample",
            "args": "--spring.profiles.active=local",
            "envFile": "${workspaceFolder}/.env"
        }
    ]
}
```
- 실행. spring boot dashboard extention을 사용하여 run


## Install(for linux)
- Get the project code
```sh
git clone https://github.com/sehoone/spring-maven-sample.git
```
- Installation dependencies
```sh
cd spring-maven-sample
./mvnw inatall
```
- run boot jar
```sh
nohup java -Dspring.profiles.active=dev -Dserver.port=8443 -jar target/spring-maven-sample-0.0.1-SNAPSHOT.jar &
```

## Source Structure
```
├── nft-exhibit-server        # root
  ├── gradle                  # gradle config
  ├── src                     # source Directory
    ├── main
      ├── net/openobject/nuguproxyserver
        ├── common            # common package
        ├── config            # common config
        ├── module            # 업무별 module
          ├── 업무            # 업무별 패키지 생성
            ├── controller    # controller. Presentation Layer
            ├── service       # service. Business Layer
            ├── repository    # repository. Persistence Layer
            ├── domain        # domain. database table domain
      ├── resource            # resource. application propertie 정의
    ├── test                  # test source root
  ├── .gitignore              # gitignore
  ├── build.gradle            # gradle build config. plugins, dependency, etc
  ├── gradlew                 # gradlew
  ├── gradlew.bat             # gradlew.bat
  ├── README.md               # README.md
  ├── settings.gradle         # settings.gradle
```