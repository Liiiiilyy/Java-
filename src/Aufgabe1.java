/**
 * Aufgabe 1: Schatzsuche nach Zielkoordinaten (50 Punkte)
 *
 * Vervollständigt den Konstruktor und die Methode "bewege" entsprechend
 * ihrer Beschreibungen.
 *
 * Die Methode "main" soll zum Testen benutzt werden.
 *
 * Die Startposition der Figur und die Position des Schatzes werden bei
 * der Bewertung andere sein. Änderungen an der Methode "main" gehen nicht
 * in die Bewertung ein. Quelltexte, die nicht kompilieren, werden nicht
 * gewertet.
 */

/*
题目描述：

        有一个游戏场景，有一个角色和一个宝藏，角色的起始位置和朝向已经固定，而宝藏的位置是作为构造函数的参数传递进来的。要求在每次移动中，角色应该朝向宝藏的方向，直到到达宝藏位置为止。

        需要实现的方法：

        构造函数
        构造函数应该接受两个参数：宝藏的横坐标和纵坐标。在构造函数中可以为类添加其他必要的属性。

        bewege() 方法
        该方法应该在每次调用时计算出角色朝向宝藏的方向，如果角色已经到达宝藏位置则返回 false，否则返回 true。在返回 true 的情况下，角色应该向前移动一步，朝向宝藏的方向前进。

        main() 方法
        在 main() 方法中创建一个 Aufgabe1 实例，并在循环中调用 bewege() 方法。在每次调用 bewege() 方法后，应该根据角色当前的朝向和位置来移动角色，并调用 Game.sleep() 方法来等待一定的时间。*/
class Aufgabe1
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject figur = new GameObject(4, 2, 0, "laila");

    // Hier weitere Attribute, falls ihr welche benötigt
    private final int treasureX;
    private final int treasureY;
    /**
     * Konstruktor einer Schatzsucher:in.
     * @param x Die x-Koordinate des Schatzes.
     * @param y Die y-Koordinate des Schatzes.
     */
    Aufgabe1(final int x, final int y)
    {
        treasureX = x;
        treasureY = y;
    }

    /**
     * Die Methode soll die Figur bei jedem Aufruf so drehen, dass sie in
     * Richtung des Schatzes guckt. Solange die Methode true zurückliefert,
     * wird die Figur danach automatisch einen Schritt vorwärts, also in
     * Blickrichtung, bewegt, wodurch sie dem Schatz näher kommen sollte.
     *
     * Benötigte Methoden der Figur: getX, getY, setRotation
     *
     * x-Koordinaten wachsen nach rechts, y-Koordinaten nach unten. Die
     * Rotation ist 0 in Richtung +x, 1 in Richtung +y, 2 in Richtung -x
     * und 3 in Richtung -y.
     *
     * @return true, wenn die Figur einen Schritt vorwärts machen soll.
     *         false, wenn der Schatz erreicht wurde.
     */
    boolean bewege()
    {
        int dx = treasureX - figur.getX();
        int dy = treasureY - figur.getY();
        if (dx == 0 && dy == 0) {
            // Wenn die Figur schon am Ziel ist, beende die Suche.
            return false;
        }
        // Bestimme die neue Drehung der Figur.
        int newRotation = 0;
        if (dx > 0) {
            newRotation = 0;
        } else if (dx < 0) {
            newRotation = 2;
        } else if (dy > 0) {
            newRotation = 1;
        } else if (dy < 0) {
            newRotation = 3;
        }
            figur.setRotation(newRotation);
        return true;
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung nähert sich die Figur
     * dem Ziel ständig und bleibt stehen, wenn es erreicht wurde (Füße auf
     * dem Kreuz).
     * Insbesondere sollte dann auch das Programm anhalten.
     */
    public static void main(String[] args)
    {
        final Aufgabe1 instanz = new Aufgabe1(12, 9);

        final GameObject figur = instanz.figur;
        final int[][] versatz = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (instanz.bewege()) {
            figur.setLocation(figur.getX() + versatz[figur.getRotation()][0],
                              figur.getY() + versatz[figur.getRotation()][1]);
            Game.sleep(100);
        }
    }
}
