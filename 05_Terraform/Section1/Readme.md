# Terraform 기본

## Terraform 구성요소

### Code, 코드로써의 인프라

Infrastructure as Code, 즉 코드로써의 인프라는 인프라를 이루넌 서버, 미들웨어 그리고 서비스 등, 인프라 구성요소들을 코드를 통해 구축하는 것. **IaC는 코드로써의 장점, 즉 작성 용이성, 재사용성, 유지보수** 등의 장점을 가진다.

### Terraform by Hashicorp

- Terraform is a tool for building, changing, and versioning infrastructure safely and efficiently.

테라폼은 인프라를 만들고, 변경하고, 기록하는 IaC를 위해 만들어진 도구로써, 문법이 쉬워 비교적 다루기 수비고 사용자가 매우 많아 참고할 수 있는 예제가 많다. AWS, Azure, GCP 같은 퍼블릭 클라우드뿐만이 아닌 다양한 서비스들 역시 지원한다.

| 테라폼 구성요소 |                                                                                |
| :-------------: | :----------------------------------------------------------------------------- |
|    provider     | 테라폼으로 생성할 인프라의 종류를 의마합니다.                                  |
|    resource     | 테라폼으로 실제로 생성할 인프라 자원을 의미합니다.                             |
|      state      | 테라폼을 통해 생성한 자원의 상태를 의미합니다.                                 |
|     output      | 테라폼으로 만든 자원을 변수 형태로 state에 저장한느 것을 의미함                |
|     module      | 공통적으로 활용할 수 있는 코드를 문자 그대로 모듈 형태로 정의하는 것을 의미함. |
|     remote      | 다른 경로의 state를 참조하는 것을 말함. output 변수를 불러올때 주로 사용함.    |

### 지금 당장 설치하고 작성해서 무언가를 만들어보세요

먼저 이론을 배우는 것도 중요하지만, 일단 설치하고 작성해서 만들어보시는 걸 추천함. 어떤 SW의 배경과 사상을 이해하는 것도 정말 중요하지만, 먼저 가까워지는 게 더 중요함.
특히 주니어라면, 일단 설치하고 작성해서 무언가를 만들어보는 경험이 매우 중요함.

#### Provider

```
provider = "aws" {
    region = "ap-northeast-2"
    version = "~> 3.0"
}
```

-> Provider 안에서 다양한 Arguments를 가집니다.
AWS RESOURCE를 다루기 위한 파일들을 다운로드 하는 역할을 함.

#### Resource

main.tf, vpc.tf 등 원하는 형태로 파일이름을 사용함.

```
resource "aws_vpc" "example" {
    cidr_block = "10.0.0/16"
    # cidr_block 이외에도 수많은 인자가 존재함.
}
```

테라폼으로 VPC를 생성하는 코드임.
VPC 역시 다양한 Argument 와 다른 구성요소가 존재하지만, 여기서는 간단하게 형태만 보고 넘어가도록 하겠습니다.

#### Terraform state

```
{
    "version" : 4,
    "terraform_version" : "0.12.24",
    "serial" : 3,
    "lineage" : "3c77xxxx-2de4-7736-1447-038974a3c187",
    "outpyts":{},
    "resource":[
        {...},{...}
    ]
}
```

테라폼 state임.
현재 인프라의 상태를 의미하는 것은 아님.
state는 원격 저장소인 'backend'에도 저장될 수 있음.

#### Output

```
resourc "aws_vpc" "default" {
    cidr_block = "10.0.0.0/16"

}
output "vpc_id"{
    value = aws_vpc.default.id
}

output "cidr_block"{
    value = aws_vpc.default.cidr_block
}
```

테라폼 output임
VPC 역시 다양한 Arguement 와 다른 구성요소가 존재하지만, 형태만 보았음.

#### module

```
module "vpc" {
    source = "../_modules/vpc"
    cidr_block = "10.0.0.0/16"
}
```

테라폼 모듈임.
모듈은 한번 만들어진 테라폼 코드로 같은 형태를 반복적으로 만들어낼때 주로 사용함.

#### remote

리포트는 원격 참조 개념으로 이해하면 됨.

```
data "terraform_remote_state" "vpc" {
    backend = "remote"
    config = {
        bucket = "terraform-s3-bucket",
        region = "ap-northeast-2"
        key = "terraform/vpc/terraform.tfstate"
    }
}
```

remote state는 key 값에 명시한 state 에서 변수를 가져옴.

| 테라폼 명령어 |                                                                             |
| :-----------: | --------------------------------------------------------------------------- |
|     init      | 테라폼 명령어 사용을 위해 각종 설정을 진행함.                               |
|     plan      | 테라폼으로 작성한 코드가 실제로 어떻게만들어질지에 대한 예측 결과를 보여줌. |
|     apply     | 테라폼 코드로 실제 인프라를 생성하는 명령어임.                              |
|    import     | 이미 만들어진 자원을 테라폼 state 파일로 옮겨주는 명령어임                  |
|     state     | 테라폼 state를 다루는 명령어임.하위 명령어로 mv, push와 같은 명령어가 있음  |
|    destroy    | 생성된 자원들 state 파일을 모두 삭제한느 명령어임.                          |

