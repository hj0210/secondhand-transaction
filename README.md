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
유저는 상품을 선택하여 거래 날짜를 예약한다.
예약완료 상태로 POST가 되면 거래진행중으로 상태가 변경된다.
거래대금(price)가 상품의 가격으로 POST되면 재고가 감소된다.
예약/거래/재고 상태가 변경될 경우 알림과 함께 상태가 업데이트된다.

## Event Storming
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/9a650292-74e8-4172-83d0-2a4c7c1b36d1)

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
데이터 세팅 후
- trade 
```
 http :8088/trades id="id" productid="productid" userid="userid" productname="productname" qty="qty" price="price"
 http :8088/trades id=1 productid="100" userid="abc" productname="애플워치" qty="1" price="50000"
```
- stock
```
 http :8088/inventories id="id" productid="productid" productname="productname" qty="qty"
 http :8088/inventories id="1" productid="100" productname="애플워치" qty="1"
```
- notification
```
 http :8088/notices id="id" productid="productid" userid="userid" productname="productname" status="status" 
```

- reservation
```
 http :8088/reserves id="id" productid="productid" userid="userid" productname="productname" qty="qty" reserveDt="reserveDt" address="address" price="price" status="status"
 http :8088/reserves productid="100" userid="abc" productname="애플워치" qty="1" reserveDt="2024-04-04" address="서울특별시 서초구 방배동" price="50000" id=1 status="예약완료" 
```

- 단일진입점 게이트웨이 8088포트로 통신하여 연결된 모습
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/1f374cf6-5e01-4fe6-9119-39a700b63a58)


## Docker
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh

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

