package fi.nano.pathfinding;

public class main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0) {
            args = new String[]{"41x41", //maze
                "false", //allowDiagonal
                "A*", //algo
                "5", //moveDelay
                "20", //doorDelay
                "10", //waitBeforeFail
                "true", //logEnabled
                "true", //windowEnabled
                "false", //smallTiles
                "200" //waitInMillis
        };
        }

        Pathfinding pathfinding = new Pathfinding(args);
        pathfinding.Run();
        pathfinding.PrintResults();
    }
}
