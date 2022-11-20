# 빌드/배포가이드

## 1. 프로젝트 기술 스택

**Frontend** 

React 18.2.0

react-redux 8.0.4

CSS3 

JavaScript(ES6) 

HTML5

**Backend** 

SpringBoot 2.7.5

Gradle 7.5.1

Spring Security 

Spring data JPA 

java-JWT 4.0.0 

**Unity**

Unity 2021.3.12.f1

**DB** 

MySQL : 8.0.29

**Server** 

Ubuntu 20.0

## 2. 빌드 설정

**소스코드 클론** 

git clone  `<repo URL>`

**디렉토리 이동** 

cd backend

**gradle 프로젝트 빌드** 

./gradlew build

**생성된 jar 파일 확인** 

cd build/libs ssafy-web-project-1.0-SNAPSHOT.jar

**AWS EC2 서버 설정**

모든 설치 전 실행

패키지 업데이트  apt-get update

**java 설치**

**JRE 설치**

 sudo apt-get install openjdk-11-jre

**JDK 설치** 

sudo apt-get install openjdk-11-jdk

**자바 설치 버전 확인** 

java -version javac -version

**자바 환경변수 설정

1.** vi 에디터 열기 

sudo vi /etc/profile

2.맨 아래 추가 하기

export JAVA_HOME=/user/lib/jvm/java-8-openjdk-amd64 export PATH=$JAVA_HOME/bin/:$PATH export CLASS_PATH=$JAVA_HOME/lib:$CLASS_PATH

3.변경 내용 즉시 적용하기

source /etc/profile

4.재부팅하기

sudo reboot now

**방화벽 설정**

**현재 방화벽 설정 확인** 

sudo ufw status

if ufw == inactive 

**방화벽 설정** 

sudo ufw allow 22 sudo ufw enable

**mysql 설치** 

sudo apt install mysql-server

**mysql 외부 접속 설정**

**디렉토리 이동** 

cd /etc/mysql/mysql.conf.d

**mysqld.cnf 파일 수정** 

sudo vi mysqld.cnf

**설정 변경**

bind-address 127.0.0.1 -> 0.0.0.0

**mysql 접속** 

sudo mysql 

**외부 접속 계정 생성 & 권한 부여** 

create user '계정이름'@'%' identified by '패스워드'; grant all privileges on *.* to '계정이름'@'%' with grant option;

**mysql 접속 포트 추가** 

sudo ufw allow 3306

## 3. 배포

### 1. Nginx 설치 및 파일설정

> 프로젝트는 Nginx를 통해 배포가 진행이 됩니다.
>  Ubuntu 20.04.4 LTS를 기준으로 작성되었습니다.

1.1  Nginx 설치

```bash
sudo apt update
sudo apt upgrade
sudo apt-get install nginx
```

1.2  Nginx Config 파일 설정

```bash
vi /etc/nginx/sites-available/default
```

1.3 /etc/nginx/sites-available/default 설정

```bash
server{
        listen  80;
        server_name k7b301.p.ssafy.io;
        return 301 https://$host$request_uri;
        index index.html index.html;
}

server{
        listen 443 ssl;
        server_name k7b301.p.ssafy.io;

        ssl_certificate /home/ubuntu/certbot/conf/live/k7b301.p.ssafy.io/fullchain.pem;
        ssl_certificate_key /home/ubuntu/certbot/conf/live/k7b301.p.ssafy.io/privkey.pem;
        root /home/ubuntu/S07P31B301/frontend/build;

                index index.html index.htm index.nginx-debian.html;

       location / {
                try_files $uri $uri/ /index.html;
       }

       location /api {
                proxy_pass http://k7b301.p.ssafy.io:8080;
                proxy_redirect off;
                charset utf-8;

                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header Host $host;
        }

        location ~ ^/(swagger|webjars|configuration|swagger-resources|v2|csrf){
                proxy_pass http://k7b301.p.ssafy.io:8080;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }


}

```

1.4 Nginx 재시작

```bash
sudo systemctl restart nginx
```

### 2. HTTPS 세팅

> 비디오, 오디오 기능을 위해 https를 적용하기 위해 인증서를 설정합니다.

2.1 nginx 중지

```bash
sudo service nginx stop
```

2.2 letsencrypt 설치

```bash
sudo apt-get update -y & sudo apt-get install letsencrypt -y
```

2.3 인증서 발급

```bash
sudo letsencrypt certonly --standalone -d k7b301.p.ssafy.io
```

2.4  nginx 재가동

```bash
sudo service nginx restart
```
### 3. 유니티 파일 빌드
3.1 유니티 빌드 설정
1. 좌측상단 File - BuildSetting
2. Platform : WebGL 선택
3. 우측하단 Switch Platform 클릭
4. 좌측하단 PlayerSettings 클릭
5. Player - PublishingSettings - Compression Format - Disabled로 설정

3.2 유니티파일 빌드하기
1. 좌측상단 File - BuildSetting
2. Platform : WebGL 선택
3. 빌드

3.3 리액트 프로젝트에 유니티 빌드
1. Build폴더 React프로젝트의 public 하위로 복사
2. React-Unity-Webgl 라이브러리를 사용하여 빌드
3. 예제코드
```JavaScript
import React from "react";
import { Unity, useUnityContext } from "react-unity-webgl";

function App() {
  const { unityProvider } = useUnityContext({
    loaderUrl: "build/myunityapp.loader.js",
    dataUrl: "build/myunityapp.data",
    frameworkUrl: "build/myunityapp.framework.js",
    codeUrl: "build/myunityapp.wasm",
  });

  return <Unity unityProvider={unityProvider} />;
}
```


### 4. 배포

> git clone을 통해 프로젝트를 받은 뒤 각각의 폴더에서 빌드를 진행합니다.

4.1 frontend 빌드(frontend폴더에서 진행)

```bash
sudo npm install
sudo npm run build
```

4.2 backend 빌드(backend폴더에서 진행)

```bash
sudo ./gradlew build
```

4.3 백엔드 배포

```bash
cd /build/libs
nohup java -jar ssafy-web-project-1.0-SNAPSHOT.jar
```

## 4. 사용한 외부 서비스

### 카카오 로그인

사용자 인증을 위해 사용

#### 설정

- [카카오 디벨로퍼스](https://developers.kakao.com/)
- 로그인
- 내 애플리케이션
- 애플리케이션 추가하기
- 이메일 필수 동의를 위해 개발용 비즈 앱 전환 ([개인 개발자 비즈 앱 전환 방법](https://developers.kakao.com/docs/latest/ko/getting-started/app#biz-app-for-individual))
- 제품 설정 > 카카오 로그인
  - 활성화 & Redirect URI 등록
- 제품 설정 > 카카오 로그인 > 동의항목
  - 닉네임, 프로필 사진, 카카오계정(이메일) 필수 동의 설정
- 앱 설정 > 앱 키
  - REST API
    - 크리덴셜로 다루기 (저장소에 올라가지 않게)
      - 프론트엔드
        - .env.local
        - .env.production.local
      - 백엔드
        - application.yml