import dataaccess.DataAccessConfig;
import dataaccess.MySQLDataAccess;
import server.PetServer;

public class ServerMain {
    /**
     * Starts the server on the given port. If port is 0 then a random port is used.
     */
    public static void main(String[] args) {
        try {
            var port = 8080;
            if (args.length >= 1) {
                port = Integer.parseInt(args[0]);
            }
            var config = DataAccessConfig.testDefault;
            if (args.length > 1) {
                if (args.length != 5) throw new Exception("Invalid args");
                config = new DataAccessConfig(args[1], args[2], args[3], args[4]);
            }

            var server = new PetServer(new MySQLDataAccess(config)).run(port);
            port = server.port();
            System.out.printf("Server started on port %d%n", port);
            return;
        } catch (Exception ex) {
            System.out.printf("Unable to start server: %s%n", ex.getMessage());
        }
        System.out.println("""
                Pet Store Server:
                java ServerMain <port> [<dburl> <dbuser> <dbpassword> <dbname>]
                """);
    }
}