public class Emulatore {

    public static Sistema sistema;
    public static Vista vista;
    public static void main(String[] args) {

        Sistema sistema= new Sistema( );
        Vista vista= new Vista( sistema );

        vista.inizia();
    }

}
