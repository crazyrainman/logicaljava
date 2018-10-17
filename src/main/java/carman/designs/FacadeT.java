package carman.designs;
class SubSystem {
    public void turnOnTV() {
        System.out.println("turnOnTV()");
    }
    public void setCD(String cd) {
        System.out.println("setCD( " + cd + " )");
    }
    public void starWatching() {
        System.out.println("startWatching");
    }
}
class Facade {
    private SubSystem subSystem = new SubSystem();
    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.starWatching();
    }
}
public class FacadeT {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.watchMovie();
    }
}