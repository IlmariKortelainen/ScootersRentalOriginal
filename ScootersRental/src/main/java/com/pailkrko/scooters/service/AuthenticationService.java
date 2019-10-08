package com.pailkrko.scooters.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class AuthenticationService {

    // luodaan salaisuus, jolla alemmalla metodilla saadaan luotua token
    public static final String SECRET = "salaisuus";

    // luodaan token käyttäen ylempänä olevaa salaisuutta, laitetaan issueriksi auth0 ja allekirjoitetaan
    public static String getJwt() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            return token;

        } catch (
                JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }
}
