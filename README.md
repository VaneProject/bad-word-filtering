# BadWordFiltering

<h1><span style="color: red">주의</span></h1>
<span style="color: yellow">비속어, 욕을 다루는 라이브러리인 많큼 코드에 욕과 비속어가 보일 수 있습니다. 양해부탁드립니다.</span>
<br>
<span style="color: pink">현재 라이브러리에 단어를 추가중입니다. 관심이 있으신 분은 아래 링크를 통하여 단어 추가에 협력부탁드립니다.</span><br>

[욕/비속어 코드 링크 바로가기](https://github.com/VaneProject/bad-word-filtering/blob/master/src/main/java/com/vane/badwordfiltering/BadWords.java)

### 블로그
- [[Java] BadWordFiltering (욕, 비속어 필터 라이브러리)](https://persestitan.tistory.com/70)
- [[Java] BadWordFiltering 2 (욕설 필터링 라이브러리)](https://persestitan.tistory.com/71)
- [[Java] BadWordFiltering 3 (라이브러리 배포 안내)](https://persestitan.tistory.com/115)

### 라이브러리
maven
```xml
<dependency>
    <groupId>io.github.vaneproject</groupId>
    <artifactId>badwordfiltering</artifactId>
    <version>1.0.0</version>
</dependency>
```

gradle
```groovy
implementation 'io.github.vaneproject:badwordfiltering:1.0.0'
```

gradle (Kotlin)
```kotlin
implementation("io.github.vaneproject:badwordfiltering:1.0.0")
```

<br>
<br>

# 생성자

```java
BadWordFiltering badWordFiltering = new BadWordFiltering();
```
```java
BadWordFiltering badWordFiltering = new BadWordFiltering(String);
```

# 메소드
### add()
리턴타입: void<br>
설명: 라이브러리에서 지원하는 단어중에 원하는 단어가 없을 경우 해당 메소드를 사용하여 추가할 수 있습니다.<br>
```java
badWordFiltering.add(String[]);
badWordFiltering.add(List<String>);
badWordFiltering.add(Set<String>);
```

### remove()
리턴타입: void<br>
설명: 라이브러리에서 지원하는 단어 중 필터링이 되면 안돼는 단어가 있을 경우 해당 메소드를 사용하여 필터링 단어에서 제거 하실 수 있습니다.<br>
```java
badWordFiltering.remove(String[]);
badWordFiltering.remove(List<String>);
badWordFiltering.remove(Set<String>);
```

### checkAndChange(String)
리턴타입: String<br>
설명: 매개변수에 라이브러리에서 지원하는 단어가 포함되어있을 경우 욕/비속어가 특정 문자로 대체된 값을 반환합니다.<br>
<span style="color: skyblue">특정문자 : 생성자를 쓸때 값을 넣으면 그 값이 적용이 되며, 기본값으로는 * 입니다.</span><br>
```java
String test = "문장...";
badWordFiltering.checkAndChange(test);

System.out.println(test);

출력
욕/비속어가 대체되어서 나온 문장
```

### check(String)
리턴타입: boolean<br>
설명: 비속어/욕이 포함되어있을 경우 true를 반환하고 포함하지 않으면 false를 반환합니다.<br>
```java
boolean test = badWordFiltering.check("문장...");
if (test) {
	(...)
}
```

### blankCheck(String)
리턴타입: boolean<br>
설명: 욕/비속어가 띄어쓰기로 나누어져있어도 띄어쓰기를 무시하고 체크를 합니다. 만약 존재하면 true를 반환하고 없다면 false를 반환합니다.<br>
```java
boolean test = badWordFiltering.blankCheck("문장...");
if (test) {
	(...)
}
```

# 동작 예제
욕은 '욕설'로 대체하여 사용합니다.<br>
### change와 생성자
```java
Sring bad = "욕설을 욕설 말하는 중";
BadWordFiltering badWordFiltering1 = new BadWordFiltering();
BadWordFiltering badWordFiltering2 = new BadWordFiltering("-");

String text1 = badWordFiltering.change(bad);	//기본값 *
String text2 = badWordFiltering.change(bad);	//입력값 -
System.out.println(text1);
System.out.println(text2);

출력
**을 ** 말하는 중
--을 -- 말하는 중
```

<br>
<br>

### check와 blankCheck
```java
Sring bad1 = "욕    설";
Sring bad2 = "욕설";
BadWordFiltering badWordFiltering1 = new BadWordFiltering();

boolean bool1 = badWordFiltering.check(bad1); 		//욕    설
boolean bool2 = badWordFiltering.blankCheck(bad1);	//욕    설
boolean bool3 = badWordFiltering.check(bad2);		//욕설
boolean bool4 = badWordFiltering.blankCheck(bad2);	//욕설
System.out.println(text1);
System.out.println(text2);
System.out.println(text3);
System.out.println(text4);

출력
false
true
true
true
```

---
# 업데이트(23/04/11)
## 변경 사항
`checkAndChange(String)` -> `change(String)`로 메소드명칭이 변경되었습니다.


## 추가 사항
### change(String, String[])
- 리턴 타입 : String
- 파라미터 : `(필터링할 텍스트, 확인할 특수기호)`
- 설명 : 받은 텍스트에 욕설중간에 특수기호등으로 넣어두어도 필터링하는 기능

#### 예시 코드
**필터링될 문자는 욕설로 대체합니다.**
```java
BadWordFiltering filtering = new BadWordFiltering();
System.out.println(filtering.change("욕_설", new String[] {"_"}));

출력
***
```

### readURL(URL, String, boolean)
- 리턴 타입 : void
- 파라미터
```
링크(URL): 욕설 리스트가 정리되어 있는 링크

기호(String): 잘라낼 기준의 기호들 (예시: "+-"일때 "+", "-"기준으로 잘라냄) (기본값: 공백)

양끝 공백 허용(boolean): 양끝 공백을 제거 허용 (기본: true)
```
- 관련 메소드
```java
readURL(URL): 기호와 양끝 공백를 기본값으로 사용 
readURL(URL, String): 양끝 공백을 기본값으로 사용
readURL(URL, boolean): 기호를 기본값으로 잘라내기
readURL(String): URL타입이 아닌 String 타입으로 입력
readURL(String, String): URL과 동일
readURL(String, boolean): URL과 동일
readURL(String, String, boolean): URL과 동일
```
- 설명: 욕설이 적혀있는 파일를 읽은뒤에 잘라낼 기호의 기준으로 잘라내어 단어를 추가하는 로직

#### 코드 예시
```java
BadWordFiltering filtering = new BadWordFiltering();
String url = "https://raw.githubusercontent.com/PersesTitan/BadWordFiltering/master/badwords.txt";
// ex1)
filtering.readURL(url, ",");
// ex2)
filtering.readURL(new URL(url), ",");
```

### readFile(File, String, boolean)
- 리턴 타입 : void
- 파라미터
```
file(File): 욕설 리스트가 정리되어 있는 파일

기호(String): 잘라낼 기준의 기호들 (예시: "+-"일때 "+", "-"기준으로 잘라냄) (기본값: 공백)

양끝 공백 허용(boolean): 양끝 공백을 제거 허용 (기본: true)
```
- 관련 메소드
```java
readFile(File): 기호와 양끝 공백를 기본값으로 사용
readFile(File, String): 양끝 공백을 기본값으로 사용
readFile(File, boolean): 기호를 기본값으로 잘라내기
readFile(String): File타입이 아닌 String 타입으로 경로 입력
readFile(String, String): File과 동일
readFile(String, boolean): File과 동일
readFile(String, String, boolean): File과 동일
```
- 설명: 욕설이 적혀있는 링크를 읽은뒤에 잘라낼 기호의 기준으로 잘라내어 단어를 추가하는 로직

#### 코드 예시
```java
BadWordFiltering filtering = new BadWordFiltering();

String filePath = "badwords.txt";
// ex1)
filtering.readFile(filePath, ",");
// ex2)
filtering.readFile(new File(filePath), ",");
```