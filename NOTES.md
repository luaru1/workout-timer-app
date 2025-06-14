# TODO: 루틴 타이머 앱 기획 및 개발 정리

## [핵심 방향성]
- 사용자가 직접 루틴을 생성하고 실행하는 가벼운 운동 타이머 앱
- 광고 최소화 (하단 배너만 사용)
- 로그인, 서버 없이 로컬 저장 기반
- 운동 동작 설명, 추천 기능 없음
- 최대한 직관적이고 방해 없는 UX 지향

---

## [홈 화면]
- [ ] 오늘 요일에 해당하는 루틴만 표시
- [ ] 각 루틴은 제목 + 완료 여부 표시
- [ ] "오늘은 쉬는 날입니다 ☕️" 표시 (루틴이 없는 경우)
- [ ] 루틴 클릭 → 루틴 실행 화면 이동
- [ ] 루틴 완료 시 해당 루틴 완료 상태 업데이트

---

## [루틴 실행 화면]
- [ ] 루틴 항목 순차 진행 (ExerciseItem, RestItem)
- [ ] ExerciseItem
  - [ ] 시간 기반: 타이머로 자동 진행
  - [ ] 횟수 기반: 사용자 완료 버튼으로 진행
- [ ] RestItem: 자동 타이머 진행
- [ ] 모든 항목은 건너뛰기(Skip) 가능
- [ ] 루틴 종료 시 자동으로 홈 화면 복귀

---

## [루틴 생성/편집 화면] (추후)
- [ ] 루틴 제목 입력
- [ ] 운동 항목 추가 (이름, 타입[TIME/COUNT], 값[초/횟수])
- [ ] 자동으로 휴식 항목 삽입
- [ ] 요일 설정 기능 추가 (월~일 선택)

---

## [데이터 저장]
- [ ] Room: 루틴 정보, 운동 항목 저장
- [ ] SharedPreferences: 간단한 설정값 저장 (예: 사용자 닉네임)

---

## [기타 고려 사항]
- [ ] 루틴 공유 기능은 QR/텍스트 기반으로만 (서버 없이)
- [ ] 로그인/회원 기능은 구현하지 않음
- [ ] 피드백 수집, 분석, 통계 등의 기능은 추가하지 않음
- [ ] 초보자용 설명, 추천 기능은 포함하지 않음

---

## [향후 아이디어 (보류)]
- [ ] 루틴 진행률 표시 (ex: 2/5)
- [ ] 루틴 완료 시 간단한 애니메이션 효과
- [ ] 홈 화면 UI 테마 커스터마이징




# 새로 배운 것

## Kotlin 관련

- `sealed class`
  - `sealed`는 상속 가능한 클래스를 제한하는 키워드
    - 현재 파일 안에 정의된 클래스만 `sealed` 클래스를 상속받을 수 있음
- `abstract val`
  - 자식 클래스에서 반드시 구현해야 하는 변수를 선언
- `override val`
  - 부모 클래스의 속성을 구현하는 변수를 선언
- `private val`
  - 외부에서 조작할 수 없도록 제한한 변수를 선언
- `<...>` (제네릭)
  - 타입을 매개변수처럼 사용하는 문법
  - 타입에 `?`를 붙이면 null을 허용한다는 의미
- `StateFlow`
  - 현재 상태를 항상 보유하면서 변경을 감지할 수 있는 스트림
  - 값을 바꿀 수 없는 읽기 전용
- `MutableStateFlow`
  - Kotlin의 비동기 스트림 중 하나로, 값이 바뀔 때마다 자동으로 구독자에게 알림을 보냄
  - Jetpack Compose에서는 UI가 이 값을 관찰해 자동으로 다시 그려짐
- `mutableListOf`
  - 변경 가능한 리스트를 생성
  - `listOf`는 읽기 전용인데 반해, `add()`, `remove()`등으로 수정할 수 있음
- `forEachIndexed`
  - 리스트의 각 요소를 반복하면서 index를 함께 가져옴

## Android 앱 구조

- `ViewModel`
  - UI와 데이터 로직 사이를 연결하는 중간 관리자 역할
  - `ViewModel`이 데이터 상태를 들고 있어 UI는 이를 그대로 보여주기만 하면 됨