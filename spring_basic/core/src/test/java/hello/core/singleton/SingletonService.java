package hello.core.singleton;

public class SingletonService {
    //static 영역에 객체를 한 개만 생성
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() { //private 생성자로 외부에서 new 키워드로 객체 인스턴스를 생서하지 못하도록 막음
    }

    private void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