|                                                    | Process                                                                                         |                                                                 |
| :------------------------------------------------- | :---------------------------------------------------------------------------------------------- | :-------------------------------------------------------------- |
| Init                                               | Plan                                                                                            | Apply                                                           |
| 작성한 코드에서 init 명령어를 입력합니다.          | 실제로 작성한 테라폼 코드가 어떻게 만들어질지에 대한 예측 결과를 보여주는 명령어임              | 실제로 작성한 코드로 명령어를 생성하는 명령어임.                |
| 테라폼의 다른 명령어들을 위한 설정을 진행함.       | 가장 많이 쓰이는 명령어임. 기본적으로 플랜에 문제가 없어야 apply에 문제가 없을 확률이 높습니다. | 실제 인프라에 영향을 끼치는 명령어이므로 주의깊게 실행을 해야함 |
| 내부적으로 Provider, state, module 설정 등이 있음. |                                                                                                 |                                                                 |

---

# AWS EC and SSH

### AWS EC2

: Amazon Elastic Computing Cloud는 안전하고 크기 조정이 가능한 컴퓨터 용량을 클라우드에서 제공하는 웹 서비스임. 사용자는 간단한 웹 서비스 인터페이스를 통해 간편하게 필요한 용량으로 서버를 구성할 수 있음. 컴퓨팅 리소스에 대한 포괄적인 제어권을 제공하며, Amazon의 검증된 컴퓨팅 인프라에서 실행할 수 있음.

### SSH

: SSH 즉 Secure Shell Protocol 은 네트워크 프로토콜 중 하나로 보통 클라이언트와 서버가 인터넷과 같은 네트워크를 통해 통신을 할때, 보안적으로 안정하게 통신을 하기 위해 사용하는 프로토콜임. 보통 비밀번호 인증과 PSA 공개키 암호화 방식으로 연결을 함.

### EC2 접속이 안된다면?

1. 처음에 EC2를 접근하고자 할때 SSH를 통해서 접속하게 되는데 IP주소와 포트번호를 확인하게 함. 이때 Private IP는 불가함.

2. 이후에는 Security Group을 확인해야함.

- 접속을 시도하는 Port와 IP가 허용되어있어야 함.

3. EC2 인스턴스가 퍼블릭 서브넷에 위치하는지 확인해야 함.
   (퍼블릭 서브넷인지 확인은 해당 서브넷이 연결된 라우팅 테이블 확인
   Routes에 0.0.0.0/0 igw-xxxxx를 확인.)

4. nacl도 확인해야 하지만 일반적으로 anyopen으로 연결되어 있을 확률이 높음.

## 실습

### EC2 만들기

1. 아마존 리눅스 선택
2. 타입은 프리티어 T2.micro 선택
3. 네트워크(기본 vpc), 서브넷(2a), 퍼블릭 IP(활성화)
4. 볼륨 유형 - GP3 선택
5. 태그 (Name-TESTEC2)
6. Security Group(이름 설정), 포트 설정(http 80, ssh 22, ftp 21, mysql 3306, redus 6379)-> 이 번호는 지양
7. 시작하기
8. key pair 저장하기!

### 접속

1. 보안들어가서 모두에게 개방되었는지 확인하기
2. 오른쪽 버튼 눌러서 연결 실시함!
3. shell 창에서 mv (pem name).pem
4. ssh -i (pem name).pem ec2-user@(ip 주소)
   4-1. WARNING : UNPROTECTED PRIVATE KEY FILE! ( 다운로드 파일 권한이 너무 허용되어 있어서 경고주는 것임. 강제로 바꿔야 함! )
5. chmod 600 하고 하면 가능해짐! (4. 반복하면 접속 성공함! )
6. 시큐리티 그룹, 서브넷 확인

- 우선 vpc - 서브넷으로 들어가서,
- 서브넷에 해당하는 라우팅 테이블 들어가서
- 라우팅 -> 대상 0.0.0.0/0에서 active 인지 확인하기!
- nacl의 경우는 인바운드, 아웃바운드 룰있는지 확인하면 됨!

### Ssh 접속

- ssh -i "pem name".pem ec2-user@(ipv4 주소) -p22
- 22번 포트로 접속기다리고 있음
- netstat -lntp로 보면 LISTEN 하고 있다는 것 확인할 수 있음
- "ps -ef | grep ssh"를 통해 확인 가능!
- ls -al 해보게 되면 .ssh 폴더 볼 수 있는데 들어가게 되면 authorized_keys라는 파일이 만들어져있음.
  -> rsa 공캐키 페어가 한쌍으로 저장되어 있다.(로컬과 EC2에) 적합한 경우 통과되게 되어 있다.

- 추가로 양쪽에 추가로 저장해서도 할 수가 있게 된다! (원리 꼭 파악할 것!)

---

# Zsh 및 Ohy-my-zsh 설치

---

# AWS CLI 및 Terraform 설치

---

# AWS Configure 설정

---

# 테라폼 작동원리와 CLI 실습
