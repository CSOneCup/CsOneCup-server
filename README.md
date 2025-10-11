# CSOneCup Server

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]  
<br />

<div align="center">
  <a href="https://github.com/CSOneCup/CSOneCup-server">
    <img src="logo.png" alt="CSOneCup Logo" width="240" height="240">
  </a>


  <h3 align="center">CSOneCup</h3>

  <p align="center">
    A gamified platform for learning computer science effectively and enjoyably.
    <br />
    <a href="https://github.com/CSOneCup/CSOneCup-server"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/CSOneCup/CSOneCup-server">View Repository</a>
    ·
    <a href="https://github.com/CSOneCup/CSOneCup-server/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/CSOneCup/CSOneCup-server/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
    ·
    <a href="https://github.com/CSOneCup/CSOneCup-client">View Client</a>
  </p>
</div>


## Introduction

**CSOneCup** is a gamified platform designed to make learning computer science (CS) knowledge both engaging and effective. By integrating CS quizzes into a fun and interactive card format, the platform aims to cater to different audiences:

- **Job Seekers**: Effortlessly prepare for technical interviews and coding tests while managing time effectively.
- **CS Students**: Build a deeper understanding of CS subjects without the stress of traditional study methods.
- **Junior Developers**: Continue professional growth while enjoying a gamified learning experience.

CSOneCup empowers users by providing them with the ability to:
- Test and validate their knowledge through quizzes.
- Focus on weak areas by targeting specific categories.
- Create custom decks to share and compete with others.

The platform turns learning into a rewarding and motivating experience through friendly competition and personalization.  
<br />

## 기술 스택

### Backend
- **Framework**: Spring Boot 3.3.5
- **Language**: Java 17
- **Database**: MySQL (AWS RDS 연동)
- **Security**: Spring Security + JWT 인증
- **API Documentation**: Swagger (SpringDoc OpenAPI)
- **Build Tool**: Gradle
- **Dependencies**:
  - Spring Data JPA
  - Lombok
  - JWT (jjwt) 0.12.3

### Frontend
- **Framework**: Flutter
- **Language**: Dart
- **Platform**: Cross-platform (iOS/Android)

### 개발 기간
**2024년 11월 ~ 2024년 12월**

<br />

## 프로젝트 구조

```
csOneCup_server/
├── src/main/java/com/csOneCup/csOneCup/
│   ├── auth/              # JWT 인증 관련
│   │   ├── JWTFilter.java
│   │   └── JWTUtil.java
│   ├── card/              # 카드 관련 도메인
│   │   ├── Card.java
│   │   ├── CardController.java
│   │   ├── CardService.java
│   │   └── CardRepository.java
│   ├── deck/              # 덱 관련 도메인
│   │   ├── Deck.java
│   │   ├── DeckController.java
│   │   ├── DeckService.java
│   │   └── DeckRepository.java
│   ├── user/              # 사용자 관련 도메인
│   │   ├── User.java
│   │   ├── userController.java
│   │   ├── UserService.java
│   │   └── UserRepository.java
│   ├── statistics/        # 통계 관련
│   ├── dto/               # Data Transfer Objects
│   ├── config/            # Spring Security 설정
│   └── global/            # 전역 에러 처리 및 응답
│       ├── common/
│       └── error/
└── resources/
    ├── application.properties
    └── dataset.csv        # CS 퀴즈 데이터
```

## API 엔드포인트

### 사용자 API (`/api/user`)
- `POST /signup` - 회원가입
- `POST /signin` - 로그인 (JWT 토큰 발급)
- `GET /info` - 사용자 정보 조회
- `GET /all/info` - 전체 사용자 정보 조회

### 카드 API (`/api/cards`)
- `GET /user` - 사용자 보유 카드 조회 (카테고리/검색 필터링 가능)
- `POST /user/add` - 사용자에게 카드 추가
- `POST /deck/add` - 덱에 카드 추가
- `POST /deck/new` - 새 덱 생성 및 카드 추가
- `GET /card` - 랜덤 카드 조회 (카테고리별, 중복 여부 선택)

### 덱 API (`/api/decks`)
- `GET /{deckId}/cards` - 특정 덱의 카드 목록 조회
- `POST /` - 새 덱 생성
- `GET /random3` - 랜덤 3개 덱 추천

## Key Features

### Main Features

- **CS Quiz Cards**  
  Each problem is presented as a card with the question on the front and the answer (with explanations) on the back.
  
- **Random Problem Solving**  
  Users can solve random questions or select specific categories to focus on their interests.

- **Custom Deck Building**  
  Users can create personalized decks using owned cards, set categories and time limits, and share them with others to compete.

### Additional Features

