package hello.core.singleton;

public class SingletonService {

    //static영역에 하나만 존재하게끔
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //private 생성자 - 다른데서 객체 생성 못하도록
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
