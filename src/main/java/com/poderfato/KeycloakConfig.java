package com.poderfato;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;


import java.io.InputStream;

public class KeycloakConfig {
    private static KeycloakDeployment keycloakDeployment;

    static {
        InputStream is = KeycloakConfig.class.getResourceAsStream("/keycloak.json");
        keycloakDeployment = KeycloakDeploymentBuilder.build(is);
    }

    public static KeycloakDeployment getDeployment() {
        return keycloakDeployment;
    }
}