- **Competitive Element**  
  A rating and ranking system to encourage users to compete and measure their progress against others.

- **Card Design Customization**  
  Unlock and apply design elements to personalize cards and decks, such as colors, animations, and styles.

- **Quiz Reminders**  
  Notifications to remind users of their scheduled learning times, with quick-access widgets for solving quizzes directly.  
  <br />

## Design
![Group 6808](https://github.com/user-attachments/assets/87203829-677f-4868-9d6e-d7f494839e6f)

<br />

## Getting Started

### Prerequisites

#### For Server (Spring Boot)
1. **Java Development Kit (JDK) 17**:
   - Install JDK 17 from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use your package manager:
     ```sh
     sudo apt install openjdk-17-jdk
     ```
2. **Gradle**:
   - Install Gradle if not bundled with the project:
     ```sh
     sudo apt install gradle
     ```

#### For Client (Flutter)
1. **Flutter SDK**:
   - Install Flutter SDK from [Flutter Official Site](https://flutter.dev/docs/get-started/install).
   - Verify installation:
     ```sh
     flutter doctor
     ```
2. **Dart SDK**:
   - Included with Flutter, no separate installation required.
3. **Android Studio or Xcode** (For Device Emulation and Building Apps):
   - Install [Android Studio](https://developer.android.com/studio) for Android development or Xcode for iOS development.
   - Ensure required plugins (Flutter and Dart) are installed in your IDE.
<br />  

### Installation

#### Server Setup (Spring Boot)

1. Clone the repository:
   ```sh
    git clone https://github.com/CSOneCup/CSOneCup-server.git
   ```
2. Navigate to the project directory:
   ```sh
   cd CSOneCup-server
   ```
3. Build the project:
   ```sh
   ./gradlew build
   ```
4. Run the Spring Boot server:
   ```sh
   ./gradlew bootRun
   ```

#### Client Setup (Flutter)

1. Clone the repository:
   ```sh
   git clone https://github.com/CSOneCup/CSOneCup-client.git
   ```
2. Navigate to the Flutter project directory:
   ```sh
   cd CSOneCup-client
   ```
3. Install dependencies:
   ```sh
   flutter pub get
   ```
4. Run the Flutter application:
   ```sh
   flutter run
   ```
<br />  

## Team Members

<div style="overflow-x: auto;">
  <table style="min-width: 800px; table-layout: fixed; text-align: center;">
    <tr>
      <td align="center">
        <img src="https://avatars.githubusercontent.com/JHoon0214" alt="김재훈" width="120" height="120"><br>
        <a href="https://github.com/JHoon0214"><b>김재훈</b></a><br>
        서버 개발 & 회원 인증 로직 개발
      </td>
      <td align="center">
        <img src="https://avatars.githubusercontent.com/SHKim55" alt="김선호" width="120" height="120"><br>
        <a href="https://github.com/SHKim55"><b>김선호</b></a><br>
        메인 메뉴 개발 & 챌린지 개발
      </td>
      <td align="center">
        <img src="https://avatars.githubusercontent.com/EpicFn" alt="김혁진" width="120" height="120"><br>
        <a href="https://github.com/EpicFn"><b>김혁진</b></a><br>
        퀴즈 로직 개발
      </td>
      <td align="center">
        <img src="https://avatars.githubusercontent.com/ja7811" alt="현재환" width="120" height="120"><br>
        <a href="https://github.com/ja7811"><b>현재환</b></a><br>
        카드 관리 & 덱 생성 로직 개발
      </td>
    </tr>
  </table>
</div>

우리 팀은 **컴퓨터과학 학습의 재미와 효과를 동시에 제공하는 플랫폼**을 만들기 위해 모였습니다. 💪



[contributors-shield]: https://img.shields.io/github/contributors/CSOneCup/CSOneCup-server.svg?style=for-the-badge
[contributors-url]: https://github.com/CSOneCup/CSOneCup-server/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/CSOneCup/CSOneCup-server.svg?style=for-the-badge
[forks-url]: https://github.com/CSOneCup/CSOneCup-server/network/members
[stars-shield]: https://img.shields.io/github/stars/CSOneCup/CSOneCup-server.svg?style=for-the-badge
[stars-url]: https://github.com/CSOneCup/CSOneCup-server/stargazers
[issues-shield]: https://img.shields.io/github/issues/CSOneCup/CSOneCup-server.svg?style=for-the-badge
[issues-url]: https://github.com/CSOneCup/CSOneCup-server/issues
[license-shield]: https://img.shields.io/github/license/CSOneCup/CSOneCup-server.svg?style=for-the-badge
[license-url]: https://github.com/CSOneCup/CSOneCup-server/blob/main/LICENSE
