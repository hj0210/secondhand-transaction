![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/68f567d6-21d1-495b-b910-befc388a3a11)

## KTDS 2차 Final Assessment 수행 리포트
본 프로젝트는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 구성으로 설계하였습니다.
- 체크포인트 : https://workflowy.com/s/assessment-check-po/T5YrzcMewfo4J6LW
  
## 클라우드 아키텍처
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/3bb6987b-a3c2-4af6-a1f0-c1ee81b55325)


## MSA 아키텍처
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/3b53847e-5b9a-4158-8820-eddb9c639f21)


# 서비스 시나리오
당근마켓 커버하기

구매자, 판매자를 User(이하 유저)라 칭한다.

유저는 상품을 선택하여 거래 날짜를 예약한다.

예약완료 상태로 POST가 되면 거래진행중으로 상태가 변경된다.

유저가 인수인계/완료를 POST하여 상태변경이 이루어지면 재고가 감소된다.

예약/거래/재고 상태가 변경될 경우 알림과 함께 상태가 업데이트된다.

Mypage view에서는 CQRS를 기반으로 Data projection을 진행한다.

## Event Storming
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/99a5f76f-ba78-4574-8325-d95e4475e23d)

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- reservation
- trade
- stock
- notification
- Mypage

## Test by API
데이터 세팅 후
- trade 
```
 http :8080/trades id="id" productid="productid" userid="userid" productname="productname" qty="qty" price="price"
 http :8080/trades id=1 productid="100" userid="abc" productname="애플워치" qty="1" price="50000"
```
- stock
```
 http :8080/inventories id="id" productid="productid" productname="productname" qty="qty"
 http :8080/inventories id="1" productid="100" productname="애플워치" qty="1"
```
- reservation
```
 http :8080/reserves id="id" productid="productid" userid="userid" productname="productname" qty="qty" reserveDt="reserveDt" address="address" price="price" status="status"
 http :8080/reserves productid="100" userid="abc" productname="애플워치" qty="1" reserveDt="2024-04-04" address="서울특별시 서초구 방배동" price="50000" id=1 status="예약완료" 
```

- 단일진입점 게이트웨이 8088포트로 통신하여 연결된 모습
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/1f374cf6-5e01-4fe6-9119-39a700b63a58)


## Docker
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh


## 컨테이너 환경
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/4f115764-4b8d-4db5-a572-55ee1e750ce5)

### 오토스케일아웃 HPA

chj0210/reservation:20240404 

kubectl apply -f - <<EOF
apiVersion: v1
kind: Pod
metadata:
  name: siege
spec:
  containers:
  - name: siege
    image: chj0210/reservation:20240404 
EOF

- HPA 설정
```
kubectl autoscale deployment reservation --cpu-percent=50 --min=1 --max=3

```

![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/241c47b0-2920-4748-8663-3b1be7220c7c)

- 부하 가동

```
kubectl exec -it siege -- /bin/bash
siege -c1 -t2S -v http://a45408026295548f58ed3b56f796c0c7-760492431.ca-central-1.elb.amazonaws.com:8080/reserves
```
오토스케일링 확인

![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/9a83cbb7-9630-48c2-aee6-df4f0c4b3adf)


### 환경변수 구성하기 (ConfigMap)

- configmap 설정
 RESERVATION_LOG_LEVEL: INFO 레벨로 변경하여 업데이트

```
kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  RESERVATION_LOG_LEVEL: INFO
EOF
```

- 로그확인

```
kubectl logs -l app=reservation
```

- configmap 결과
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/a7ca9fe6-ffce-4e59-9b20-742101dce0bc)

### EFS 파일시스템 마운트 PVC
- 환경변수 설정

```
export AWS_ROOT_UID=879772956301
export REGION=ca-central-1
export CLUSTER_NAME=user12-eks
export FILE_SYSTEM_ID=fs-07cd311e1a18d29a1)
```

-  Cluster에 EFS CSI 드라이버 설치

```
helm repo add aws-efs-csi-driver https://kubernetes-sigs.github.io/aws-efs-csi-driver
helm repo update
helm upgrade -i aws-efs-csi-driver aws-efs-csi-driver/aws-efs-csi-driver \
  --namespace kube-system \
  --set image.repository=602401143452.dkr.ecr.ca-central-1.amazonaws.com/eks/aws-efs-csi-driver \
  --set controller.serviceAccount.create=false \
  --set controller.serviceAccount.name=efs-csi-controller-sa
```

-  EFS csi Driver로 StorageClass 생성

```
kubectl apply -f - <<EOF
kind: StorageClass
apiVersion: storage.k8s.io/v1
metadata:
  name: efs-sc
provisioner: efs.csi.aws.com
parameters:
  provisioningMode: efs-ap
  fileSystemId: $FILE_SYSTEM_ID
  directoryPerms: "700"
EOF
```
- 결과
  
![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/c8e7d35b-052c-4747-83ee-8cc42d98ae9e)

### 셀프힐링 & 무정지 배포 
- 컨테이너에 장애가 생겼을 때, 컨테이너 플랫폼이 자동으로 장애를 감지하여 복구

- Liveness Probe 확인
```
http a45408026295548f58ed3b56f796c0c7-760492431.ca-central-1.elb.amazonaws.com:8080/actuator/health
```

# Liveness Probe Fail 설정 및 확인
http put a45408026295548f58ed3b56f796c0c7-760492431.ca-central-1.elb.amazonaws.com:8080/actuator/down
http a45408026295548f58ed3b56f796c0c7-760492431.ca-central-1.elb.amazonaws.com:8080/actuator/health

![image](https://github.com/hj0210/secondhand-transaction/assets/68845747/9fb741a7-a274-4ae1-99e9-a75ebc5a2c2d)



