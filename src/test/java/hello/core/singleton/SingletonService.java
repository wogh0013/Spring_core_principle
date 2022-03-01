package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체 instance를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2. 이 객체 instance가 필요하면, 오직 getInstance() 메서드를 통해서만 조회 가능
    //   이 메서드를 호출하면 항상 같은 인스턴스가 반환된다.
    public static SingletonService getInstance(){
        return instance;
    }

    //3. 딱 1개의 인스턴스만 존재해야 함
    //   ★생성자를 private으로 선언 -> 외부에서 new 키워드로 객체 생성 X
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출출");
    }
}

// 객체 생성 -> 객체 반환해주는 메서드 생성 -> private 생성자