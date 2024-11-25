public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Music menuMusic = new Music("assets/music/Eric-Skiff-A-Night-Of-Dizzy-Spells.wav;;;");
        menuMusic.play();

        Menu menu = new Menu(player);
        menu.show();
    }
}
