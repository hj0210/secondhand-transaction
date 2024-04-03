![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/68f567d6-21d1-495b-b910-befc388a3a11)

## KTDS 2차 Final Assessment 수행 리포트
본 프로젝트는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 구성으로 설계하였습니다.
- 체크포인트 : https://workflowy.com/s/assessment-check-po/T5YrzcMewfo4J6LW
  
## 클라우드 아키텍처
- 이미지 첨부 예정
## MSA 아키텍처
- 이미지 첨부 예정

# 서비스 시나리오
당근마켓 커버하기

구매자, 판매자를 User(이하 유저)라 칭한다.
유저는 상품을 선택하여 거래 날짜를 예약한다
예약이 모두 완료되면 알림과 함께 상태가 업데이트된다.


주문이 되면 주문 내역이 입점상점주인에게 전달된다
상점주인이 확인하여 요리해서 배달 출발한다
고객이 주문을 취소할 수 있다
주문이 취소되면 배달이 취소된다
고객이 주문상태를 중간중간 조회한다
주문상태가 바뀔 때 마다 카톡으로 알림을 보낸다
비기능적 요구사항

트랜잭션
결제가 되지 않은 주문건은 아예 거래가 성립되지 않아야 한다 Sync 호출
장애격리
상점관리 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다 Circuit breaker, fallback
성능
고객이 자주 상점관리에서 확인할 수 있는 배달상태를 주문시스템(프론트엔드)에서 확인할 수 있어야 한다 CQRS
배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다 Event driven


## Event Storming
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/9a650292-74e8-4172-83d0-2a4c7c1b36d1)

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- reservation
- trade
- stock
- notification


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- reservation
```
 http :8088/reserves id="id" productid="productid" userid="userid" productname="productname" qty="qty" reserveDt="reserveDt" address="address" price="price" status="status" 
```
- trade
```
 http :8088/trades id="id" productid="productid" userid="userid" productname="productname" qty="qty" price="price" 
```
- stock
```
 http :8088/inventories id="id" productid="productid" productname="productname" qty="qty" 
```
- notification
```
 http :8088/notices id="id" productid="productid" userid="userid" productname="productname" status="status" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

