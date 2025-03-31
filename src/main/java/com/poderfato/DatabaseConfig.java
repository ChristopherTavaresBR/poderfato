package com.poderfato;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;
import io.vertx.pgclient.PgPool;

public class DatabaseConfig {
    public static SqlClient createClient(Vertx vertx) {
        // Lê variáveis de ambiente (com valores padrão caso não existam)
        String dbHost = System.getenv().getOrDefault("DB_HOST", "localhost");
        int dbPort = Integer.parseInt(System.getenv().getOrDefault("DB_PORT", "5432"));
        String dbName = System.getenv().getOrDefault("DB_NAME", "poderfato");
        String dbUser = System.getenv().getOrDefault("DB_USER", "poder");
        String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "pod3rFato");

        PgConnectOptions connectOptions = new PgConnectOptions()
            .setPort(dbPort)
            .setHost(dbHost)
            .setDatabase(dbName)
            .setUser(dbUser)
            .setPassword(dbPassword);

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

        return PgPool.client(vertx, connectOptions, poolOptions);
    }
}