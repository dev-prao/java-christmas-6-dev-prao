## 패키지 구조

<div>
<table>
    <tr>
        <th style="text-align: center;">📂 패 키 지</th>
        <th style="text-align: center;">✏️ 클 래 스</th>
        <th style="text-align: center;">📝 설 명</th>
    </tr>
    <tr>
        <td><b>🕹 Controller️</b></td>
        <td><b>📓 EventController</b></td>
        <td>이벤트 로직을 메인으로 동작하는 컨트롤러 클래스</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="6"><b>🙍‍ domain</b></td>
        <td><b>📗 DateDiscount</b></td>
        <td>날짜별 할인을 계산하는 클래스</td>
    </tr>
    <tr>
        <td><b>📗 DayDiscount</b></td>
        <td>요일별 할인을 계산하는 클래스</td>
    </tr>    
    <tr>
        <td><b>📙 EventBadge</b></td>
        <td>이벤트 배지의 기준 가격과 배지를 저장하는 Enum 클래스</td>
    </tr>
    <tr>
        <td><b>📙 Menu</b></td>
        <td>메뉴의 카테고리와 가격을 저장하는 Enum 클래스</td>
    </tr>
    <tr>
        <td><b>📗 PresentEvent</b></td>
        <td>총주문 가격에 따라 증정품 증정 여부를 결정하는 클래스</td>
    </tr>
    <tr>
        <td><b>📗 SpecialDiscount</b></td>
        <td>별표가 표시된 날들을 저장하고 특별 할인을 계산하는 클래스</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="3"><b>📭‍ dto</b></td>
        <td><b>📔 DateDTO</b></td>
        <td>방문 날짜를 전달하는 DTO 레코드</td>
    </tr>
    <tr>
        <td><b>📔 DiscountDTO</b></td>
        <td>할인 금액을 전달하는 DTO 레코드</td>
    </tr>
    <tr>
        <td><b>📔 OrderDTO</b></td>
        <td>주문 정보를 전달하는 DTO 레코드</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="1"><b>✂️‍ parser</b></td>
        <td><b>📗 Parser</b></td>
        <td>입력을 형식에 맞게 파싱하는 클래스</td>
    </tr>
    <tr><td colspan="3"></td></tr>
    <tr>
        <td rowspan="2"><b>📺️ view</b></td>
        <td><b>📗 InputView</b></td>
        <td>입력을 담당하는 클래스</td>
    </tr>
    <tr>
        <td><b>📗 OutputView</b></td>
        <td>출력을 담당하는 클래스</td>
    </tr>
</table>
</div>

## 게임 진행

1. 방문 날짜를 입력 받는다.
    - 올바른 날짜인지 검증 후 잘못된 입력일 경우 다시 입력하도록 한다.
2. 주문 메뉴를 입력 받는다.
    - 올바른 주문인지 검증 후 잘못된 입력일 경우 다시 입력하도록 한다.
3. 방문 날짜를 포함한 미리보기 문구와 입력 받은 주문 메뉴를 출력한다.
5. 할인 전 총주문 금액을 출력한다.
6. 증정 메뉴를 출력한다.
7. 혜택 내역을 계산하여 출력한다.
8. 이벤트 배지를 출력한다.



# 구현할 기능 목록

- 메뉴(Menu)
    + 책임: enum 클래스로 카테고리별 메뉴와 가격을 저장한다.

- 날짜별 할인(DateDiscount)
    + 책임: 날짜별 할인 금액을 계산한다.
      + 이벤트 기간: 2023.12.1 ~ 2023.12.25
      + 1,000원으로 시작해서 하루에 할인 금액이 100원씩 증가
      + 총주문 금액에서 해당 금액만큼 할인

- 요일별 할인(DayDiscount)
    + 책임: 요일별 할인 금액을 계산한다.
      + 평일(일~목): 디저트 메뉴 1개당 2,023원 할인
      + 주말(금,토): 메인 메뉴 1개당 2,023원 할인

- 특별 할인(SpecialDiscount)
    + 책임: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.

- 증정 이벤트(PresentEvent)
    + 책임: 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개 증정

- 배지(Badge)
    + 책임: 총혜택 금액별 배지를 부여한다.
      + 5천 원 이상: 별
      + 1만 원 이상: 트리
      + 2만 원 이상: 산타
