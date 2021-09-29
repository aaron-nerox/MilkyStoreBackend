package tech.nerostarx.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*


fun Application.configureAuthentication(){
    install(Authentication){
        jwt {

        }
    }
}